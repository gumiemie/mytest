package cn.bidlink.nbl.shjd;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用来处理上海机电数据同步
 * @date 2016/11/7
 */
public class DisposeDataForSHJD {

    private FileInputStream is = null;
    private Workbook workbook = null;

    /**
     * @Description: 用来处理上海机电数据的执行方法
     * @param:
     * @return:
     * @Date: 2016/11/7 14:58
     */
    public void execute() {
        //第一步:读取待处理数据excel,获取要处理的项目编号.
        List<String> projectNumbers = this.readExcelFile();
        //第二步:根据项目编号(project_number)查询到项目id,并将查询到的结果生成excel文件.
        Map<String, String> projectIds = this.findProjectIds(projectNumbers);
        //第三步:模拟发送http请求,拉取ID对应的信息
        this.sendHttpRequest(projectIds);

    }

    /**
     * @Description: 同步上海机电数据(处理少量数据,不用读取excel)
     * @return: void
     * @Date: 2017/1/17 9:43
     */
    @Test
    public void execute1(){

        //List<String> params = new ArrayList<String>();
        //params.add("0613-176033070026");//将需要处理的项目编号,存入到参数集合中
        Map<String, String> projectIds = new HashMap<String, String>();
        projectIds.put("cnIds","1306258121,1306258099,1306258116,1306258066,1306258107,1306258054,1306258053,1306258052,1306258049,1306258050,1306257958");

        projectIds.put("inIds","153094,154199,149981,153415,153833,147874,147873,152475");
        this.sendHttpRequest(projectIds);

    }

