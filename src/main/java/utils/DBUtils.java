package utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.nutz.dao.impl.NutDao;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用于获取dbCennection的工具类.
 * @date 2017/8/7 10:06$
 */
public class DBUtils {

    private NutDao cnDao;//国内标
    private NutDao inDao;//国际标
    private NutDao shjdDao;//机电
    private NutDao oaDao;//oa库
    private NutDao testHadesDao;//hades测试库
    private NutDao hadesDao;//hades正式库
    private NutDao biddingDao;//bidding正式库
    private NutDao testBiddingDao;//bidding测试库
    private NutDao testOaDao;//oa测试库
    private NutDao testNoticeDao;//公告测试库
    private NutDao testExpertDao;//专家测试库
    private NutDao expertDao;//专家正式库
    private NutDao msgCenterDao;
    private NutDao oldTestOmsDao;//老oms测试库
    private NutDao oldOmsDao;//老oms正式
    private NutDao newTestOmsDao;//新oms测试库
    private NutDao newOmsDao;//新oms正式
    private NutDao testCenterDao;//中心库测试
    private NutDao centerDao;//中心库正式
    private NutDao nblUserDao;//正式nblUser库
    private NutDao nblUserTestDao;//测试nblUser


    public NutDao getCnDao() {
        DruidDataSource cnDataSource = new DruidDataSource();
        cnDataSource.setUrl("jdbc:mysql://10.0.0.88:3306/bms_cn?useUnicode=true");
        cnDataSource.setUsername("ebnewuser");
        cnDataSource.setPassword("ebnewuserbid6link!@#$");
        cnDao = new NutDao(cnDataSource);
        return cnDao;
    }

    public void setCnDao(NutDao cnDao) {
        this.cnDao = cnDao;
    }

    public NutDao getInDao() {
        DruidDataSource inDataSource = new DruidDataSource();
        inDataSource.setUrl("jdbc:mysql://211.151.182.215:3306/bms_yf?useUnicode=true");
        inDataSource.setUsername("bps2015");
        inDataSource.setPassword("bps2015@zw!");
        inDao = new NutDao(inDataSource);
        return inDao;
    }

    public void setInDao(NutDao inDao) {
        this.inDao = inDao;
    }

    public NutDao getShjdDao() {
        DruidDataSource shjdDataSource = new DruidDataSource();
        shjdDataSource.setUrl("jdbc:mysql://222.66.64.102:3306/meetc-official?useUnicode=true");
        shjdDataSource.setUsername("meetcuser");
        shjdDataSource.setPassword("bU01l@w0%Il");
        shjdDao = new NutDao(shjdDataSource);
        return shjdDao;
    }

    public void setShjdDao(NutDao shjdDao) {
        this.shjdDao = shjdDao;
    }

    public NutDao getOaDao() {
        DruidDataSource oaDataSource = new DruidDataSource();
        oaDataSource.setUrl("jdbc:mysql://10.0.0.88:3306/nbl_oa?useUnicode=true");
        oaDataSource.setUsername("ebnewuser");
        oaDataSource.setPassword("ebnewuserbid6link!@#$");
        oaDao = new NutDao(oaDataSource);
        return oaDao;
    }

    public void setOaDao(NutDao oaDao) {
        this.oaDao = oaDao;
    }

    public NutDao getTestHadesDao() {
        DruidDataSource testHadesDruidDataSource = new DruidDataSource();
        testHadesDruidDataSource.setUrl("jdbc:mysql://10.1.1.68:3306/hades?useUnicode=true");
        testHadesDruidDataSource.setUsername("testdb");
        testHadesDruidDataSource.setPassword("NYbICTrtYS#E");
        testHadesDao = new NutDao(testHadesDruidDataSource);
        return testHadesDao;
    }

    public void setTestHadesDao(NutDao testHadesDao) {
        this.testHadesDao = testHadesDao;
    }

    public NutDao getHadesDao() {
        DruidDataSource busiDruidDataSource = new DruidDataSource();
        busiDruidDataSource.setUrl("jdbc:mysql://10.0.0.88:3306/hades?useUnicode=true");
        busiDruidDataSource.setUsername("ebnewuser");
        busiDruidDataSource.setPassword("ebnewuserbid6link!@#$");
        hadesDao = new NutDao(busiDruidDataSource);
        return hadesDao;
    }

