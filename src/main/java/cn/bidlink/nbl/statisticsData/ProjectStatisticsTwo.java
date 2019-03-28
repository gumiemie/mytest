package cn.bidlink.nbl.statisticsData;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import utils.DBUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 统计租户下项目信息, 投标人, 中标人, 成交金额，
 * for 梁老师
 * @date 2018/10/24 16:16$
 */
@SuppressWarnings("all")
public class ProjectStatisticsTwo {
    //设置参数,时间范围
    private final String beginDate = "2019-02-01";
    private final String endDate = "2019-02-28";

    private final String filePath = "E:\\文档\\数据统计\\";
    private final String[] titles = new String[]{"机构名称", "流程类型", "项目数量", "投标商数量", "中标商数量", "中标金额(万元)"};

    //要统计的租户code
    //北京国际 0610 ; 湖南国联 0646 ; 云南云创 0848 北京建壮安徽分公司 2935,北京建智达工程管理股份有限公司 1440,海南海机采招标服务有限公司 1668 ,广东海虹药通电子商务有限公司 2220;
    private final String[] tenders = new String[]{"0646", "0610", "0848"};
    private DBUtils dbUtils = new DBUtils();
    private final NutDao biddingDao = dbUtils.getBiddingDao();
    private final NutDao nblUserDao = dbUtils.getNblUserDao();
    private final NutDao oaDao = dbUtils.getOaDao();
    private final NutDao centerDao = dbUtils.getCenterDao();

