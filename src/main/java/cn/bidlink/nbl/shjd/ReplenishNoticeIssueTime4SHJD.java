package cn.bidlink.nbl.shjd;

import com.alibaba.druid.pool.DruidDataSource;
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

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 补录上海机电中标日期
 * @date 2016/12/6 9:22$
 */
public class ReplenishNoticeIssueTime4SHJD {

    /**
     * @Description: 补录中标日期的入口方法
     * @Date: 2016/12/6 9:34
     */
    @Test
    public void execute() {

        //1,从表格中读取项目编号,及中标日期
        Map<String, Date> params = this.readExcel();
        //2,根据项目编号查询中标日期,如为空则写入.如非空,记录项目编号
        Map<String, List> executeResult = this.replenishTime(params);
        //3,打印处理结果
        printResult(executeResult);
    }

    /**
     * @Description: 读取excel
     * @return: map<项目编号,中标日期>
     * @Date: 2016/12/6 9:41
     */
    private Map<String, Date> readExcel() {
        Map<String, Date> result = new HashMap<String, Date>();
        Workbook workbook = null;
        FileInputStream fis = null;
        //要扫描的文件夹路径
        String filePath = "C:\\Users\\Administrator\\Desktop\\台帐问题";
        File file = new File(filePath);
        if (file != null && file.isDirectory()) {
            try {
                String[] files = file.list();//获取文件夹内所有文件

                for (String excelName : files) {//遍历文件
                    String fileName = filePath + "\\" + excelName;
                    if (fileName.endsWith(".xls")) {//2003,2007
                        fis = new FileInputStream(fileName);
                        workbook = new HSSFWorkbook(fis);
                    } else if (fileName.endsWith(".xlsx")) {
                        fis = new FileInputStream(fileName);
                        workbook = new XSSFWorkbook(fis);//2010
                    } else {
                        continue;
                    }

                    short projectNumberIndex = 0;
                    int timeIndex = 0;
                    int sheets = workbook.getNumberOfSheets();
                    //只遍历第一个sheet
                    for (int s = 0; s < 1; s++) {//遍历单个excel中的sheet
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
                                if (cellValue.contains("中标日期")) {
                                    timeIndex = s1;
                                }
                            }
                            int rowNum = sheet.getLastRowNum();
                            for (int i = 1; i <= rowNum; i++) {//遍历每行,获取项目编号及中标日期的值
                                Row tempRow = sheet.getRow(i);
                                if (tempRow != null) {
                                    Cell projectNumberCell = tempRow.getCell(projectNumberIndex);
                                    Cell timeCell = tempRow.getCell(timeIndex);
                                    if (projectNumberCell == null || timeCell == null) {
                                        continue;
                                    }
                                    String projectNumber = projectNumberCell.getStringCellValue();
                                    Date issueTime = timeCell.getDateCellValue();
                                    /*if (timeCell.getCellType()== HSSFCell.CELL_TYPE_STRING){
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        issueTime =simpleDateFormat.parse(timeCell.getStringCellValue().trim());
                                    }else {
                                        issueTime = ;
                                    }*/
                                    //如单元格值非空,放入到map中
                                    if (projectNumber != null && !"".equals(projectNumber) && issueTime != null) {
                                        result.put(projectNumber, issueTime);
                                    }
                                }
                            }
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

        return result;
    }

    /**
     * @Description: 将中标日期补充到数据库中
     * @param:
     * @return:
     * @Date: 2016/12/6 11:14
     */
    private Map<String, List> replenishTime(Map<String, Date> params) {
        Map<String, List> result = new HashMap<String, List>();

        DruidDataSource shjdDataSource = new DruidDataSource();
        shjdDataSource.setUrl("jdbc:mysql://222.66.64.102:3306/meetc-official?useUnicode=true");
        shjdDataSource.setUsername("meetcuser");
        shjdDataSource.setPassword("bU01l@w0%Il");
        NutDao shDao = new NutDao(shjdDataSource);

        Set<String> keySet = params.keySet();
        List<String> successList = new ArrayList<String>();
        List<String> faileList = new ArrayList<String>();
        for (String key : keySet) {

            List<Record> records = shDao.query("bm_project", Cnd.where("project_number", "=", key));
            if (records != null || records.size() > 0) {
                Date Time = params.get(key);
                Object notice_issue_time = records.get(0).get("notice_issue_time");
                int updateResult = shDao.update("bm_project", Chain.make("notice_issue_time", Time), Cnd.where("project_number", "=", key));
                if (updateResult > 0) {//补录成功
                    successList.add(key);
                } else {//补录失败
                    faileList.add(key);
                }
            } else {
                faileList.add(key);
            }
        }

        result.put("successList", successList);
        result.put("faileList", faileList);

        return result;
    }

    private void printResult(Map<String, List> executeResult) {
        if (executeResult == null) {
            System.out.println("没有处理成功的数据!");
        } else {
            Set<String> keySet = executeResult.keySet();
            for (String s : keySet) {
                List<String> list = executeResult.get(s);
                if (s.contains("success")) {
                    System.out.println("已经成功补录" + list.size() + "条数据!");
                } else {
                    System.out.println("处理失败的数据条数为:" + list.size());
                }
            }
        }
    }

}