    public void setHadesDao(NutDao hadesDao) {
        this.hadesDao = hadesDao;
    }

    public NutDao getBiddingDao() {
        DruidDataSource biddingDataSource = new DruidDataSource();
        biddingDataSource.setUrl("jdbc:mysql://10.0.0.88:3306/nbl_bidding?useUnicode=true");
        biddingDataSource.setUsername("ebnewuser");
        biddingDataSource.setPassword("ebnewuserbid6link!@#$");
        biddingDao = new NutDao(biddingDataSource);
        return biddingDao;
    }

    public void setBiddingDao(NutDao biddingDao) {
        this.biddingDao = biddingDao;
    }

    public NutDao getTestBiddingDao() {
        DruidDataSource testBiddingDataSource = new DruidDataSource();
        testBiddingDataSource.setUrl("jdbc:mysql://10.4.1.31:3306/nbl_bidding?useUnicode=true");
        testBiddingDataSource.setUsername("jiaoyipt");
        testBiddingDataSource.setPassword("8kS#0(M");
        testBiddingDao = new NutDao(testBiddingDataSource);
        return testBiddingDao;
    }

    public void setTestBiddingDao(NutDao testBiddingDao) {
        this.testBiddingDao = testBiddingDao;
    }

    public NutDao getTestOaDao() {
        DruidDataSource testOaDataSource = new DruidDataSource();
        testOaDataSource.setUrl("jdbc:mysql://10.4.1.31:3306/nbl_oa?useUnicode=true");
        testOaDataSource.setUsername("jiaoyipt");
        testOaDataSource.setPassword("8kS#0(M");
        testOaDao = new NutDao(testOaDataSource);
        return testOaDao;
    }

    public void setTestOaDao(NutDao testOaDao) {
        this.testOaDao = testOaDao;
    }

    public NutDao getTestNoticeDao() {
        DruidDataSource testNoticeDataSource = new DruidDataSource();
        testNoticeDataSource.setUrl("jdbc:mysql://10.4.1.31:3306/nbl_notice?useUnicode=true");
        testNoticeDataSource.setUsername("jiaoyipt");
        testNoticeDataSource.setPassword("8kS#0(M");
        testNoticeDao = new NutDao(testNoticeDataSource);
        return testNoticeDao;
    }

    public void setTestNoticeDao(NutDao testNoticeDao) {
        this.testNoticeDao = testNoticeDao;
    }

    public NutDao getTestExpertDao() {
        DruidDataSource testExpertDataSource = new DruidDataSource();
        testExpertDataSource.setUrl("jdbc:mysql://10.4.1.31:3306/nbl_expert?useUnicode=true");
        testExpertDataSource.setUsername("jiaoyipt");
        testExpertDataSource.setPassword("8kS#0(M");
        testExpertDao = new NutDao(testExpertDataSource);
        return testExpertDao;
    }

    public void setTestExpertDao(NutDao testExpertDao) {
        this.testExpertDao = testExpertDao;
    }

    public NutDao getExpertDao() {
        DruidDataSource expertDataSource = new DruidDataSource();
        expertDataSource.setUrl("jdbc:mysql://10.0.0.88:3306/nbl_expert?useUnicode=true");
        expertDataSource.setUsername("ebnewuser");
        expertDataSource.setPassword("ebnewuserbid6link!@#$");
        expertDao = new NutDao(expertDataSource);
        return expertDao;
    }

    public void setExpertDao(NutDao expertDao) {
        this.expertDao = expertDao;
    }

    public NutDao getMsgCenterDao() {
        DruidDataSource msgCenterDataSource = new DruidDataSource();
        msgCenterDataSource.setUrl("jdbc:mysql://211.151.182.216:3306/usercenter_message?useUnicode=true");
        msgCenterDataSource.setUsername("pmptiming");
        msgCenterDataSource.setPassword("pmp2012timingebnew");
        msgCenterDao = new NutDao(msgCenterDataSource);
        return msgCenterDao;
    }

    public void setMsgCenterDao(NutDao msgCenterDao) {
        this.msgCenterDao = msgCenterDao;
    }

