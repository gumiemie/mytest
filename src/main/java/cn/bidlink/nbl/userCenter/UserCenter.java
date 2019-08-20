package cn.bidlink.nbl.userCenter;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import utils.DBUtils;

import java.util.HashSet;
import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description solr中的数据缺失, 用于查询哪些数据少了。
 * @date 2019-08-12 11:01
 */
public class UserCenter {

    private SolrClient solrClient;
    private Integer pageSize = 10000;

    @Test
    public void execute() {

        NutDao centerDao = new DBUtils().getCenterDao();

        Sql sql1 = Sqls.create("select count(*) from t_reg_company");
        sql1.setCallback(Sqls.callback.integer());
        centerDao.execute(sql1);
        Integer aLong = sql1.getInt();
        Integer totalPage = aLong % pageSize == 0 ? aLong / pageSize : aLong / pageSize + 1;
        Sql sql2 = Sqls.create("select id from t_reg_company");
        sql2.setCallback(Sqls.callback.strList());
        HashSet<Long> ids = new HashSet<>();
        Pager pager = new Pager();
        pager.setPageSize(pageSize);
        sql2.setPager(pager);

        for (int i = 0; i < totalPage; i++) {
            pager.setPageNumber(i + 1);
            sql2.setSourceSql("select id from t_reg_company");
            sql2 = centerDao.execute(sql2);
            List<Long> idlist = sql2.getList(Long.class);
            ids.addAll(idlist);
        }

        initializeCloudSolrClient();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRows(pageSize);
        for (int i = 0; i < totalPage; i++) {
            solrQuery.setStart(i * pageSize);
            solrQuery.setFields("id");
            try {
                QueryResponse response = solrClient.query(solrQuery);
                SolrDocumentList results = response.getResults();
                results.forEach(id -> {
                    Long longId = Long.valueOf(id.getFieldValue("id").toString());
                    if (ids.contains(longId)) {
                        ids.remove(longId);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ids.forEach(id -> System.out.println(id.longValue()));

    }

    private void initializeCloudSolrClient() {
        solrClient = new HttpSolrClient("http://211.151.208.171:8983/solr/#/searchcorp/");
    }


}
