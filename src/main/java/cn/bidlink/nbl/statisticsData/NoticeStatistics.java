package cn.bidlink.nbl.statisticsData;

import cn.bidlink.nbl.common.model.OpenProduct;
import cn.bidlink.nbl.common.model.User;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import scala.collection.mutable.HashSet;
import utils.DBUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/11/13 10:03$
 */
public class NoticeStatistics {
    DBUtils dbUtils = new DBUtils();
    private final NutDao biddingDao = dbUtils.getBiddingDao();
    private final NutDao nblUserDao = dbUtils.getNblUserDao();
    private final NutDao oaDao = dbUtils.getOaDao();

    //设置参数,时间范围
    private final String beginDate = "2018-01-01";
    private final String endDate = "2018-10-31";
    private final String filePath = "E:\\文档\\数据统计\\";


    @Test
    public void execute() {
        //根据产品开通类型查询公司及名称
        //国际标付费用户
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet1 = workbook.createSheet("国际标付费用户");
        List<OpenProduct> companys = findCompanys(true, 2);
        HashMap hashMap = new HashMap();
        HashSet hashSet = new HashSet();

        //根据公司id查询公司租户id
        for (int i = 0; i < companys.size(); i++) {
            XSSFRow row = sheet1.createRow(i);
            OpenProduct op = companys.get(i);
            String tenanId = getTenanId(op.getCompanyId());
            row.createCell(1).setCellValue(op.getCompanyName());
            //根据租户id查询项目量和自助公告量
            row.createCell(2).setCellValue(countProjectByTenantId(tenanId, beginDate, endDate));
            row.createCell(3).setCellValue(countNoticeByTenantId(tenanId, beginDate, endDate));
            hashSet.add(op.getCompanyId());
        }
        XSSFSheet sheet2 = workbook.createSheet("国际标未付费用户");
        List<OpenProduct> companys2 = findCompanys(false, 2);
        //根据公司id查询公司租户id
        for (int i = 0; i < companys2.size(); i++) {
            XSSFRow row = sheet2.createRow(i);
            OpenProduct op = companys2.get(i);
            if (!hashSet.contains(op.getCompanyId())) {
                String tenanId = getTenanId(op.getCompanyId());
                row.createCell(1).setCellValue(op.getCompanyName());
                //根据租户id查询项目量和自助公告量
                row.createCell(2).setCellValue(countProjectByTenantId(tenanId, beginDate, endDate));
                row.createCell(3).setCellValue(countNoticeByTenantId(tenanId, beginDate, endDate));
            }
        }

        XSSFSheet sheet3 = workbook.createSheet("国内标用户");
        companys = findCompanys(null, 1);
        //根据公司id查询公司租户id
        for (int i = 0; i < companys.size(); i++) {
            XSSFRow row = sheet3.createRow(i);
            OpenProduct op = companys.get(i);
            String tenanId = getTenanId(op.getCompanyId());
            row.createCell(1).setCellValue(op.getCompanyName());
            //根据租户id查询项目量和自助公告量
            row.createCell(2).setCellValue(countProjectByTenantId(tenanId, beginDate, endDate));
            row.createCell(3).setCellValue(countNoticeByTenantId(tenanId, beginDate, endDate));
        }
        String fileName = filePath + this.beginDate + "-" + this.endDate + "项目统计.xlsx";
        //导出到excel中
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(fileName));
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param isPay    是否付费
     * @param userType 用户类型：1=国内机构;2=国际机构;12=采购商
     * @return
     */
    private List<OpenProduct> findCompanys(Boolean isPay, Integer userType) {
        List<OpenProduct> companies = null;
        Cnd param = Cnd.where("IS_DELETE", "=", 0).and("status", "=", 1);
        if (userType != null) {
            param.and("user_type", "=", userType);
        }
        if (isPay != null) {
            if (isPay) {
                param.and("platform", ">", 0);
            } else {
                param.and("platform", "=", 0);
            }
        }

        companies = nblUserDao.query(OpenProduct.class, param);
        return companies;
    }

    private String getTenanId(Long oldCompanyId) {
        List<User> userList = nblUserDao.query(User.class, Cnd.where("OLD_COMP_ID", "=", oldCompanyId).and("IS_SUBJECT", "=", "0"));
        if (userList != null && userList.size() > 0) {
            return userList.get(0).getTenantId();
        }
        return "";
    }

    private Integer countProjectByTenantId(String tenantId, String startDate, String endDate) {
        String s = new String("select count(1) from project where TENANT_ID = @tenantId and create_time > @startDate and create_time < @endDate and SYSTEM_STATUS = 1");
        Sql sql = Sqls.create(s);
        sql.setParam("tenantId", tenantId);
        sql.setParam("startDate", startDate);
        sql.setParam("endDate", endDate);
        sql.setCallback(Sqls.callback.integer());
        biddingDao.execute(sql);
        return sql.getInt();
    }

    private Integer countNoticeByTenantId(String tenantId, String startDate, String endDate) {
        String s = new String("select count(1) from notice where DOC_SOURCE = 131 AND TENANT_ID = @tenantId and create_time > @startDate and create_time < @endDate and SYSTEM_STATUS = 1");
        Sql sql = Sqls.create(s);
        sql.setParam("tenantId", tenantId);
        sql.setParam("startDate", startDate);
        sql.setParam("endDate", endDate);
        sql.setCallback(Sqls.callback.integer());
        oaDao.execute(sql);
        return sql.getInt();
    }


}
