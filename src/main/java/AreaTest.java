import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.nutz.dao.impl.NutDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DBUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 取得所有的省
 * @date 2016/11/10 14:57$
 */
public class AreaTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void processBidOrderStatus() {

        DBUtils dbUtils = new DBUtils();
        NutDao testBiddingDao = dbUtils.getTestBiddingDao();


    }

    @Test
    public void execute1() {
        String content = "";
        content = content.replaceAll("&", "&amp;");
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
        if (content.length() > 65535) {
            Pattern pattern = Pattern.compile("&\\w+(?!;)");
            String endString = content.substring(65494, 65500);
            Matcher matcher = pattern.matcher(endString);
            if (matcher.find()) {
                content = content.substring(0, 65494 + endString.lastIndexOf("&"));
            } else {
                content = content.substring(0, 65500);
            }
            content = content + "请到必联网查看详情,http://www.ebnew.com";
        }
        System.out.println(content);
    }

    @Test
    public void execute() throws Exception {
        for (int i = 0; i < 300; i++) {
            System.out.println(generateRandom(20));
        }
    }

    /**
     * 生成随机数
     *
     * @param digits
     * @return
     */
    private String generateRandom(int digits) {
        BigDecimal bigDecimal = new BigDecimal(Math.random() * Math.pow(10, (digits - 12) > 3 ? (digits - 12) : 3));
        StringBuffer sb = new StringBuffer().append(new Date().getTime()).append(bigDecimal.longValue());
        while (sb.length() < digits) {
            sb.append("0");
        }
        return sb.length() > digits ? sb.substring(sb.length() - digits) : sb.toString();
    }


    @Test
    public void execute2() throws Exception {
        String solrServceUrl1 = "http://211.151.208.171:8800/solr/searchbu/";
        String solrServceUrl2 = "http://211.151.208.173:8800/solr/searchbu/";

        LBHttpSolrClient solrClient = new LBHttpSolrClient.Builder().withBaseSolrUrls(solrServceUrl1, solrServceUrl2).build();
        solrClient.setParser(new XMLResponseParser());
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("title");
        solrQuery.addHighlightField("content");
        // 设置高亮关键字的前缀和后缀
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");

        solrQuery.setStart(0);
        solrQuery.setRows(50);
        // SolrQuery.SortClause sortClause = new SolrQuery.SortClause("docSource",SolrQuery.ORDER.desc);
        solrQuery.setSort("docSource desc,pubDate", SolrQuery.ORDER.desc);
        solrQuery.setQuery("招标");
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList results = response.getResults();
        SolrDocument solrDocument = results.get(0);
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
    }

    /*private List<ItemSolr> queryItemSolrByKeyword(String keywords, Integer page, Integer rows)
            throws Exception {
        // 创建查询对象,查询条件就是 title:小米
        SolrQuery solrQuery = new SolrQuery("title:" + keywords);

        // 从哪一条开始查Math.max(page, 1):确保page是大于等于1的
        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
        solrQuery.setRows(rows);

        // 设置查询条件是否高亮
        boolean flag = !StringUtils.equals(keywords, "*") && !StringUtils.isBlank(keywords);

        // 设置高亮
        if (flag) {
            // 设置开启高亮
            solrQuery.setHighlight(true);
            // 设置哪个字段开启高亮
            solrQuery.addHighlightField("title");
            // 设置高亮关键字的前缀和后缀
            solrQuery.setHighlightSimplePre("<em>");
            solrQuery.setHighlightSimplePost("</em>");
        }

        // 执行查询
        QueryResponse response = this.httpSolrServer.query(solrQuery);
        // 获取查询结果
        // id=1468290083713, title=小米6快出来了,
        List<ItemSolr> list = response.getBeans(ItemSolr.class);

        if (flag) {
            // {1468290083713={title=[<em>小米</em>6快出来了]},（这个就是遍历的entry)
            // 1468292150289={title=[<em>小米</em>5s快出来了]}}
            Map<String, Map<String, List<String>>> map = response.getHighlighting();
            for (ItemSolr itemSolr : list) {
                for (Map.Entry<String, Map<String, List<String>>> entry : map.entrySet()) {
                    // 判断返回值的id和高亮的id是否一致，如果一直则设置高亮数据
                    if (StringUtils.equals(itemSolr.getId(), entry.getKey())) {
                        // [<em>小米</em>5s快出来了] 就是list
                        List<String> hlist = entry.getValue().get("title");
                        // 把高亮数据设置到结果中
                        itemSolr.setTitle(StringUtils.join(hlist, ""));

                    }

                }
            }
        }

        return list;*/

}

