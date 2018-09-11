package shjd;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 补录项目性质的值(重招后的项目会出现项目性质缺失现象), 将在台帐表格放到C:\Users\Administrator\Desktop\台帐问题\项目性质缺失
 * 文件夹内
 * @date 2016/12/7 9:40$
 */
public class ReplenishProjectNature4SHJD {
    //初始化数据源
    static DBUtils dbUtils = new DBUtils();
    private static NutDao shDao = dbUtils.getShjdDao();
    private static NutDao inDao = dbUtils.getInDao();
    private static NutDao cnDao = dbUtils.getCnDao();

    @Test
    public void execute() {

        //1,读取excel
        String filePath = "C:\\Users\\Administrator\\Desktop\\台帐问题\\项目性质缺失";
        FileInputStream fis = null;
        Workbook workbook = null;
        try {
            File dir = new File(filePath);
            if (dir.isDirectory()) {
                String[] fileList = dir.list();
                for (String fileName : fileList) {
                    File file = new File(filePath + "\\" + fileName);
                    if (!file.isDirectory()) {
                        fis = new FileInputStream(filePath + "\\" + fileName);
                    }
                    if (fileName.endsWith("xlsx")) {
                        workbook = new XSSFWorkbook(fis);//07/10
                    } else if (fileName.endsWith("xls")) {//03
                        workbook = new HSSFWorkbook(fis);
                    } else {
                        continue;
                    }

                    short projectNumberIndex = 0;//项目编号在行中的索引位置
                    int projectNatureIndex = 0;//项目性质在行中的索引位置
                    int projectStatusIndex = 0;//项目状态在行中的索引位置
                    int bidderPriceIndex = 0;//项目状态在行中的索引位置

                    int queNum = 0;//有问题的记录数
                    int sucessNum = 0;//同步成功的记录数
                    List<String> faileList = new ArrayList<String>();//同步失败的项目编号集合

                    int sheets = workbook.getNumberOfSheets();
                    for (int s = 0; s < sheets; s++) {//遍历单个excel中的sheet
                        Sheet sheet = workbook.getSheetAt(s);
                        if (sheet != null) {
                            Row headerRow = sheet.getRow(0);//获取表格头
                            short cellNum = headerRow.getLastCellNum();
                            for (short s1 = 0; s1 < cellNum; s1++) {
                                //获取招标编号和中标日期所在的列位置
                                String cellValue = headerRow.getCell(s1).getStringCellValue();
                                if (cellValue.contains("招标编号")) {
                                    projectNumberIndex = s1;
                                }
                                if (cellValue.contains("项目性质")) {
                                    projectNatureIndex = s1;
                                }
                                if (cellValue.contains("项目状况")) {
                                    projectStatusIndex = s1;
                                }
                            }
                            int rowNum = sheet.getLastRowNum();
                            for (int i = 1; i <= rowNum; i++) {//遍历每行,获取项目编号及中标日期的值
                                Row tempRow = sheet.getRow(i);
                                if (tempRow != null) {
                                    Cell projectNumberCell = tempRow.getCell(projectNumberIndex);
                                    Cell projectNatureCell = tempRow.getCell(projectNatureIndex);
                                    //Cell projectStatusCell = tempRow.getCell(projectStatusIndex);
                                    //如果项目编号非空,项目性质为空且项目没有撤项.则此条记录为问题数据
                                    if (projectNumberCell != null && projectNatureCell == null) {
                                        //if (!projectStatusCell.getStringCellValue().contains("撤项")) {
                                        String projectNumber = projectNumberCell.getStringCellValue();
                                        if (projectNumber.contains("重招")) {//如果重招,处理项目编号
                                            projectNumber = projectNumber.substring(0, projectNumber.length() - 4);
                                        }
                                        //为出现问题的记录计数
                                        queNum++;
                                        //查询上海机电数据库中的记录
                                        List<Record> records = shDao.query("bm_project", Cnd.where("project_number", "like", "%" + projectNumber + "%"));
                                        if (records.size() == 2) {//如果数据库中有两条数据
                                            Record record0 = records.get(0);
                                            Record record1 = records.get(1);
                                            String project_nature0 = (String) record0.get("project_nature");
                                            String project_nature1 = (String) record1.get("project_nature");
                                            //如果第一条数据值为空,第二条数据值非空,同步
                                            if (StringUtils.isBlank(project_nature0) && StringUtils.isNotBlank(project_nature1)) {
                                                //将第二条中的数据同步到第一条记录中
                                                shDao.update("bm_project", Chain.make("project_nature", project_nature1), Cnd.where("id", "=", record0.get("id")));
                                                sucessNum++;
                                            }
                                            //第一条记录数据非空,第二条数据值为空,同步
                                            else if (StringUtils.isNotBlank(project_nature0) && StringUtils.isBlank(project_nature1)) {
                                                //将第1条记录中数据同步到第2条记录中
                                                shDao.update("bm_project", Chain.make("project_nature", project_nature0), Cnd.where("id", "=", record1.get("id")));
                                                sucessNum++;
                                            }//两条记录的值都为空.去国际标数据库中查询
                                            else if (StringUtils.isBlank(project_nature0) && StringUtils.isBlank(project_nature1)) {
                                                //去国际标库中查询最新的项目
                                                List<Record> re = inDao.query("proj_inter_project", Cnd.where("project_number", "like", "%" + projectNumber + "%").orderBy("CREATE_TIME", "DESC"));
                                                if (re.size() > 0) {
                                                    String project_nature = (String) re.get(0).get("project_nature");
                                                    if (StringUtils.isNotBlank(project_nature)) {
                                                        shDao.update("bm_project", Chain.make("project_nature", project_nature), Cnd.where("id", "=", record1.get("id")));
                                                        shDao.update("bm_project", Chain.make("project_nature", project_nature), Cnd.where("id", "=", record0.get("id")));
                                                        sucessNum++;
                                                    }
                                                } else {
                                                    faileList.add(projectNumber);
                                                }

                                            }
                                        } else if (records.size() == 1) {//如果数据中只有一条记录
                                            Record record0 = records.get(0);
                                            String project_nature0 = (String) record0.get("project_nature");
                                            if (StringUtils.isBlank(project_nature0)) {//如果project_nature值为空
                                                //去国际标库中查询最新的项目
                                                List<Record> re = inDao.query("proj_inter_project", Cnd.where("project_number", "like", "%" + projectNumber + "%").orderBy("CREATE_TIME", "DESC"));
                                                if (re.size() > 0) {
                                                    String project_nature = (String) re.get(0).get("project_nature");
                                                    //如果查询到的数据非空
                                                    if (StringUtils.isNotBlank(project_nature)) {
                                                        //将查询到数据同步到上海机电库中
                                                        shDao.update("bm_project", Chain.make("project_nature", project_nature), Cnd.where("id", "=", record0.get("id")));
                                                    } else {
                                                        faileList.add(projectNumber);
                                                    }
                                                } else {
                                                    faileList.add(projectNumber);
                                                }
                                            }
                                        }
                                        //}
                                    }
                                }
                            }

                        }
                    }
                    //打印同步失败的项目编号
                    System.out.println("出现问题的记录数为: " + queNum);
                    System.out.println("处理成功的条数的为: " + sucessNum);
                    System.out.println("同步失败的记录数为: " + faileList.size() + "  项目编号依次为:");
                    for (String s : faileList) {
                        System.out.println(s);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
