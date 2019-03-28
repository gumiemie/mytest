package cn.bidlink.nbl.expert;

import com.alibaba.druid.pool.DruidDataSource;
import cn.bidlink.nbl.expert.model.ExpPartnership;
import cn.bidlink.nbl.expert.model.ExpertInfo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 将BMS专家库的专家同步到现有悦招nbl_expert库中
 * @date 2017/1/17 17:05$
 */
public class FetchExpertDataFromBMS {

    private static NutDao bmsDao;
    private static NutDao expertDao;
    private static NutDao expertTestDao;

    //初始化数据源
    static {
        //BMS数据库
        DruidDataSource bmsDruidDataSource = new DruidDataSource();
        bmsDruidDataSource.setUrl("jdbc:oracle:thin:@211.151.182.203:1521:newxcb");
        bmsDruidDataSource.setUsername("gyseledb");
        bmsDruidDataSource.setPassword("seledb@2017");
        bmsDao = new NutDao(bmsDruidDataSource);

        //nbl_expert正式库
        DruidDataSource expertDruidDataSource = new DruidDataSource();
        expertDruidDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/nbl_expert?useUnicode=true");
        expertDruidDataSource.setUsername("ebnewuser");
        expertDruidDataSource.setPassword("ebnewuserbid6link!@#$");
        expertDao = new NutDao(expertDruidDataSource);

        //nbl_expert测试库
        DruidDataSource expertTestDruidDataSource = new DruidDataSource();
        expertTestDruidDataSource.setUrl("jdbc:mysql://10.1.1.68:3306/nbl_expert?useUnicode=true");
        expertTestDruidDataSource.setUsername("jiaoyipt");
        expertTestDruidDataSource.setPassword("8kS#0(M");
        expertTestDao = new NutDao(expertTestDruidDataSource);

    }

    @Test
    public void execute() {

        //第一步:从BMS库中读取专家信息,参数是bidorg
        List<Record> list = fetchData("0646");

        //第二步,处理专家信息,将信息同步到nbl_expert库中
        processData(list);

    }


    /**
     * @Description: 根据租户ID来查询机构下所关联的专家并返回
     * @param: 租户ID
     * @return:
     * @Date: 2017/1/18 10:32
     */
    public List fetchData(String code) {
        List<Record> records = null;
        if (StringUtils.isNotBlank(code)) {
            //TODO 确认一下查询专家的 状态,BMS库中status为1的是正常状态,其它为异常状态.
            records = bmsDao.query("xcb.rsc_experts", Cnd.where("bidorg", "=", code));
        }
        return records;
    }


    /**
     * @Description: 将BMS返回的数据同步到expert库中
     * @param: BMS专家数据集合
     * @return:
     * @Date: 2017/1/20 16:19
     */
    public void processData(List<Record> params) {

        for (Record record : params) {
            //封装成专家信息对象:expertInfo 和 专家机构对应关系对象 expPartnership
            Map rs = record2Expert(record);

            //校验是否已经存在,同一专家的认定标准是:姓名和手机号相同(我猜的)
            ExpertInfo expertInfo = (ExpertInfo) rs.get("expertInfo");
            List<Record> exps = null;
            if (StringUtils.isNotBlank(expertInfo.getMobilePhone())){//如果手机号非空,才会校验专家是否存在.
                exps = expertDao.query("exp_expert_info", Cnd.where("name", "=", expertInfo.getName()).and("MOBILE_PHONE", "=", expertInfo.getMobilePhone()));
            }
            ExpPartnership expPartnership = (ExpPartnership) rs.get("expPartnership");
            //不存在时再存储
            if (exps == null || exps.size() == 0) {
                expertDao.insert(expertInfo);
                if (expPartnership!=null){
                    expertDao.insert(expPartnership);
                }
            }else {
                String expId = exps.get(0).getString("id");
                if (expPartnership != null) {
                    //查询合作关系
                    List<Record> ships = expertDao.query("exp_partnership", Cnd.where("EXPERT_ID", "=", expId).and("TENANT_ID", "=", expPartnership.getTenantId()));
                    //如果专家和机构的合作关系不存在,则存储合作关系.
                    if (ships == null || ships.size() == 0) {
                        expertDao.insert(expPartnership);
                    }
                }

            }

        }

    }


