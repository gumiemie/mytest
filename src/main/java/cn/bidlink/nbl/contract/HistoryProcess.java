package cn.bidlink.nbl.contract;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.util.List;

/**
 * 合同历史数据处理
 */
public class HistoryProcess {

    private DBUtils dbUtils = new DBUtils();

    @Test
    public void execute() {
        NutDao testOaDao = dbUtils.getOaDao();
        NutDao nblUserTestDao = dbUtils.getNblUserDao();
        List<Record> contracts = testOaDao.query("contract", null);
        for (Record contract : contracts) {
            String contractId = contract.getString("id");
            int contractType = contract.getInt("type");
            Long firstPartyId = Long.valueOf(contract.getString("first_party_id"));
            String firstPartyLoginName = "";
            List<Record> subjectUsers = nblUserTestDao.query("user", Cnd.where("OLD_COMP_ID", "=", firstPartyId).and("IS_SUBJECT", "=", 0));
            Record subjectUser = subjectUsers.get(0);
            int type = subjectUser.getInt("type");
            if (type == 12) {
                firstPartyLoginName = subjectUser.getString("login_name");
            } else {
                List<Record> user = nblUserTestDao.query("user", Cnd.where("id", "=", contract.getString("create_user_id")));
                firstPartyLoginName = user.get(0).getString("login_name");
            }

            Long secondPartyId = Long.valueOf(contract.getString("second_party_id"));
            List<Record> secondPartysubjectUsers = nblUserTestDao.query("user", Cnd.where("OLD_COMP_ID", "=", secondPartyId).and("IS_SUBJECT", "=", 0));
            String secondPartyLoginName = secondPartysubjectUsers.get(0).getString("login_name");

            //更新contract表
            if (StringUtils.isBlank(contract.getString("first_party_login_name"))) {
                testOaDao.update("contract", Chain.make("first_party_login_name", firstPartyLoginName), Cnd.where("id", "=", contractId));
            }
            if (StringUtils.isBlank(contract.getString("second_party_login_name"))) {
                testOaDao.update("contract", Chain.make("second_party_login_name", secondPartyLoginName), Cnd.where("id", "=", contractId));
            }

            //更新contract_fulfillment_record表
            List<Record> records = testOaDao.query("contract_fulfillment_record", Cnd.where("contract_id", "=", contractId));
            if (records.size() > 0) {
                for (Record record : records) {
                    int fulfillmentUserType = record.getInt("fulfillment_user_type");
                    if (fulfillmentUserType == 13) {
                        testOaDao.update("contract_fulfillment_record", Chain.make("fulfillment_user_login_name", secondPartyLoginName), Cnd.where("id", "=", record.getString("id")));
                    } else if (fulfillmentUserType == 12) {
                        if (contractType == 1) {
                            testOaDao.update("contract_fulfillment_record", Chain.make("fulfillment_user_login_name", secondPartyLoginName), Cnd.where("id", "=", record.getString("id")));
                        } else {
                            testOaDao.update("contract_fulfillment_record", Chain.make("fulfillment_user_login_name", firstPartyLoginName), Cnd.where("id", "=", record.getString("id")));
                        }
                    } else {
                        testOaDao.update("contract_fulfillment_record", Chain.make("fulfillment_user_login_name", firstPartyLoginName), Cnd.where("id", "=", record.getString("id")));
                    }
                }
            }

        }
    }

    @Test
    public void execute2() {
        NutDao testOaDao = dbUtils.getOaDao();
        NutDao nblUserTestDao = dbUtils.getNblUserDao();

        List<Record> records = testOaDao.query("contract_fulfillment_record", null);
        for (Record record : records) {
            List<Record> users = nblUserTestDao.query("user", Cnd.where("id", "=", record.getString("create_user_id")));
            Record user = users.get(0);
            testOaDao.update("contract_fulfillment_record", Chain.make("create_user_type", user.getInt("type") == 1 ? 2 : user.getInt("type")).add("create_user_login_name", user.getString("login_name")), Cnd.where("id", "=", record.getString("id")));
        }


    }


}