    /**
     * @Description: 读取上海机电数据表格, 获取project_number
     * @param:
     * @return: ArrayList<String>,其中元素值为project_number
     * @Date: 2016/11/7 15:01
     */
    private List<String> readExcelFile() {

        List<String> results = new ArrayList<String>();
        String fileName = "C:\\Users\\Administrator\\Desktop\\工作簿1.xlsx";//设定文件路径
        try {
            is = new FileInputStream(fileName);
            if (fileName.endsWith(".xls")) {//2003,2007
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(is);//2010
            } else {
                throw new Exception("读取的文件不是excel");//不是excel
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        int numberOfSheets = workbook.getNumberOfSheets();//获取sheet的个数
        for (int i = 0; i < numberOfSheets; i++) {//遍历依次获取每个sheet
            Sheet sheet = workbook.getSheetAt(i);
            int projectNumberIndex = 0;
            Row headerRow = sheet.getRow(0);
            if (headerRow==null){
                break;
            }
            short cellNum = headerRow.getLastCellNum();
            for (short a = 0; a < cellNum; a++) {//遍历表格头的行信息,获取"招标编号"所在列的索引
                String headerCellValue = headerRow.getCell(a).getStringCellValue();
                boolean isContains = headerCellValue.contains("招标编号");
                if (isContains){
                    projectNumberIndex = a;//拿到项目编号所在列的索引值.
                    break;
                }
            }
            int lastRowNum = sheet.getLastRowNum();
            for (int j = 1; j < lastRowNum; j++) {//遍历sheet,从第二行开始
                Row tempRow = sheet.getRow(j);
                if (tempRow != null) {
                    Cell cell = tempRow.getCell(projectNumberIndex);
                    String projectNumber = cell==null?"":cell.getStringCellValue();
                    if (projectNumber.startsWith("0613-")) {//判断目标值是否为项目编号
                        //将项目编号写入集合中
                        results.add(projectNumber.contains("重招") == false ? projectNumber : projectNumber.substring(0, projectNumber.length() - 4));
                    }
                }
            }
        }

        return results;
    }

    /**
     * @Description: 根据项目编号查询到项目ID将结果写入到excel中
     * @param: 项目编号的集合
     * @return:
     * @Date: 2016/11/7 16:50
     */
    private Map<String, String> findProjectIds(List<String> params) {

        NutDao cnDao = null;
        NutDao inDao = null;
        NutDao shDao = null;

        String inId = "";
        String cnId = "";

        List<String> cnIds = new ArrayList<String>();//存放国内标ID
        List<String> inIds = new ArrayList<String>();//存放国际标ID
        List<String> noIdNumbers = new ArrayList<String>();//存放未找到项目ID的项目编号

        try {
            DruidDataSource cnDataSource = new DruidDataSource();
            cnDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/bms_cn?useUnicode=true");
            cnDataSource.setUsername("ebnewuser");
            cnDataSource.setPassword("ebnewuserbid6link!@#$");
            cnDao = new NutDao(cnDataSource);

            DruidDataSource inDataSource = new DruidDataSource();
            inDataSource.setUrl("jdbc:mysql://211.151.182.215:3306/bms_yf?useUnicode=true");
            inDataSource.setUsername("bps2015");
            inDataSource.setPassword("bps2015@zw!");
            inDao = new NutDao(inDataSource);

            DruidDataSource shjdDataSource = new DruidDataSource();
            shjdDataSource.setUrl("jdbc:mysql://222.66.64.102:3306/meetc-official?useUnicode=true");
            shjdDataSource.setUsername("meetcuser");
            shjdDataSource.setPassword("bU01l@w0%Il");
            shDao = new NutDao(shjdDataSource);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //遍历所有项目,以查询项目ID,并区分国内标或国际标
        for (String projectNumber : params) {
            List<Record> cnResults = cnDao.query("proj_inter_project", Cnd.where("project_number", "=", projectNumber).orderBy("create_time", "desc"));
            if (cnResults != null && cnResults.size() != 0) {//在国内标查询到项目ID
                String tempCnId = cnResults.get(0).getString("id");
                cnIds.add(tempCnId);
            } else {
                List<Record> inResults = inDao.query("proj_inter_project", Cnd.where("project_number", "=", projectNumber).orderBy("create_time", "desc"));
                if (inResults != null && inResults.size() != 0) {//在国际标中查询到项目ID
                    String tempInId = inResults.get(0).getString("id");
                    inIds.add(tempInId);
                } else {//都没有查询到项目ID
                    noIdNumbers.add(projectNumber);
                }
            }
        }

        try {

            System.out.println("需要查询的项目个数为:" + params.size());
            System.out.print("未能查询到projectId的个数为:" + noIdNumbers.size() + " ,project_number依次为: ");
            if (noIdNumbers.size()!=0){
                String noResult = "";
                //将未查询到项目ID的项目编号写入到excel中
                FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\result.xls"));
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfWorkbook.createSheet("未能查询到projectId");
                sheet.setColumnWidth(0,600);//设置列宽
                sheet.createRow(0).createCell(0).setCellValue("未查到ID的项目编号");
                for (String string : noIdNumbers) {
                    noResult += (string + ",");
                    int lastRowNum = sheet.getLastRowNum();
                    HSSFRow row = sheet.createRow(++lastRowNum);
                    HSSFCell cell = row.createCell(0);
                    cell.setCellValue(string);
                }
                hssfWorkbook.write(fileOutputStream);
                System.out.println(noResult.substring(0, noResult.length() - 1));
            }

            System.out.print("成功查询到ID的国内标个数为:" + cnIds.size() + ";查询到的ID为:");
            if(cnIds.size()!=0){
                for (String a : cnIds) {
                    cnId += (a + ",");
                }
                cnId = cnId.substring(0, cnId.length() - 1);
                System.out.println(cnId);

            }

            System.out.print("成功查询到ID的国际标个数为:" + inIds.size() + ";查询到的ID为:");
            if (inIds.size()!=0){
                for (String a : inIds) {
                    inId += (a + ",");
                }
                inId = inId.substring(0, inId.length() - 1);
                System.out.println(inId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, String> results = new HashMap<String, String>();
        results.put("cnIds", cnId);
        results.put("inIds", inId);
        return results;
    }


    /**
     * @Description: 用于发送http请求来摘取数据.
     * @param:
     * @return:
     * @Date: 2016/11/8 14:23
     */
    @SuppressWarnings("all")
    public void sendHttpRequest(Map params){

        //从参数中获取需要处理的项目ID
        String cnIds = (String) params.get("cnIds");//国内标
        String inIds = (String) params.get("inIds");//国际标

        //请求路径
        String cnUrl = "http://new.shbid.com/bid/admin/againSyncData.action?project_id="+cnIds+"&type=2&all=0";
        String inUrl = "http://new.shbid.com/bid/admin/againSyncData.action?project_id="+inIds+"&type=1&all=0";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try{

        for (int i=1;i<21;i++){//处理国内标数据
            if(i!=11){
                continue;
            }
            String tempUrl=cnUrl;
            tempUrl+=("&interfaceId="+i);
            HttpGet httpGet = new HttpGet(tempUrl);
            HttpPost httpPost = new HttpPost(tempUrl);
            try{
                response = httpClient.execute(httpGet);//发送get请求
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    response.close();//关闭连接
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        for (int j=1;j<23;j++){//处理国际标数据
            if(j!=11){
                continue;
            }
            String tempUrl=inUrl;
            tempUrl+=("&interfaceId="+j);
            HttpGet httpGet = new HttpGet(tempUrl);
            try{
                response = httpClient.execute(httpGet);//发送get请求
                System.out.println(response.getEntity().getContent());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    response.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }


}