    public NutDao getOldTestOmsDao() {
        DruidDataSource oldTestOmsDataSource = new DruidDataSource();
        oldTestOmsDataSource.setUrl("jdbc:oracle:thin:@10.1.0.10:1521:import");
        oldTestOmsDataSource.setUsername("eoss");
        oldTestOmsDataSource.setPassword("eoss");
        oldTestOmsDao = new NutDao(oldTestOmsDataSource);
        return oldTestOmsDao;
    }

    public void setOldTestOmsDao(NutDao oldTestOmsDao) {
        this.oldTestOmsDao = oldTestOmsDao;
    }

    public NutDao getNewTestOmsDao() {
        DruidDataSource newTestOmsDataSource = new DruidDataSource();
        newTestOmsDataSource.setUrl("jdbc:mysql://10.4.0.20:3306/bac_bak?useUnicode=true");
        newTestOmsDataSource.setUsername("tianyuan");
        newTestOmsDataSource.setPassword("s7GaCUG5");
        newTestOmsDao = new NutDao(newTestOmsDataSource);
        return newTestOmsDao;
    }

    public void setNewTestOmsDao(NutDao newTestOmsDao) {
        this.newTestOmsDao = newTestOmsDao;
    }

    public NutDao getTestCenterDao() {
        DruidDataSource testCenterDataSource = new DruidDataSource();
        testCenterDataSource.setUrl("jdbc:mysql://10.1.1.209:3306/unireg?useUnicode=true");
        testCenterDataSource.setUsername("unireg");
        testCenterDataSource.setPassword("BiD@!u^i1$");
        testCenterDao = new NutDao(testCenterDataSource);
        return testCenterDao;
    }

    public void setTestCenterDao(NutDao testCenterDao) {
        this.testCenterDao = testCenterDao;
    }

    public NutDao getCenterDao() {
        DruidDataSource centerDataSource = new DruidDataSource();
        centerDataSource.setUrl("jdbc:mysql://mariadb.ebnew.com:3306/unireg?useUnicode=true");
        centerDataSource.setUsername("querydb");
        centerDataSource.setPassword("QuerBi%DdB8");
        centerDao = new NutDao(centerDataSource);
        return centerDao;
    }

    public void setCenterDao(NutDao centerDao) {
        this.centerDao = centerDao;
    }

    public NutDao getOldOmsDao() {
        DruidDataSource oldOmsDataSource = new DruidDataSource();
        oldOmsDataSource.setUrl("jdbc:oracle:thin:@211.151.182.202:1521:blcg");
        oldOmsDataSource.setUsername("eoss");
        oldOmsDataSource.setPassword("eoss");
        oldOmsDao = new NutDao(oldOmsDataSource);
        return oldOmsDao;
    }

    public void setOldOmsDao(NutDao oldOmsDao) {
        this.oldOmsDao = oldOmsDao;
    }

    public NutDao getNewOmsDao() {
        DruidDataSource newOmsDataSource = new DruidDataSource();
        newOmsDataSource.setUrl("jdbc:mysql://10.0.0.88:3306/bac_bak?useUnicode=true");
        newOmsDataSource.setUsername("ebnewuser");
        newOmsDataSource.setPassword("ebnewuserbid6link!@#$");
        newOmsDao = new NutDao(newOmsDataSource);
        return newOmsDao;
    }

    public void setNewOmsDao(NutDao newOmsDao) {
        this.newOmsDao = newOmsDao;
    }

    public NutDao getNblUserDao() {
        DruidDataSource userDataSource = new DruidDataSource();
        userDataSource.setUrl("jdbc:mysql://10.0.0.88:3306/nbl_user?useUnicode=true");
        userDataSource.setUsername("ebnewuser");
        userDataSource.setPassword("ebnewuserbid6link!@#$");
        nblUserDao = new NutDao(userDataSource);
        return nblUserDao;
    }

    public NutDao getNblUserTestDao() {
        DruidDataSource nblUserTestDataSource = new DruidDataSource();
        nblUserTestDataSource.setUrl("jdbc:mysql://10.4.1.31:3306/nbl_user?useUnicode=true");
        nblUserTestDataSource.setUsername("jiaoyipt");
        nblUserTestDataSource.setPassword("8kS#0(M");
        nblUserTestDao = new NutDao(nblUserTestDataSource);
        return nblUserTestDao;
    }

    public void setNblUserTestDao(NutDao nblUserTestDao) {
        this.nblUserTestDao = nblUserTestDao;
    }

}
