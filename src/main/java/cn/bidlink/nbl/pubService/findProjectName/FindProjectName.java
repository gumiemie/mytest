package cn.bidlink.nbl.pubService.findProjectName;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import utils.DBUtils;
import utils.ExcelFileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-08-14 11:05
 */
public class FindProjectName {

    private Sheet sheet;
    private NutDao oaDao = new DBUtils().getOaDao();
    private NutDao biddingDao = new DBUtils().getBiddingDao();

    @Test
    public void execute() {
        try {
            sheet = ExcelFileUtil.getSheetByIndex("/Users/guyang/Documents/国内编号.xls", 0);
            Workbook workbook = sheet.getWorkbook();
            int lastRowNum = sheet.getLastRowNum();

            Sheet newSheet = workbook.createSheet();
            Row titleRow = newSheet.createRow(1);
            titleRow.createCell(1).setCellValue("项目编号");
            titleRow.createCell(2).setCellValue("项目名称");
            titleRow.createCell(3).setCellValue("包名称");

            HashSet<String> numberSet = new HashSet<>();

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = this.sheet.getRow(i);
                String number = row.getCell(0).getStringCellValue().trim();
                if (!numberSet.contains(number)) {
                    numberSet.add(number);

                    List<String> dataList = findDataByNumber(number);

                    HashSet<String> nameSet = new HashSet<>();
                    dataList.forEach(data -> {
                        String projectName = analysisData(data);
                        if (!nameSet.contains(projectName)) {
                            nameSet.add(projectName);
                        }
                    });
                    int cellNo = 2;
                    if (nameSet.size()>1){
                        cellNo = 3;
                    }
                    Iterator<String> iterator = nameSet.iterator();
                    while (iterator.hasNext()){
                        Row tempRow = newSheet.createRow(newSheet.getLastRowNum() + 1);
                        tempRow.createCell(1).setCellValue(number);
                        tempRow.createCell(cellNo).setCellValue(iterator.next());
                    }
                }
            }

            workbook.write(new FileOutputStream(new File("/Users/guyang/Documents/国内数据-last.xls")));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private List<String> findDataByNumber(String number) {
        Sql sql = Sqls.create("select data from supervise_send_log where create_time > '2019-01-01' and type < 4 and is_send =1 and data like @data ");
        sql.setParam("data", "%" + number + "%");
        sql.setCallback(Sqls.callback.strList());
        oaDao.execute(sql);
        if (StringUtils.isBlank(sql.getString())) {
            biddingDao.execute(sql);
        }
        return sql.getList(String.class);
    }

    private String analysisData(String data) {
        if (StringUtils.isNotBlank(data)) {
            HashMap hashMap = JSONObject.parseObject(data, HashMap.class);
            return hashMap.get("tenderProjectName").toString();
        }

        return "";
    }


}
