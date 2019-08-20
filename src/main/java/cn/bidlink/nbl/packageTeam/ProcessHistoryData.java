package cn.bidlink.nbl.packageTeam;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 处理packageTeam及packageTeamMember的历史数据.专家portal 十月份上线.
 * @date 2017/10/23 16:38$
 */
public class ProcessHistoryData {
    DBUtils dbUtils = new DBUtils();
    NutDao testBiddingDao = dbUtils.getBiddingDao();
    NutDao testExpertDao = dbUtils.getExpertDao();

    @Test
    public void execute2() {
        //根据committee数据初始化packageTeam以及packageTeamMember的数据
        List<Record> committees = testBiddingDao.query("cn/bidlink/nbl/committee", Cnd.where("source", "=", "1"));
        for (Record committee : committees) {
            String packageId = committee.getString("package_id");
            String type = committee.getString("type");
            String packageGroupId = committee.getString("PACKAGE_GROUP_ID");
            PackageTeam params = null;
            if (StringUtils.isNotBlank(packageId)) {
                List<PackageTeam> packageTeams = testBiddingDao.query(PackageTeam.class, Cnd.where("package_id", "=", packageId).and("type", "=", type));
                if (packageTeams != null && packageTeams.size() > 0) {
                    params = packageTeams.get(0);
                }
            } else {
                if (StringUtils.isNotBlank(packageGroupId)) {
                    List<PackageTeam> packageTeams = testBiddingDao.query(PackageTeam.class, Cnd.where("PACKAGE_GROUP_ID", "=", packageGroupId).and("type", "=", type));
                    if (packageTeams != null && packageTeams.size() > 0) {
                        params = packageTeams.get(0);
                    }
                }
            }
            if (params == null) {
                params = new PackageTeam();
                params.setId(UUID.randomUUID().toString().replace("-", ""));
                params.setType(type);
                params.setCreateType(1);
                params.setPackageId(packageId);
                params.setPackageGroupId(packageGroupId);
                params.setWorkTime((Date) committee.get("EVAL_TIME"));
                params.setWorkAddress(committee.getString("EVAL_ADDRESS"));
                params.setWorkDuration((Float) committee.get("EVAL_DURATION"));
                params.setCreateTime((Date) committee.get("CREATE_TIME"));
                params.setExpertCount(committee.getInt("EXPERT_COUNT"));
                params.setTenderRepresentCount(committee.getInt("TENDER_REPRESENT_COUNT"));
                params.setCreateUserId(committee.getString("CREATE_USER_ID"));
                params.setUpdateUserId(committee.getString("UPDATE_USER_ID"));
                params.setUpdateTime((Date) committee.get("UPDATE_TIME"));
                params.setOrgCode(committee.get("org_code") != null ? Long.valueOf(committee.get("org_code").toString()) : null);
                params.setTenantId(committee.getString("TENANT_ID"));
                params.setIsTest(committee.getInt("is_test"));
                params.setSystemStatus(committee.getInt("SYSTEM_STATUS"));
                testBiddingDao.insert(params);

                String id = committee.getString("id");
                List<Record> experts = testBiddingDao.query("committee_expert", Cnd.where("COMMITTEE_ID", "=", id).and("status", "=", "1"));
                List<Record> represents = testBiddingDao.query("committee_tender_represent", Cnd.where("COMMITTEE_ID", "=", id));
                if (experts != null && experts.size() > 0) {
                    for (Record expert : experts) {
                        PackageTeamMember member = new PackageTeamMember();
                        member.setId(UUID.randomUUID().toString().replace("-", ""));
                        member.setPackageTeamId(params.getId());
                        member.setUserType("expert");
                        member.setCreateType(1);
                        List<Record> rs = testExpertDao.query("exp_expert_info", Cnd.where("id", "=", expert.getString("EXPERT_INFO_ID")));
                        boolean flag = rs != null && rs.size() > 0;
                        member.setUserId(flag ? rs.get(0).getString("CENTER_USER_ID") : "");
                        member.setUserName(expert.getString("name"));
                        member.setUserPhone(expert.getString("MOBILE_PHONE"));
                        member.setUserEmail(expert.getString("EMAIL"));
                        member.setWorkUnit(expert.getString("WORK_UNIT"));
                        member.setCertificateNum(flag ? rs.get(0).getString("CERTIFICATE_NUM") : "");
                        member.setCreateTime((Date) expert.get("CREATE_TIME"));
                        member.setCreateUserId(expert.getString("CREATE_USER_ID"));
                        member.setUpdateUserId(expert.getString("UPDATE_USER_ID"));
                        member.setUpdateTime((Date) expert.get("UPDATE_TIME"));
                        member.setOrgCode(expert.get("org_code") != null ? Long.valueOf(expert.get("org_code").toString()) : null);
                        member.setTenantId(expert.getString("TENANT_ID"));
                        member.setIsTest( expert.getInt("is_test"));
                        testBiddingDao.insert(member);
                    }
                }
                if (represents != null && represents.size() > 0) {
                    for (Record represent : represents) {
                        PackageTeamMember member = new PackageTeamMember();
                        member.setId(UUID.randomUUID().toString().replace("-", ""));
                        member.setPackageTeamId(params.getId());
                        member.setUserType("tendRepresent");
                        member.setCreateType(1);
                        member.setUserId(represent.getString("USER_ID"));
                        member.setUserName(represent.getString("name"));
                        member.setUserPhone(represent.getString("MOBILE_PHONE"));
                        member.setUserEmail(represent.getString("EMAIL"));
                        member.setWorkUnit(represent.getString("WORK_UNIT"));
                        member.setCreateTime((Date) represent.get("CREATE_TIME"));
                        member.setCreateUserId(represent.getString("CREATE_USER_ID"));
                        member.setUpdateUserId(represent.getString("UPDATE_USER_ID"));
                        member.setUpdateTime((Date) represent.get("UPDATE_TIME"));
                        member.setOrgCode(represent.get("org_code") != null ? Long.valueOf(represent.get("org_code").toString()) : null);
                        member.setTenantId(represent.getString("TENANT_ID"));
                        member.setIsTest(represent.getInt("is_test"));
                        testBiddingDao.insert(member);
                    }
                }
            }
        }

    }