    @Test
    public void execute() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = format.parse(this.beginDate + " 00:00:00");
        Date endDate = format.parse(this.endDate + " 23:59:59");
        String fileName = filePath + this.beginDate + "-" + this.endDate + "电子标统计.xlsx";
        export(this.tenders, beginDate, endDate, fileName);
    }

    public void countBidderByProjectType() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = format.parse(this.beginDate + " 00:00:00");
        Date endDate = format.parse(this.endDate + " 23:59:59");
        StringBuilder sb1 = new StringBuilder("select DISTINCT BIDDER_NAME from bid where SYSTEM_STATUS = 1 And package_id in(select id from packages where tenant_id = @tenantId" +
                "and TENDER_PROCUREMENT_MODE = @mode)");
        buildParamStr(sb1, null, beginDate, endDate);
        Sql sql1 = Sqls.create(sb1.toString());

        StringBuilder sb2 = new StringBuilder("select Sum(open_price) from bid where SYSTEM_STATUS = 1 And package_id in(select id from packages where tenant_id = @tenantId" +
                "and TENDER_PROCUREMENT_MODE = @mode)");
        buildParamStr(sb2, null, beginDate, endDate);
        Sql sql2 = Sqls.create(sb2.toString());
        String[] columns = new String[]{"国内招标(含资格预审)", "竞争性谈判", "询价", "单一来源", "竞争性磋商", "资格预审"};
        List<Map<String, String>> tenderNameAndTenantIds = getTenderNameAndTenantIds(tenders);
        for (Map map : tenderNameAndTenantIds) {
            Set<String> set = map.keySet();
            for (String key : set) {
                workbook.createSheet((String) map.get(key));
                setParams(sql1, key, beginDate, endDate);
                setParams(sql2, key, beginDate, endDate);
                for (int index = 0; index < columns.length; index++) {

                }
            }
        }


    }


    /**
     * 统计数据并导出excel
     *
     * @param tenders
     * @param beginDate
     * @param endDate
     */
    private void export(String[] tenders, Date beginDate, Date endDate, String fileName) {
        if (tenders.length == 0) {
            System.out.println("参数非法!");
            return;
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        createTitleRow(sheet, titles);
        List<Map<String, String>> tenderNameAndTenantIds = getTenderNameAndTenantIds(tenders);
        for (Map map : tenderNameAndTenantIds) {
            Set<String> set = map.keySet();
            for (String key : set) {
                List<List<String>> lists = queryProject(key, (String) map.get(key), beginDate, endDate);
                for (List<String> list:lists){
                    XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
                    for (int i = 0; i < list.size(); i++) {
                        row.createCell(i).setCellValue(list.get(i));
                    }
                }
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(fileName));
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //根据公司code 查询nbl中tenantId和企业名称
    private List<Map<String, String>> getTenderNameAndTenantIds(String[] tenders) {
        ArrayList<Map<String, String>> Maps = new ArrayList();
        for (String tenderCode : tenders) {
            List<Record> companys = centerDao.query("t_reg_company", Cnd.where("code", "=", tenderCode));
            if (companys != null && companys.size() == 1) {
                Record company = companys.get(0);
                List<Record> users = nblUserDao.query("user", Cnd.where("OLD_COMP_ID", "=", company.getInt("id")).and("SYSTEM_STATUS", "=", 1)
                        .and("IS_SUBJECT", "=", 0));
                HashMap<String, String> tenderNameAndTenantId = new HashMap<String, String>();
                tenderNameAndTenantId.put(users.get(0).getString("id"), company.getString("name"));
                Maps.add(tenderNameAndTenantId);
            }
        }
        return Maps;
    }

    //生成表头
    private void createTitleRow(XSSFSheet sheet, String[] titles) {

        if (sheet == null || titles == null || titles.length == 0) {
            return;
        }
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }
    }

    //组装查询结果
    private List<List<String>> queryProject(String tenantId, String tenderName, Date beginDate, Date endDate) {
        List<List<String>> resultList = new ArrayList<List<String>>();
        String[] columns = new String[]{"国内招标（含资审）", "竞争性谈判", "询价", "单一来源", "竞争性磋商"};
        for (int i = 1; i <= 5; i++) {
            ArrayList<String> strings = new ArrayList<String>();
            strings.add(tenderName);
            strings.add(columns[i-1]);
            if (i == 1) {
                strings.add(countProject(i, tenantId, beginDate, endDate) + "(" + countPrequalify(tenantId, beginDate, endDate) + ")");
            } else {
                strings.add(countProject(i, tenantId, beginDate, endDate));
            }
            strings.add(countBidder(null, i, tenantId, beginDate, endDate));
            strings.add(countBidder(1, i, tenantId, beginDate, endDate));
            strings.add(sumOpenPrice(tenantId, i, beginDate, endDate));
            resultList.add(strings);
        }
        return resultList;
    }

    //查询项目数量(不包括资审)
    private String countProject(Integer mode, String tenantId, Date beginDate, Date endDate) {
        StringBuilder sqlSb = new StringBuilder("SELECT count(1) FROM project WHERE SYSTEM_STATUS = 1 AND IS_TEST = 0");
        if (mode != null) {
            sqlSb.append(" AND TENDER_PROCUREMENT_MODE = @mode");
        }
        buildParamStr(sqlSb, tenantId, beginDate, endDate);
        Sql sql = Sqls.create(sqlSb.toString());
        sql.setParam("mode", mode);
        setParams(sql, tenantId, beginDate, endDate);
        sql.setCallback(Sqls.callback.integer());
        Sql result = biddingDao.execute(sql);
        return result.getInt() + "";
    }

    //查询资格预审项目数量
    private String countPrequalify(String tenantId, Date beginDate, Date endDate) {
        StringBuilder sqlSb = new StringBuilder("SELECT count(1) FROM project WHERE TENDER_PROCUREMENT_MODE = 1");
        buildParamStr(sqlSb, tenantId, beginDate, endDate);
        String tempTypeIds = getTempTypeIds(tenantId);
        if (StringUtils.isBlank(tempTypeIds)) {
            return "0";
        }
        sqlSb.append(" AND TYPE_ID in").append(tempTypeIds);
        Sql sql = Sqls.create(sqlSb.toString());
        sql.setCallback(Sqls.callback.integer());
        setParams(sql, tenantId, beginDate, endDate);
        Sql result = biddingDao.execute(sql);
        return result.getInt() + "";
    }

    //获取资格预审流程的templateTypeIds
    private String getTempTypeIds(String tenantId) {
        Sql sql = Sqls.create("SELECT id FROM nbl_oa.app_set_template_type WHERE TEMPLATE_TYPE_KEY LIKE '%prequalify%' and SYSTEM_STATUS = 1 AND TENANT_ID = @tenantId");
        sql.setParam("tenantId", tenantId);
        sql.setCallback(Sqls.callback.strList());
        Sql result = oaDao.execute(sql);
        List<String> typeIds = result.getList(String.class);
        StringBuilder idsStr = new StringBuilder("(");
        for (String id : typeIds) {
            idsStr.append("'" + id + "'").append(",");
        }
        if (idsStr.length() > 1) {
            return idsStr.substring(0, idsStr.length() - 1) + ")";
        }
        return "";
    }


    //查询投/中标人数量
    private String countBidder(Integer bidResult, Integer mode, String tenantId, Date beginDate, Date endDate) {
        StringBuilder sqlSb = new StringBuilder("select DISTINCT BIDDER_NAME FROM bid WHERE SYSTEM_STATUS = 1 AND IS_TEST = 0 ");
        if (bidResult != null) {
            sqlSb.append(" AND BID_RESULT = @bidResult");
        }
        if (mode != null) {
            sqlSb.append(" AND package_id in(SELECT id from packages WHERE TENDER_PROCUREMENT_MODE = @mode AND TENANT_ID = @tenantId)");
        }
        buildParamStr(sqlSb, tenantId, beginDate, endDate);
        Sql sql = Sqls.create(sqlSb.toString());
        setParams(sql, tenantId, beginDate, endDate);
        if (bidResult != null) {
            sql.setParam("bidResult", bidResult);
        }
        if (mode != null) {
            sql.setParam("mode", mode);
        }
        sql.setCallback(Sqls.callback.strList());
        Sql result = biddingDao.execute(sql);
        return result.getList(String.class).size() + "";
    }

    //查询总中标金额
    private String sumOpenPrice(String tenantId, Integer mode, Date beginDate, Date endDate) {
        StringBuilder sqlSb = new StringBuilder("SELECT SUM(OPEN_PRICE) FROM bid WHERE BID_RESULT = 1 AND SYSTEM_STATUS = 1 AND OPEN_price < 10000");
        if (mode != null) {
            sqlSb.append(" AND package_id in(SELECT id from packages WHERE TENDER_PROCUREMENT_MODE = @mode AND TENANT_ID = @tenantId)");
        }
        buildParamStr(sqlSb, tenantId, beginDate, endDate);
        Sql sql = Sqls.create(sqlSb.toString());
        setParams(sql, tenantId, beginDate, endDate);
        if (mode != null) {
            sql.setParam("mode", mode);
        }
        sql.setCallback(Sqls.callback.doubleValue());
        Sql result = biddingDao.execute(sql);
        return result.getDouble() + "";
    }


    //构建sql语句条件
    private void buildParamStr(StringBuilder sqlSb, String tenantId, Date beginDate, Date endDate) {
        if (StringUtils.isNotBlank(tenantId)) {
            sqlSb.append(" AND TENANT_ID = @tenantId");
        }
        if (beginDate != null) {
            sqlSb.append(" AND CREATE_TIME > @beginDate");
        }
        if (endDate != null) {
            sqlSb.append(" AND CREATE_TIME < @endDate");
        }
    }


    //设置sql参数
    private void setParams(Sql sql, String tenantId, Date beginDate, Date endDate) {
        if (StringUtils.isNotBlank(tenantId)) {
            sql.setParam("tenantId", tenantId);
        }
        if (beginDate != null) {
            sql.setParam("beginDate", beginDate);
        }
        if (endDate != null) {
            sql.setParam("endDate", endDate);
        }

    }


}