    /**
     * @Description: 转换为专家信息对象 ExpertInfo 和 机构专家合作关系对象:ExpPartnership
     * @param:
     * @return:
     * @Date: 2017/1/20 17:01
     */
    public Map record2Expert(Record record) {
        Map result = new HashMap<String, Object>();

        ExpertInfo expertInfo = new ExpertInfo();
        expertInfo.setOldId(((BigDecimal) record.get("id")).longValue());//老数据库的ID
        expertInfo.setId(UUID.randomUUID().toString().replace("-", ""));//专家ID
        expertInfo.setName(record.getString("name"));//专家姓名
        expertInfo.setSex(StringUtils.isBlank(record.getString("SEX")) ? 0 : Integer.valueOf(record.getString("SEX")));//性别
        expertInfo.setAppId("bms_expert");//专家来源
        expertInfo.setEmail(record.getString("email"));//邮箱
        expertInfo.setAddress(record.getString("ADDRESS"));//通讯地址
        expertInfo.setMobilePhone(getMobile(record));//手机号

        expertInfo.setCertificateType(processCardType(StringUtils.isNotBlank(record.getString("CARDTYPE")) ? record.getString("CARDTYPE") : "1"));//证件类型
        expertInfo.setCertificateNum(record.getString("CARDNUMBER"));//证件号码

        expertInfo.setGraduateSchool(record.getString("SCHOOLNAME"));//毕业院校
        expertInfo.setMajor(record.getString("PROFESSION"));//专业
        expertInfo.setTopEducation(processTopEducation(record.getString("DEGREETYPE")));//最高学历
        expertInfo.setDegree(processDegree(record.getString("DEGREE")));//学位

        //出生日期
        expertInfo.setBirthdate(getBirthDate(record.getString("BIRTHYEAR"), record.getString("BIRTHMONTH"), record.getString("BIRTHDAY")));
        expertInfo.setPhone(getLinkTel(record));//联系电话
        expertInfo.setFax(record.getString("FAX"));//传真
        expertInfo.setHomePhone(record.getString("HOMETEL"));//家庭电话
        //expertInfo.setZipCode(record.getString(""));//邮政编码
        //地区编码
        expertInfo.setAreaCode(getAreaCode(StringUtils.isNotBlank(record.getString("CITY")) ? record.getString("CITY") : record.getString("PROVINCE")));//行政区域code

        expertInfo.setWorkUnit(record.getString("WORKORG"));//工作单位
        expertInfo.setOnJob(StringUtils.isNotBlank(record.getString("WORKORG")) ? 1 : 0);//是否在职
        expertInfo.setDuty(record.getString("POSITION"));//职务
        expertInfo.setResume(record.getString("RESUME"));//简历
        expertInfo.setTitle(record.getString("POST"));//职称
        //expertInfo.setWorkLife(record.getInt(""));//从业年限
        //TODO 专家状态待定
        expertInfo.setStatus(record.getInt("STATUS") == 1 ? 1 : 3);//专家状态

        expertInfo.setIsCommon(0);//是否为公共专家
        expertInfo.setIsActive(0);//是否激活
        expertInfo.setAuditStatus(3);//审批状态 0:待审批 2:审批不通过3:审批通过

        expertInfo.setCreateUserId("system");
        expertInfo.setCreateTime(new Date());
        expertInfo.setOrgCode(null);
        String bidOrgCode = record.getString("bidorg");
        expertInfo.setTenantId("bms_import");

        //expertInfo.setOccupQualSeq("");//职业资格序列
        //expertInfo.setOccupQualLevel("");//职业资格等级

        //如果BMS库中bidorg字段非空,刚需要保存专家与机构的合作关系
        if (StringUtils.isNotBlank(bidOrgCode)) {
            ExpPartnership expPartnership = new ExpPartnership();
            expPartnership.setId(UUID.randomUUID().toString().replace("-", ""));//ID
            expPartnership.setExpertId(expertInfo.getId());//专家ID
            expPartnership.setTenantId(getTenantId(bidOrgCode));//租户ID
            expPartnership.setStatus(0);//招标机构对专家的启用/禁用状态, 0:启用 1:禁用
            expPartnership.setAuditStatus(3);//审核状态 0:待审批 1:审批中 2:审批不通过3:审批通过
            expPartnership.setOrigin(1);//来源 1:内部 2:外部
            expPartnership.setCreateTime(new Date());
            result.put("expPartnership", expPartnership);
        }

        result.put("expertInfo", expertInfo);

        return result;

    }