    @Test
    public void execute3() {
        //把room及roomUser中的数据同步到packageTeam及packageTeamMember
        List<Room> rooms = testBiddingDao.query(Room.class, null);
        for (Room room : rooms) {
            String packageId = room.getPackageId();
            String packageGroupId = room.getPackageGroupId();
            List<PackageTeam> pts = null;
            if (StringUtils.isNotBlank(packageId)) {
                pts = testBiddingDao.query(PackageTeam.class, Cnd.where("package_id", "=", packageId).and("type", "=", "012"));
            } else {
                if (StringUtils.isNotBlank(packageGroupId)) {
                    pts = testBiddingDao.query(PackageTeam.class, Cnd.where("package_group_id", "=", packageGroupId).and("type", "=", "012"));
                }
            }
            PackageTeam params = null;
            if (pts == null || pts.size() == 0) {
                params = new PackageTeam();
                params.setId(UUID.randomUUID().toString().replace("-", ""));
                params.setType("012");
                params.setCreateType(2);
                params.setPackageId(packageId);
                params.setPackageGroupId(packageGroupId);
                List<Record> records = null;
                if (StringUtils.isNotBlank(packageId)) {
                    records = testBiddingDao.query("cn/bidlink/nbl/committee", Cnd.where("package_id", "=", packageId).and("type", "=", "012"));
                } else {
                    if (StringUtils.isNotBlank(packageGroupId)) {
                        records = testBiddingDao.query("cn/bidlink/nbl/committee", Cnd.where("package_group_id", "=", packageGroupId).and("type", "=", "012"));
                    }
                }
                if (records != null && records.size() > 0) {
                    Record committee = records.get(0);
                    params.setWorkTime((Date) committee.get("EVAL_TIME"));
                    params.setWorkAddress(committee.getString("EVAL_ADDRESS"));
                    params.setWorkDuration((Float) committee.get("EVAL_DURATION"));
                    params.setExpertCount(committee.getInt("EXPERT_COUNT"));
                    params.setTenderRepresentCount(committee.getInt("TENDER_REPRESENT_COUNT"));
                }
                params.setCreateTime(room.getCreateTime());
                params.setCreateUserId(room.getCreateUserId());
                params.setUpdateUserId(room.getUpdateUserId());
                params.setUpdateTime(room.getUpdateTime());
                params.setOrgCode(room.getOrgCode());
                params.setTenantId(room.getTenantId());
                params.setIsTest(room.getIsTest());
                params.setSystemStatus(room.getSystemStatus());
                testBiddingDao.insert(params);
            } else {
                params = pts.get(0);
            }

            List<RoomUser> roomUsers = testBiddingDao.query(RoomUser.class, Cnd.where("room_id", "=", room.getId()));
            if (roomUsers != null && roomUsers.size() > 0) {
                for (RoomUser roomUser : roomUsers) {
                    String packageTeamId = params.getId();
                    List<Record> members = testBiddingDao.query("package_team_member", Cnd.where("package_team_id", "=", packageTeamId).and("user_name", "=", roomUser.getUserName()).and("USER_PHONE", "=", roomUser.getUserPhone()));
                    if (members == null || members.size() == 0) {
                        PackageTeamMember member = new PackageTeamMember();
                        member.setId(UUID.randomUUID().toString().replace("-", ""));
                        member.setPackageTeamId(params.getId());
                        member.setUserType(roomUser.getUserType());
                        member.setCreateType(2);
                        member.setUserId(roomUser.getUserId());
                        member.setUserName(roomUser.getUserName());
                        member.setUserPhone(roomUser.getUserPhone());
                        member.setUserEmail(roomUser.getUserEmail());
                        member.setCreateTime(roomUser.getCreateTime());
                        member.setCreateUserId(roomUser.getCreateUserId());
                        member.setUpdateUserId(roomUser.getUpdateUserId());
                        member.setUpdateTime(roomUser.getUpdateTime());
                        member.setOrgCode(roomUser.getOrgCode());
                        member.setTenantId(roomUser.getTenantId());
                        member.setIsTest(roomUser.getIsTest());
                        testBiddingDao.insert(member);
                    }
                }
            }

        }
    }
}
