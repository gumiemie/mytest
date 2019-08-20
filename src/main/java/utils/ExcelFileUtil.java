package utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description excel文件工具类，用于excel导入导出
 * @date 2018/7/3 10:05$
 */
public class ExcelFileUtil {

    /**
     * 对外开放的接口
     *
     * @param isUseSerialColumn 是否使用序号列
     * @param downloadFileName  下载文件名
     * @param headers           表格头
     * @param fields            集合元素中要取值的属性
     * @param dataList          填充到表格中的数据
     * @param map               值的对应关系,值要处理,map保存着对应关系
     * @param response          返回值
     */
    public static void exportFile(Boolean isUseSerialColumn, String downloadFileName, String[] headers, String[] fields, Collection<?> dataList, Map<String, Map> map, HttpServletResponse response) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        setHeaders(isUseSerialColumn, sheet, headers);
        fillContent(isUseSerialColumn, sheet, fields, dataList, map);
        doExport(downloadFileName, workbook, response);
    }

    /**
     * 对外开放的接口，导出文件到本地
     *
     * @param downloadFileName 文件名
     * @param headers          表格头
     * @param fields           要取值的字段项
     * @param dataList         数据集合
     * @param map              需要额外处理的字段的value的对应关系
     * @param filePath         导出的路径
     */
    public static void exportFile(String downloadFileName, String headers, String fields, Collection<?> dataList, Map<String, Map> map, String filePath) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        setHeaders(false, sheet, headers.split(","));
        fillContent(false, sheet, fields.split(","), dataList, map);
        doExport(downloadFileName, workbook, filePath);
    }

    /**
     * 对外开放的接口
     *
     * @param isUseSerialColumn 是否使用序号列
     * @param downloadFileName  下载文件名
     * @param headers           表格头
     * @param fields            集合元素中要取值的属性
     * @param dataList          填充到表格中的数据
     * @param response          返回值
     */
    public static void exportFile(Boolean isUseSerialColumn, String downloadFileName, String[] headers, String[] fields, Collection<?> dataList, HttpServletResponse response) {
        Map map = null;
        exportFile(isUseSerialColumn, downloadFileName, headers, fields, dataList, map, response);
    }

    /**
     * 对外开放的接口
     *
     * @param isUseSerialColumn 是否使用序号列
     * @param downloadFileName  下载文件名
     * @param headers           表格头
     * @param fields            集合元素中要取值的属性
     * @param dataList          填充到表格中的数据
     * @param response          返回值
     */
    public static void exportFile(Boolean isUseSerialColumn, String downloadFileName, String headers, String fields, Collection<?> dataList, HttpServletResponse response) {
        Map map = null;
        exportFile(isUseSerialColumn, downloadFileName, headers.split(","), fields.split(","), dataList, map, response);
    }


    /**
     * 对外开放的接口
     *
     * @param isUseSerialColumn 是否使用序号列
     * @param downloadFileName  下载文件名
     * @param headers           表格头
     * @param fields            集合元素中要取值的属性
     * @param dataList          填充到表格中的数据
     * @param fieldsMappingJson 原value值的对应关系
     * @param response          返回值
     */
    public static void exportFile(Boolean isUseSerialColumn, String downloadFileName, String[] headers, String[] fields, Collection<?> dataList, String fieldsMappingJson, HttpServletResponse response) {
        HashMap hashMap = JSON.parseObject(fieldsMappingJson, HashMap.class);
        exportFile(isUseSerialColumn, downloadFileName, headers, fields, dataList, hashMap, response);
    }


    /**
     * 填充表格正文内容
     *
     * @param isSerial 首列是否使用序号
     * @param sheet
     * @param fields   集合元素中要取值的属性
     * @param dataList 表格里要填充的数据
     */
    private static void fillContent(Boolean isSerial, Sheet sheet, String[] fields, Collection dataList, Map<String, Map> map) {
        if (fields == null || fields.length == 0 || dataList == null || dataList.size() == 0) {
            return;
        }
        //首序是否使用序号？
        int serialCellIndex = 0;
        int serialNo = 1;
        if (isSerial) {
            serialCellIndex = 1;
        }
        Iterator<?> iterator = dataList.iterator();
        int lastRowNum = sheet.getLastRowNum();
        if (sheet.getRow(0) != null) {
            lastRowNum = 1;
        }
        while (iterator.hasNext()) {
            Row row = sheet.createRow(lastRowNum++);
            if (isSerial) {
                row.createCell(0).setCellValue(serialNo++);
            }
            Object next = iterator.next();
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i];
                if (next instanceof Map) {
                    setCellValue(row.createCell(i + serialCellIndex), ((Map) next).get(fieldName), map != null ? map.get(fieldName) : null);
                } else {
                    Class<?> aClass = next.getClass();
                    char c = Character.toUpperCase(fieldName.charAt(0));
                    String newFieldName = c + fieldName.substring(1);
                    try {
                        Method method = aClass.getMethod("get" + newFieldName, null);
                        Object returnValue = method.invoke(next, null);
                        setCellValue(row.createCell(i + serialCellIndex), returnValue, map != null ? map.get(fieldName) : null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 通过输出流写出
     *
     * @param downloadFileName
     * @param workBook
     * @param response
     */
    private static void doExport(String downloadFileName, Workbook workBook, HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            //设置下载响应头
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
            response.setContentType("application/vnd.ms-excel");
            outputStream = response.getOutputStream();
            workBook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 写出到本地文件
     */
    private static void doExport(String downloadFileName, Workbook workBook, String filePath) {
        if (StringUtils.isBlank(filePath)) {
            filePath = "D:/";
        }
        if (StringUtils.isBlank(downloadFileName)) {
            downloadFileName = "test.xls";
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(filePath + downloadFileName));
            workBook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置标题行
     *
     * @param isSerial 首列是否使用序号列
     * @param sheet
     * @param headers  标题行
     */
    private static void setHeaders(Boolean isSerial, Sheet sheet, String[] headers) {
        sheet.setDefaultColumnWidth(100);
        if (headers != null && headers.length > 0) {
            Row headerRow = sheet.createRow(0);
            int serialNoCellIndex = 0;
            if (isSerial) {
                headerRow.createCell(serialNoCellIndex).setCellValue("序号");
                serialNoCellIndex = 1;
            }
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i + serialNoCellIndex).setCellValue(headers[i]);
                //设置自动列宽
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
            }
        }
    }

    /**
     * 设置cell的值
     *
     * @param map   value需要特殊处理,map中存放的是对应关系
     * @param cell
     * @param value 设置的值
     */
    private static void setCellValue(Cell cell, Object value, Map<Object, String> map) {
        boolean flag = false;
        if (map != null && map.keySet().size() > 0) {
            flag = true;
        }
        if (cell == null || value == null) return;
        if (value instanceof Date) {
            //此段代码有bug,最多能设置43个单元格的日期格式
           /* Workbook workbook = cell.getSheet().getWorkbook();
            CellStyle cellStyle = workbook.createCellStyle();
            DataFormat dataFormat = workbook.createDataFormat();
            cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);*/
            String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) value);
            cell.setCellValue(dateString);
        } else if (value instanceof String) {
            if (flag) {
                cell.setCellValue(map.get(value));
                return;
            }
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            if (flag) {
                cell.setCellValue(map.get(value));
                return;
            }
            cell.setCellValue(((Integer) value).intValue());
        } else if (value instanceof Double) {
            cell.setCellValue(((Double) value).intValue());
        } else if (value instanceof Float) {
            cell.setCellValue(((Float) value).intValue());
        } else if (value instanceof Long) {
            cell.setCellValue(((Long) value).intValue());
        } else {
            cell.setCellType(Cell.CELL_TYPE_BLANK);
        }
    }

    /**
     * 设置cell的值
     *
     * @param cell
     * @param value
     */
    private static void setCellValue(Cell cell, Object value) {
        setCellValue(cell, value, null);
    }

    /**
     * 处理下载名的中文乱码问题
     *
     * @param fileName
     * @param request
     * @throws Exception
     */
    public static String handleFileName(String fileName, HttpServletRequest request) throws Exception {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }

        return fileName;
    }


    private static Sheet readExcelFile(String filePath, Integer sheetIndex, String sheetName) throws Exception {

        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        Workbook workbook;
        if (filePath.contains(".xls")) {
            workbook = new HSSFWorkbook(fileInputStream);
        } else if (filePath.contains(".xlsx")) {
            workbook = new XSSFWorkbook(fileInputStream);
        } else {
            throw new Exception("文件路径参数非法");
        }

        if (sheetIndex!=null&&sheetIndex>-1){
            return workbook.getSheetAt(sheetIndex);
        }

        if (StringUtils.isNotBlank(sheetName)){
            return workbook.getSheet(sheetName);
        }

        return null;

    }

    public static Sheet getSheetByIndex(String filePath,Integer sheetIndex) throws Exception{
        return readExcelFile(filePath,sheetIndex,null);
    }

    public static Sheet getSheetByName(String filePath,String sheetName) throws Exception{
        return readExcelFile(filePath,null,sheetName);
    }

}
