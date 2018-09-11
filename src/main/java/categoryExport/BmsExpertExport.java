package categoryExport;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 导出BMS库中的专家数据供销售对比
 * @date 2017/3/3 17:03$
 */
public class BmsExpertExport {

    private FileInputStream is = null;
    private Workbook workbook = null;
    private static FileOutputStream fos;

    @Test
    public void execute() throws Exception {
        Map<String,String> result= new HashMap<String, String>();
        String fileName = "C:\\Users\\Administrator\\Desktop\\bbbb.xlsx";//设定文件路径
        try {
            is = new FileInputStream(fileName);
            if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(is);//2010
            } else {
                throw new Exception("读取的文件不是excel");//不是excel
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int j = 0; j < lastRowNum; j++) {//遍历sheet,从第二行开始
                Row tempRow = sheet.getRow(j);
                if (tempRow != null) {
                    String name = tempRow.getCell(0).getStringCellValue();
                    String cate = tempRow.getCell(1).getStringCellValue();
                    String s = result.get(name);
                    result.put(name,StringUtils.isNotBlank(s)?s+","+cate:cate);
                }
            }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet1 = workbook.createSheet("专家2");

        for (Map.Entry entry:result.entrySet()){
            int rowNum = sheet1.getLastRowNum();
            HSSFRow row = sheet1.createRow(rowNum + 1);
            row.createCell(1).setCellValue(entry.getKey().toString());
            row.createCell(2).setCellValue(entry.getValue().toString());
        }

        fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\专家2.xls"));
        workbook.write(fos);
        fos.flush();
        fos.close();

    }

}
