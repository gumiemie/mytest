package cn.bidlink.nbl.statisticsData;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import utils.DBUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用于标书费使用情况的数据统计.
 * @date 2017/8/7 10:00$
 */
public class StatisticsBidorderData {

    @Test
    public void execute() throws Exception {
        DBUtils dbUtils = new DBUtils();
        NutDao oaDao = dbUtils.getOaDao();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginTimeString = "2017-08-07 00:00:00";
        String endTimeString = "2017-12-31 23:59:59";

        DateTime beginDate = new DateTime(simpleDateFormat.parse(beginTimeString));
        DateTime endDate = new DateTime(simpleDateFormat.parse(endTimeString));
        int weeks = (endDate.getDayOfYear() - beginDate.getDayOfYear()) / 7;

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("111");
        Row r1 = sheet.createRow(1);
        Row r2 = sheet.createRow(2);
        Row r3 = sheet.createRow(3);
        Row r4 = sheet.createRow(4);


        for (int i = 0; i <= weeks; i++) {
            endTimeString = simpleDateFormat.format(new DateTime(simpleDateFormat.parse(beginTimeString).getTime()).plusWeeks(1).toDate());

            String cndString = " WHERE CREATE_TIME BETWEEN " + "'" + beginTimeString + "'" + " AND " + "'" + endTimeString + "'" + " AND IS_TEST = 0";
            Sql sql1 = Sqls.create("select count(DISTINCT TENANT_ID) count FROM bid_order" + cndString);
            sql1.setCallback(new SqlCallback() {
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                    int count = 0;
                    while (rs.next()) {
                        count = rs.getInt("count");
                    }
                    return count;
                }
            });
            oaDao.execute(sql1);
            r1.createCell(i).setCellValue(sql1.getInt());

            Sql sql2 = Sqls.create("select count(DISTINCT BIDDER_ID) count FROM bid_order " + cndString + " AND IS_MANUAL =1");
            sql2.setCallback(new SqlCallback() {
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                    int count = 0;
                    while (rs.next()) {
                        count = rs.getInt("count");
                    }
                    return count;
                }
            });
            oaDao.execute(sql2);
            r2.createCell(i).setCellValue(sql2.getInt());

            Sql sql3 = Sqls.create("select count(DISTINCT PACKAGE_GROUP_ID)+count(DISTINCT NOTICE_ID) count FROM bid_order " + cndString);
            sql3.setCallback(new SqlCallback() {
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                    int count = 0;
                    while (rs.next()) {
                        count = rs.getInt("count");
                    }
                    return count;
                }
            });
            oaDao.execute(sql3);
            r3.createCell(i).setCellValue(sql3.getInt());


            Sql sql4 = Sqls.create("select count(DISTINCT BIDDER_ID) count FROM bid_order " + cndString + " AND IS_MANUAL !=1");
            sql4.setCallback(new SqlCallback() {
                public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                    int count = 0;
                    while (rs.next()) {
                        count = rs.getInt("count");
                    }
                    return count;
                }
            });
            oaDao.execute(sql4);
            r4.createCell(i).setCellValue(sql4.getInt());

            beginTimeString = simpleDateFormat.format(new DateTime(simpleDateFormat.parse(beginTimeString).getTime()).plusWeeks(1).toDate());
        }

        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\标书费运营统计.xlsx"));
        workbook.write(fos);
        fos.flush();
        fos.close();

    }
}
