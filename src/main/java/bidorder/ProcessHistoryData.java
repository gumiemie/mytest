package bidorder;

import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/9/1 13:36$
 */
public class ProcessHistoryData {
    DBUtils dbUtils = new DBUtils();
    NutDao biddingDao = dbUtils.getBiddingDao();
    NutDao oaDao = dbUtils.getOaDao();

    //三费与非招标流程上线时历史数据处理.
    @Test
    public void processPackageGroupName(){

        List<BidOrder> bidOrders = oaDao.query(BidOrder.class, null);
        Integer projectType;
        for (BidOrder order:bidOrders){
            projectType = order.getProjectType();
            if (projectType ==1){
                List<Record> records = biddingDao.query("package_group", Cnd.where("id", "=", order.getPackageGroupId()));
                if (records!=null&&records.size()>0){
                    order.setPackageGroupNumber(records.get(0).getString("PROJECT_NUMBER"));
                    order.setPackageGroupName(records.get(0).getString("BIDCALL_TITLE"));
                }
            }else if (projectType==0){
                List<Record> records = biddingDao.query("sync_international_project", Cnd.where("PROJECT_ID", "=", order.getPackageGroupId()));          if (records!=null&&records.size()>0){
                    order.setPackageGroupNumber(records.get(0).getString("PROJECT_NUMBER"));
                    order.setPackageGroupName(records.get(0).getString("PROJECT_NAME"));
                }
            }else if (projectType==2){
                List<Record> records = oaDao.query("notice", Cnd.where("id", "=", order.getNoticeId()));
                if (records!=null&&records.size()>0){
                    order.setPackageGroupNumber(records.get(0).getString("BID_CODE"));
                    order.setPackageGroupName(records.get(0).getString("DOC_TITLE"));
                }
            }else if (projectType>2){
                List<Record> records = biddingDao.query("packages", Cnd.where("id", "=", order.getPackageGroupId()));
                if (records!=null&&records.size()>0){
                    order.setPackageGroupNumber(records.get(0).getString("NUMBER"));
                    order.setPackageGroupName(records.get(0).getString("NAME"));
                }
            }

            oaDao.update(order);
        }
    }

    @Test
    public void processBidorderStatus() {

        Sql sql = Sqls.create("SELECT a.BIDDER_ID,b.PACKAGE_ID from bid_order a,bid_order_package b WHERE a.id=b.ORDER_ID AND a.STATUS = 1 and b.CHECK_STATUS = 1 AND PROJECT_TYPE = 1");
        sql.setCallback(new SqlCallback() {
            List list = new ArrayList();

            public List invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                while (rs.next()) {
                    HashMap<String, String> record = new HashMap<String, String>();
                    record.put(rs.getString(1), rs.getString(2));
                    list.add(record);
                }
                return list;
            }
        });
        oaDao.execute(sql);
        List<Map> maps = sql.getList(Map.class);
        List<Map> results = new ArrayList<Map>();
        if (maps != null && maps.size() > 0) {
            for (Map m1 : maps) {
                Set<String> set = m1.keySet();
                for (String bidderID : set) {
                    String packageId = (String) m1.get(bidderID);
                    List<Record> records = biddingDao.query("bid", Cnd.where("PACKAGE_ID", "=", packageId).and("BIDDER_ID", "=", bidderID));
                    if (records != null && records.size() > 0) {
                        biddingDao.update("bid", Chain.make("BID_ORDER_STATUS", 1), Cnd.where("id", "=", records.get(0).getString("ID")));
                    } else {
                        results.add(m1);
                    }
                }
            }
            //遍历未能查询到的信息
            for (Map m1 : results){
                Set<String> set = m1.keySet();
                for (String key : set) {
                    String value = (String) m1.get(key);
                    System.out.println(key+"------"+value);
                }

            }
        }
    }
}
