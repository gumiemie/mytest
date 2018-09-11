package busiopport;

import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 根据日志中的参数,从solr中查询数据.
 * @date 2017/2/21 9:51$
 */
public class QueryFromSolr {
    DBUtils dbUtils = new DBUtils();
    NutDao hadesDao = dbUtils.getHadesDao();
    @Test
    public void execute(){
        List<MailcustomDto> ms = hadesDao.query(MailcustomDto.class, Cnd.where("status","in","1,4").and("create_time","<","2017-09-24"));
        int resut = 0;
        for (MailcustomDto dto:ms){
            String mailNames = dto.getMailNames();
            String[] split = mailNames.split(",");
            resut+=split.length;
        }
        System.out.println("邮件数量是"+resut);
    }


    @Test
    public void haha(){
        Sql sql = Sqls.create("SELECT\n" +
                "\tDISTINCT(mail_names)\n" +
                "FROM\n" +
                "\tmail_custom_history a,\n" +
                "\tbo_crontab_email b\n" +
                "WHERE\n" +
                "\ta.user_login_name = b.user_login_name\n" +
                "AND a.CREATE_TIME BETWEEN '2017-09-24'\n" +
                "AND '2017-09-25'\n" +
                "AND b.CREATE_TIME BETWEEN '2017-09-24'\n" +
                "AND '2017-09-25'\n" +
                "AND b.is_send = 1");
        sql.setCallback(new SqlCallback() {
            List mails = new ArrayList();
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                while (rs.next()){
                    mails.add(rs.getString(1));
                }
                return mails;
            }
        });
        hadesDao.execute(sql);
        List<String> results = sql.getList(String.class);
        int result =0;
        for (String mails:results){
            String[] split = mails.split(",");
            result+=split.length;
        }

        System.out.println(result);
    }



}
