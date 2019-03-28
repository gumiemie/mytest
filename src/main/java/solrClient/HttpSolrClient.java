package solrClient;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2019/3/21 10:09$
 */
public class HttpSolrClient {


    private CloudSolrServer cloudSolrServer;
    private String zkHost = "10.4.0.163:2181,10.4.0.164:2181,10.4.0.165:2181";
    private String defaultCelection = "searchcorp";

    /*@Test
    public void execute() throws Exception{

        cloudSolrClient =  new CloudSolrClient.Builder().withZkHost(zkHost).build();
        cloudSolrClient.setDefaultCollection(defaultCelection);

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setStart(0);
        solrQuery.setRows(50);
        solrQuery.set("q","*:*");

        QueryResponse queryResponse = cloudSolrClient.query(solrQuery);
        SolrDocumentList results = queryResponse.getResults();

        long numFound = results.getNumFound();
        SolrDocument solrDocument = results.get(0);

        System.out.print(1111);

    }*/

    @Test
    public void execute1()throws Exception{
        cloudSolrServer = new CloudSolrServer(zkHost);
        cloudSolrServer.setDefaultCollection(defaultCelection);

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","*:*");
        solrQuery.setStart(0);
        solrQuery.setRows(50);

        QueryResponse response = cloudSolrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        if (results!=null&&results.size()>0){
            SolrDocument solrDocument = results.get(0);
        }

    }




}
