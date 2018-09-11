package oms;

import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/8/30 10:01$
 */
public class ProcessHonestTender {

    private static NutDao nblUserDao = new DBUtils().getNblUserDao();

    @Test
    public void execute() throws Exception {
        List<Record> openProducts = nblUserDao.query("open_product", Cnd.where("product_id", "!=", "9892e5fcc97f11e598bcfa163e84de1b").and("IS_DELETE", "=", 1));
        for (Record record : openProducts) {
            String endTime = record.getString("end_time");
            Date endTimeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
            String companyId = record.getString("COMPANY_ID");
            String startTime = record.getString("start_time");
            if (endTimeDate.after(new Date())) {
                List<Record> existCompanys = nblUserDao.query("honest_tender", Cnd.where("COMPANY_ID", "=", companyId));
                if (existCompanys != null && existCompanys.size() != 0) {
                    nblUserDao.update("honest_tender", Chain.make("is_open", "1").add("OPEN_TIME_BEGIN", startTime).add("OPEN_TIME_END", endTime), Cnd.where("COMPANY_ID", "=", companyId));
                } else {
                    List<Record> records = nblUserDao.query("user", Cnd.where("OLD_COMP_ID", "=", companyId));
                    nblUserDao.insert("honest_tender", Chain.make("id", UUID.randomUUID().toString().replaceAll("-", "")).add("LOGIN_NAME", records.get(0).getString("LOGIN_NAME"))
                            .add("COMPANY_NAME", record.getString("COMPANY_NAME")).add("COMPANY_ID", companyId).add("OPEN_TIME_BEGIN", startTime).add("IS_OPEN", 1)
                            .add("OPEN_TIME_END", endTime).add("CREATE_TIME", new Date()).add("IS_TEST", 0).add("SYSTEM_STATUS", 1));
                }
                System.out.println("已更新" + companyId + "---------" + endTime);
            } else {
                System.out.println("未更新" + companyId + "---------" + endTime);
            }

        }
    }


}
