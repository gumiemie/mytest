package cn.bidlink.nbl.shjd;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import cn.bidlink.nbl.shjd.model.Message;
import utils.DBUtils;

import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 清除某用户某时间点之前的消息记录
 * @date 2017/8/11 16:02$
 */
public class ClearMessage {

    @Test
    public void execute() {
        //设置用户名,及时间点,必须要慎重.
        String loginName = "zw";
        String dateTime = "2018-08-01";

        DBUtils dbUtils = new DBUtils();
        NutDao shjdDao = dbUtils.getShjdDao();

        String userid = "";
        String sqlString = "";
        //1.清除所有帐号的消息
        if (loginName.equals("all")) {
            sqlString = "delete from bm_message where create_time < " + dateTime;
            Sql sql = Sqls.create(sqlString);
            shjdDao.execute(sql);
            return;
        }

        //2.1查用户id
        List<Record> users = shjdDao.query("mgt_userinfo", Cnd.where("loginphone", "=", loginName));
        if (users != null && users.size() > 0) {
            userid = users.get(0).getString("userid");
        } else {
            System.out.println("未查询到该用户.");
        }
        //2.2查询此用户与别的用户共享的消息.
        List<Message> records = shjdDao.query(Message.class, Cnd.where("type", "=", 1).and("target_addr", "like", "%" + userid + "%"));
        if (records != null && records.size() > 0) {
            //2.3更新或删除记录.
            for (Message record : records) {
                String target_addr = record.gettargetAddr();
                String replace = target_addr.replace("," + userid, "");
                replace = replace.replace(userid + ",", "");
                replace = replace.replace(userid, "");
                if (StringUtils.isBlank(replace) || replace.equals(",")) {
                    shjdDao.delete(record);
                } else {
                    record.settargetAddr(replace);
                    shjdDao.update(record);
                }
            }
        }
    }
}