    //获取联系电话
    private String getLinkTel(Record record) {
        String mobile = record.getString("MOBILE");
        String linktel = record.getString("LINKTEL");
        if (StringUtils.isNotBlank(linktel)) {
            if (linktel.length()>=20){
                return linktel.substring(0,20);
            }
            return linktel;
        } else {
            if (StringUtils.isNotBlank(mobile)) {
                if (mobile.length() >= 20) {
                    return mobile.substring(0, 20);
                }
                return mobile;
            }
            return null;
        }
    }

    //获取手机号
    private String getMobile(Record record) {
        String mobile = record.getString("MOBILE");
        String linktel = record.getString("LINKTEL");
        if (StringUtils.isNotBlank(mobile)) {
            if (mobile.startsWith("1") && mobile.length() > 10) {
                return mobile.substring(0, 11);
            }
            if (StringUtils.isNotBlank(linktel)) {
                if (linktel.startsWith("1") && linktel.length() > 10) {
                    return linktel.substring(0, 11);
                }
            }
            return null;

        } else {
            if (StringUtils.isNotBlank(linktel)) {
                if (linktel.startsWith("1") && linktel.length() > 10) {
                    return linktel.substring(0, 11);
                }
            }
            return null;
        }
    }


    //证件类型转换
    public String processCardType(String cardType) {

        if (StringUtils.isBlank(cardType)) {
            return null;
        }
        if (cardType.equals("1")) {
            return "01";
        } else if (cardType.equals("2")) {
            return "02";
        } else if (cardType.equals("3")) {
            return "08";
        }

        return null;
    }

    //拼接出生日期
    public Date getBirthDate(String year, String month, String day) {

        if (StringUtils.isBlank(year) || StringUtils.isBlank(month) || StringUtils.isBlank(day)) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthDay = year + "-" + month + "-" + day;
        Date birth = null;
        try {
            birth = format.parse(birthDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return birth;
    }

    //将BMS中的学位转换成expert中的
    public String processDegree(String bmsDegree) {
        if (StringUtils.isBlank(bmsDegree)) {
            return null;
        }
        if (bmsDegree.equals("2")) {
            return "BACHELOR";//学士
        } else if (bmsDegree.equals("3")) {
            return "MASTER";//硕士
        } else if (bmsDegree.equals("4")) {
            return "DOCTOR";//博士
        } else {
            return "OTHER";//其它
        }

    }

    //行政区域代码转换,对应关系在expert测试库中的area_bms表中.
    public String getAreaCode(String bmsCityId) {
        if (StringUtils.isBlank(bmsCityId)) {
            return null;
        }
        List<Record> zones = expertTestDao.query("area_bms", Cnd.where("bms_area_code", "like", "%" + bmsCityId + "%"));
        if (zones != null && zones.size() == 1) {
            return zones.get(0).getString("code");
        } else {
            return null;
        }
    }

    //最高学历转换
    public String processTopEducation(String bmsDegreeType) {
        if (StringUtils.isBlank(bmsDegreeType)) {
            return null;
        }
        if (bmsDegreeType.equals("1")) {
            return "21";
        } else if (bmsDegreeType.equals("2")) {
            return "14";
        } else if (bmsDegreeType.equals("3")) {
            return "11";
        } else if (bmsDegreeType.equals("4")) {
            return "00";
        } else if (bmsDegreeType.equals("5")) {
            return "31";
        }
        return null;
    }

    //根据BMS表中的bidorg来查询租户id
    public String getTenantId(String bidOrg) {
        if (StringUtils.isBlank(bidOrg)) {
            return null;
        }
        List<Record> rsc_experts = expertTestDao.query("tenantId_bidOrg", Cnd.where("bidOrg", "=", bidOrg));
        if (rsc_experts != null && rsc_experts.size() > 0) {
            return rsc_experts.get(0).getString("tenantId");
        }
        return null;
    }


}
