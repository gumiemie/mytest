package cn.bidlink.nbl.bidcall;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/9/29 13:58$
 */
public class BidCallFileProcess {

    private static DBUtils dbUtils = new DBUtils();

    /**
     * 处理bidCallFiles,建立与 packageId之间的关系
     */
    @Test
    public void execute(){
        NutDao testBiddingDao = dbUtils.getTestBiddingDao();
        List<Record> packageGroups = testBiddingDao.query("package_group", null);
        for (Record packageGroup:packageGroups){
            String packageGroupId = packageGroup.getString("id");
            if (StringUtils.isBlank(packageGroupId)){
                continue;
            }
            List<Record> packages = testBiddingDao.query("packages", Cnd.where("package_group_id", "=", packageGroupId));
            if (packages==null||packages.size()<1){
                continue;
            }
            List<BidCallFile> bidCallFiles = testBiddingDao.query(BidCallFile.class, Cnd.where("packageGroupId", "=", packageGroupId).and("type","=",1));
            for (int i=0;i<packages.size();i++){
                Record packageTemp = packages.get(i);
                if (i<1){
                    for (BidCallFile bidCallFile:bidCallFiles){
                        bidCallFile.setPackageId(packageTemp.getString("id"));
                        testBiddingDao.update(bidCallFile);
                    }
                }else {
                    for (BidCallFile bidCallFile:bidCallFiles){
                        bidCallFile.setId(UUID.randomUUID().toString().replaceAll("-",""));
                        bidCallFile.setPackageId(packageTemp.getString("id"));
                        testBiddingDao.insert(bidCallFile);
                    }
                }
            }
        }
    }

}
