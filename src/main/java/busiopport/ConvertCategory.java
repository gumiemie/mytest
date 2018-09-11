package busiopport;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;

import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 商机订阅历史数据处理--行业转换
 * @date 2017/3/27 17:12$
 */
public class ConvertCategory {
    private static NutDao hadesTestDao;
    private static NutDao busiDao;
    private static final String allInCodes= "11,12,14,08,22,04,50,02,21,09,24,26,15,10,16,18,39,07,05,17,25,19,13,20,06,23" +
            ",27,01,03,31,45,35,36,34,43,48,32,42,44,46,28,47,49,30,33,40,38,41,37,29,all";
    private static final String allInNames="机械设备,交通运输,仪器仪表,医药、医疗器械,冶金矿产,纺织服装皮革,环保设备,能源（石油/石化/煤炭/新能源）,印刷,五金,电气,电力,IT、通讯及信息技术," +
            "电子,工程建筑行业,传媒、广电,文化、教育、体育、娱乐服务,化工,包装,安全防护,建筑建材,家居行业,办公用品,橡胶塑胶,工艺礼品玩具,家用电器,运动、休闲,农产品,食品饮料烟草,物流运输," +
            "维护清洗,房产物业,租赁和商务服务,金融保险,软件服务,网络通信,通信及信息服务,出版印刷,咨询培训,会展服务,居民服务和其它服务业,代理营销,其他服务,纸业,批发零售、住宿餐饮," +
            "卫生、社会保障福利,水利、环境和公共设施管理,国际、社会组织与公共管理,科研技术和地质勘查,商业贸易(综合类企业)";

    static {
        DruidDataSource expertTestDruidDataSource = new DruidDataSource();
        expertTestDruidDataSource.setUrl("jdbc:mysql://10.1.1.68:3306/hades?useUnicode=true");
        expertTestDruidDataSource.setUsername("testdb");
        expertTestDruidDataSource.setPassword("NYbICTrtYS#E");
        hadesTestDao = new NutDao(expertTestDruidDataSource);

        DruidDataSource busiDruidDataSource = new DruidDataSource();
        busiDruidDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/hades?useUnicode=true");
        busiDruidDataSource.setUsername("ebnewuser");
        busiDruidDataSource.setPassword("ebnewuserbid6link!@#$");
        busiDao = new NutDao(busiDruidDataSource);


    }

    /**
     * @Description: 转换mailCustomHistory表
     * @param:
     * @return:
     * @Date: 2017/4/10 17:15
     */
    /*public void execute() throws Exception {
        List<MailCustomHistoryDto> historyDtos = hadesTestDao.query(MailCustomHistoryDto.class, null);
        if (historyDtos!=null&&historyDtos.size()>0){
            for (MailCustomHistoryDto mch:historyDtos){
                String industryCodes = mch.getIndustryCodes();
                if (StringUtils.isBlank(industryCodes)){
                    continue;
                }
                if (industryCodes.contains("all")) {
                    mch.setIndustryCodes(allInCodes);
                    mch.setIndustryNames(allInNames);
                } else {
                    StringBuffer code = new StringBuffer();
                    StringBuffer name = new StringBuffer();
                    String[] codes = industryCodes.split(",");

                    for (int i = 0; i < codes.length; i++) {
                        List<CategoryMapping> mappings = hadesTestDao.query(CategoryMapping.class, Cnd.where("code_b", "=", codes[i]));
                        for (CategoryMapping sm : mappings) {
                            if (code.indexOf(sm.getCodec())<=0) {
                                code.append(sm.getCodec() + ",");
                                name.append(sm.getNamec() + ",");
                            }
                        }
                    }
                    if (code != null && code.length() > 0 && name != null && name.length() > 0) {
                        mch.setIndustryCodes(code.substring(0, code.length() - 1));
                        mch.setIndustryNames(name.substring(0, name.length() - 1));
                    }else {
                        continue;
                    }
                }
                hadesTestDao.update(mch);
            }
        }
    }*/

    @Test
    /**
     * @Description: 转换mailCustom表
     * @param: []
     * @return: void
     * @Date: 2017/4/10 17:14
     */
    public void convert() {

        List<MailcustomDto> mailCustoms = busiDao.query(MailcustomDto.class, null);
        if (mailCustoms != null && mailCustoms.size() > 0) {
            for (MailcustomDto mailcustomDto : mailCustoms) {
                String industryCodes = mailcustomDto.getIndustryCodes();
                if (StringUtils.isBlank(industryCodes)){
                    continue;
                }
                if (industryCodes.contains("all")) {
                    mailcustomDto.setIndustryCodes(allInCodes);
                    mailcustomDto.setIndustryNames(allInNames);
                } else {
                    String[] codes = industryCodes.split(",");
                    StringBuffer code = new StringBuffer();
                    StringBuffer name = new StringBuffer();

                    for (int i = 0; i < codes.length; i++) {
                        List<CategoryMapping> mappings = hadesTestDao.query(CategoryMapping.class, Cnd.where("code_b", "=", codes[i]));
                        for (CategoryMapping sm : mappings) {
                            if (code.indexOf(sm.getCodec())<=0){
                                code.append(sm.getCodec() + ",");
                                name.append(sm.getNamec() + ",");
                            }
                        }
                    }
                    if (code != null && code.length() > 0 && name != null && name.length() > 0) {
                        mailcustomDto.setIndustryCodes(code.substring(0, code.length() - 1));
                        mailcustomDto.setIndustryNames(name.substring(0, name.length() - 1));
                    }else {
                        continue;
                    }
                }
                busiDao.update(mailcustomDto);
            }
        }

    }

}
