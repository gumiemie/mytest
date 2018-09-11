package guarantee;

import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 历史数据处理，新增了loginName字段
 * @date 2018/8/13 11:15$
 */
public class HistoryDataProcess {
    private DBUtils dbUtils = new DBUtils();
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 处理保证金
     * @throws Exception
     */
    @Test
    public void execute() throws Exception {
        final NutDao testOaDao = dbUtils.getOaDao();
        final NutDao nblUserTestDao = dbUtils.getNblUserDao();

        for (int i = 0; i < 10; i++) {
            final int tempi = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    List<Record> records = testOaDao.query("guarantee", Cnd.where("BIDDER_ID", " mod 10 = ", tempi).and("login_name", "is ", null));
                    System.out.println(records.size() + "-------------" + tempi);
                    if (records != null && records.size() > 0) {
                        for (Record record : records) {
                            List<Record> users = nblUserTestDao.query("user", Cnd.where("OLD_COMP_ID", "=", record.getString("BIDDER_ID")));
                            if (users != null && users.size() > 0) {
                                Record user = users.get(0);
                                testOaDao.update("guarantee", Chain.make("login_name", user.getString("LOGIN_NAME")), Cnd.where("id", "=", record.getString("id")));
                            }
                        }
                    }
                }
            });

        }
        Thread.sleep(100000000);
        System.out.println("end");
    }

    /**
     * 处理资源管理
     */
    @Test
    public void execute2(){
        final NutDao testOaDao = dbUtils.getOaDao();
        final NutDao nblUserTestDao = dbUtils.getNblUserDao();

        List<Record> records = testOaDao.query("resource_supplier", Cnd.where("login_name","is",null));
        for (Record record:records){
            List<Record> companys = nblUserTestDao.query("company", Cnd.where("ID", "=", record.getString("COMP_ID")));
            if (companys!=null&&companys.size()>0){
                List<Record> users = nblUserTestDao.query("user", Cnd.where("OLD_COMP_ID", "=", companys.get(0).getString("OLD_ID")));
                if (users!=null&&users.size()>0){
                    testOaDao.update("resource_supplier", Chain.make("login_name", users.get(0).getString("LOGIN_NAME")), Cnd.where("id", "=", record.getString("id")));
                }
            }
        }

    }

    /**
     * 处理标书费
     * @throws Exception
     */
    public void execute3() throws Exception {
        final NutDao testOaDao = dbUtils.getOaDao();
        final NutDao nblUserTestDao = dbUtils.getNblUserDao();

        for (int i = 0; i < 10; i++) {
            final int tempi = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    List<Record> records = testOaDao.query("bid_order", Cnd.where("BIDDER_ID", " mod 10 = ", tempi).and("login_name", "is ", null));
                    System.out.println(records.size() + "-------------" + tempi);
                    if (records != null && records.size() > 0) {
                        for (Record record : records) {
                            List<Record> users = nblUserTestDao.query("user", Cnd.where("OLD_COMP_ID", "=", record.getString("BIDDER_ID")));
                            if (users != null && users.size() > 0) {
                                Record user = users.get(0);
                                testOaDao.update("bid_order", Chain.make("login_name", user.getString("LOGIN_NAME")), Cnd.where("id", "=", record.getString("id")));
                            }
                        }
                    }
                }
            });

        }
        Thread.sleep(10000000);
        System.out.println("end");
    }

    /**
     * 处理服务费
     * @throws Exception
     */
    public void execute4() throws Exception {
        final NutDao testOaDao = dbUtils.getOaDao();
        final NutDao nblUserTestDao = dbUtils.getNblUserDao();

        for (int i = 0; i < 10; i++) {
            final int tempi = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    List<Record> records = testOaDao.query("charge", Cnd.where("SERVICE_ID", " mod 10 = ", tempi).and("login_name", "is ", null));
                    System.out.println(records.size() + "-------------" + tempi);
                    if (records != null && records.size() > 0) {
                        for (Record record : records) {
                            List<Record> users = nblUserTestDao.query("user", Cnd.where("OLD_COMP_ID", "=", record.getString("SERVICE_ID")));
                            if (users != null && users.size() > 0) {
                                Record user = users.get(0);
                                testOaDao.update("charge", Chain.make("login_name", user.getString("LOGIN_NAME")), Cnd.where("id", "=", record.getString("id")));
                            }
                        }
                    }
                }
            });

        }
        Thread.sleep(1000000);
        System.out.println("end");
    }

    /**
     * 处理服务费
     * @throws Exception
     */
    public void execute5() throws Exception {
        final NutDao testOaDao = dbUtils.getOaDao();
        final NutDao nblUserTestDao = dbUtils.getNblUserDao();

        for (int i = 0; i < 10; i++) {
            final int tempi = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    List<Record> records = testOaDao.query("resource_supplier", null);
                    System.out.println(records.size() + "-------------" + tempi);
                    for (Record record:records){
                        List<Record> users = nblUserTestDao.query("user", Cnd.where("COMPANY_ID", "=", record.getString("COMP_ID")));
                        if (users!=null&&users.size()>0){
                            testOaDao.update("resource_supplier", Chain.make("login_name", users.get(0).getString("LOGIN_NAME")), Cnd.where("id", "=", record.getString("id")));
                        }
                    }
                }
            });

        }
        Thread.sleep(1000000);
        System.out.println("end");
    }


}
