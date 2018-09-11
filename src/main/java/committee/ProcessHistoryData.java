package committee;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/9/29 17:02$
 */
public class ProcessHistoryData {

    @Test
    public void execute() {
        DBUtils dbUtils = new DBUtils();
        NutDao testBiddingDao = dbUtils.getBiddingDao();
        List<Committee> cs = testBiddingDao.query(Committee.class, Cnd.where("SYSTEM_STATUS", "=", "1").and("source","!=",0));
        if (cs != null && cs.size() > 0) {
            for (Committee committee : cs) {
                String packageId = committee.getPackageId();
                if (StringUtils.isNotBlank(packageId)) {
                    List<Record> records = testBiddingDao.query("packages", Cnd.where("id", "=", packageId));
                    if (records != null && records.size() > 0) {
                        if (StringUtils.isBlank(committee.getProjectName())) {
                            committee.setProjectName(records.get(0).getString("name"));
                        }
                        if (StringUtils.isBlank(committee.getProjectNumber())) {
                            committee.setProjectNumber(records.get(0).getString("number"));
                        }
                        if (committee.getSource()==null){
                            committee.setSource(1);
                        }
                        testBiddingDao.update(committee);
                    }
                } else {
                    String packageGroupId = committee.getPackageGroupId();
                    if (StringUtils.isNotBlank(packageGroupId)) {
                        List<Record> records = testBiddingDao.query("package_group", Cnd.where("id", "=", packageGroupId));
                        if (records != null && records.size() > 0) {
                            if (StringUtils.isBlank(committee.getProjectName())) {
                                committee.setProjectName(records.get(0).getString("bidcall_title"));
                            }
                            if (StringUtils.isBlank(committee.getProjectNumber())) {
                                committee.setProjectNumber(records.get(0).getString("project_number"));
                            }
                            if (committee.getSource()==null){
                                committee.setSource(1);
                            }
                            testBiddingDao.update(committee);
                        }
                    }
                }
            }
        }
    }

}
