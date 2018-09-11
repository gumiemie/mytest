import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 处理资质认证的数据问题
 * @date 2016/12/8 9:38$
 */
public class ProcessQualificationData {
    private static NutDao dao;

    //初始化数据源
    static {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:oracle:thin:@211.151.182.199:1521:import");
        druidDataSource.setUsername("unireg");
        druidDataSource.setPassword("BidUnireg");
        dao = new NutDao(druidDataSource);
    }

    @Test
    /**
     * @Description: 处理资质状态更新
     * @param: []
     * @return: void
     * @Date: 2016/12/16 9:35
     */
    public void execute1() {
        //更新资质状态 参数为登录名
        updateQualificationStatus("hebeihenglikongtiao");
    }

    @Test
    public void execute2() {
        //信息缺失补充,参数分别为用户名,注册号,地址,法人姓名,注册资金(万),注册资金类别(1=RMB,2=美元)
        replenishData("YUECAI69948", "91130184MA08WEB4XK", "新乐市马头铺镇马石桥村北", "王同良", 5000, 1);
    }

    public void replenishData(String loginName, String regNumber, String address, String bossName, Integer fund, int fundType) {

        List<Record> users = dao.query("T_REG_USER", Cnd.where("LOGIN_NAME", "=", loginName));
        BigDecimal companyId = null;
        if (users != null && users.size() > 0) {
            Record record = users.get(0);
            companyId = (BigDecimal) record.get("COMPANY_ID");
        }
        if (companyId != null) {
            List<Record> quas = dao.query("T_QUA_QUALIFICATION_INFO", Cnd.where("COMPANY_ID", "=", companyId).and("NAME", "=", "企业法人营业执照").and("AUTHEN_STATUS", "=", "1"));
            if (quas != null && quas.size() > 0) {
                Record tQua = quas.get(0);
                BigDecimal id = (BigDecimal) tQua.get("id");
                List<Record> tInfos = dao.query("T_QUA_ENTERPRISE_INFO", Cnd.where("QUALIFICATION_INFO_ID", "=", id));
                if (tInfos != null && tInfos.size() > 0) {//有记录,修改值

                    dao.update("T_QUA_ENTERPRISE_INFO", Chain.make("COMPANY_NAME", tQua.get("COMPANY_NAME")).add("REG_NUMBER", regNumber).add("ADDRESS", address)
                            .add("BOSS_NAME", bossName).add("FUND", fund), Cnd.where("ID", "=", tInfos.get(0).get("ID")));

                } else {//无记录,新增
                    // List<Record> query = dao.query("T_QUA_ENTERPRISE_INFO", Cnd.orderBy());
                    // int id = query.get(0).getInt("ID");
                    //TODO
                    dao.insert("T_QUA_ENTERPRISE_INFO", Chain.make("id", (id.subtract(new BigDecimal(1)))).add("COMPANY_NAME", tQua.get("COMPANY_NAME")).add("USER_ID", tQua.get("USER_ID"))
                            .add("REG_NUMBER", regNumber).add("ADDRESS", address).add("BOSS_NAME", bossName).add("FUND", fund).add("FUNDUNIT", fundType)
                            .add("QUALIFICATION_INFO_ID", id).add("PIC_ID", tQua.get("PIC_ID")));
                }
            }
        }


    }


    /**
     * @Description: 老OMS和资质认证服务之前通信异常造成审核不通过的资质状态未改变问题
     * @param: []
     * @return: void
     * @Date: 2016/12/8 9:48
     */
    public boolean updateQualificationStatus(String loginName) {

        if (StringUtils.isBlank(loginName)) return false;

        List<Record> users = dao.query("T_REG_USER", Cnd.where("LOGIN_NAME", "=", loginName));
        BigDecimal companyId = null;

        if (users != null && users.size() > 0) {
            companyId = (BigDecimal) users.get(0).get("COMPANY_ID");
            List<Record> quas = dao.query("T_QUA_QUALIFICATION_INFO", Cnd.where("COMPANY_ID", "=", companyId).and("NAME", "=", "企业法人营业执照"));

            if (quas != null && quas.size() > 0) {
                BigDecimal quaId = (BigDecimal) quas.get(0).get("ID");
                int updateRecords = dao.update("T_QUA_QUALIFICATION_INFO", Chain.make("AUTHEN_STATUS", 3), Cnd.where("ID", "=", quaId));
                if (updateRecords < 1) {
                    System.out.println("更新状态失败!");
                } else {
                    List<Record> allQuas = dao.query("T_QUA_QUALIFICATION_INFO", Cnd.where("COMPANY_ID", "=", companyId));
                    String quaIdParam = "";
                    if (allQuas != null && allQuas.size() > 0) {
                        for (Record record : allQuas) {
                            quaIdParam = "," + record.get("ID") + quaIdParam;
                        }
                    }
                    quaIdParam = quaIdParam.substring(1);
                    List<Record> quaInfoIds = dao.query("T_QUA_AUTHSTATE_INFO", Cnd.where("COMPANY_ID", "=", companyId).and("QUALIFICATION_INFO_ID", "NOT IN", quaIdParam));
                    String quaInfoIdsParam = "";
                    if (quaInfoIds != null && quaInfoIds.size() > 0) {
                        for (Record record : quaInfoIds) {
                            quaInfoIdsParam = "," + record.get("ID") + quaInfoIdsParam;
                        }
                        quaInfoIdsParam = quaInfoIdsParam.substring(1);
                        int clearResult = dao.clear("T_QUA_AUTHSTATE_INFO", Cnd.where("ID", "IN", quaInfoIdsParam));
                        if (clearResult == quaInfoIds.size()) {
                            System.out.print("删除认证审核记录成功!");
                        }
                    }
                }
            } else {
                System.out.println("当前companyID不存在对应的资质纪录!");
            }
        } else {
            System.out.println("未查询到当前用户");
            return false;
        }
        return true;
    }


}
