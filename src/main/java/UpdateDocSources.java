import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;

import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 商机订阅迭代--更新DocSources值
 * @date 2016/12/29 13:16$
 */
public class UpdateDocSources {

    private static NutDao dao;
    private static NutDao testDao;

    //初始化数据源
    static {
        DruidDataSource cnDataSource = new DruidDataSource();
        cnDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/hades?useUnicode=true");
        cnDataSource.setUsername("hades_wt");
        cnDataSource.setPassword("blw_hades_wt");
        dao = new NutDao(cnDataSource);
    }

    @Test
    public void execute(){

        List<Record> mail_custom = dao.query("mail_custom", null);
        int size = mail_custom.size();
        int totalNum = 0;
        for (Record record:mail_custom){
            String[] keywordses = record.get("keywords").toString().split(",");
            totalNum+=keywordses.length;
        }

        System.out.print("平均个数为:"+new Double(totalNum/size));

    }


}
