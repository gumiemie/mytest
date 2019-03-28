package cn.bidlink.nbl.expert.utils;

import cn.bidlink.openapi.client.usercenter.impl.OpenApiRegisterClientImpl;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 处理BMS库和现在有专家库中的地址对应关系
 *              base_area表数据同步到nbl_expert测试库area_bms表并添加对应字段bms_code
 * @date 2017/1/22 9:21$
 */
public class AreaMapping {

    private static NutDao expertTestDao;
    private static NutDao bmsDao;
    private static NutDao centerDao;
    private static NutDao userDao;

    private OpenApiRegisterClientImpl openApiRegisterClient;


    static {
        //BMS数据库
        DruidDataSource bmsDruidDataSource = new DruidDataSource();
        bmsDruidDataSource.setUrl("jdbc:oracle:thin:@211.151.182.203:1521:newxcb");
        bmsDruidDataSource.setUsername("gyseledb");
        bmsDruidDataSource.setPassword("seledb@2017");
        bmsDao = new NutDao(bmsDruidDataSource);

        //nbl_expert测试库
        DruidDataSource expertTestDruidDataSource = new DruidDataSource();
        expertTestDruidDataSource.setUrl("jdbc:mysql://10.1.1.68:3306/nbl_expert?useUnicode=true");
        expertTestDruidDataSource.setUsername("jiaoyipt");
        expertTestDruidDataSource.setPassword("8kS#0(M");
        expertTestDao = new NutDao(expertTestDruidDataSource);

        //悦招用户正式库
        DruidDataSource userDruidDataSource = new DruidDataSource();
        userDruidDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/nbl_user?useUnicode=true");
        userDruidDataSource.setUsername("ebnewuser");
        userDruidDataSource.setPassword("ebnewuserbid6link!@#$");
        userDao = new NutDao(userDruidDataSource);

        //中心库正式
        DruidDataSource centerDruidDataSource = new DruidDataSource();
        centerDruidDataSource.setUrl("jdbc:mysql://mariadb.ebnew.com:3306/unireg?useUnicode=true");
        centerDruidDataSource.setUsername("querydb");
        centerDruidDataSource.setPassword("QuerBi%DdB8");
        centerDao = new NutDao(centerDruidDataSource);

    }

    @Test
    public void execute(){
        processAreaData();
    }

    public  void processAreaData(){

        List<Record> rsc_zone = bmsDao.query("XCB.RSC_ZONE", null);
        List<String> noResult = new ArrayList<String>();
        List<String> mʌltiResult = new ArrayList<String>();
        for (Record r:rsc_zone){
            String name = r.getString("name");
            List<Record> zones = expertTestDao.query("area_bms", Cnd.where("name", "like", "%" + name + "%"));
            if (zones!=null&&zones.size()>0){
                if (zones.size()>1){//对应的记录数有多个,不处理,将有问题的数据存储起来,人工核对.
                    String newName = name+"市";
                    mʌltiResult.add(name);
                    List<Record> newZones = expertTestDao.query("area_bms", Cnd.where("name", "like", "%" + newName + "%"));
                    if (newZones.size()==1){
                        expertTestDao.update("area_bms", Chain.make("bms_area_code",r.getInt("id")).add("bms_area_name",name),
                                Cnd.where("id","=",newZones.get(0).getString("id")));
                        mʌltiResult.remove(name);
                    }

                }else {//正好对应 ,刚将BMS库中的id,name同步到expert库中.
                    expertTestDao.update("area_bms", Chain.make("bms_area_code",r.getInt("id")).add("bms_area_name",name),
                            Cnd.where("id","=",zones.get(0).getString("id")));
                }
            }else {
                noResult.add(name);
            }

        }
        System.out.println("需要人工核对的:");
        for (String s:mʌltiResult){
            System.out.println(s);
        }

        System.out.println("在expert中没有查到对应地址的有:");
        for (String s1:noResult){
            System.out.println(s1);
        }

    }

    /**
     * @Description: 建立租户ID和bidOrg的对应关系,在表tenantId_bidOrg中体现
     * @param:
     * @return:
     * @Date: 2017/1/22 16:59
     */
    @Test
    public  void processBidOrgData(){

        List<Record> rsc_experts = expertTestDao.query("tenantId_bidOrg", null);
        for (Record r:rsc_experts){
            //code是BMS库中机构代码
            String code =  r.getString("bidorg");
            //根据机构代码去中心库t_reg_companyg表中查询'公司ID'
            List<Record> query = centerDao.query("t_reg_company", Cnd.where("CODE", "=", code));
            String id = null;
            //若查询结果不为空
            if (query!=null&&query.size()>0){
                //获取公司ID
                String companyId = query.get(0).getString("ID");
                //根据公司ID去user表中查询tenantId
                List<Record> tenantIds = userDao.query("user", Cnd.where("OLD_COMP_ID", "=", companyId).and("IS_SUBJECT","=",0));
                if (tenantIds!=null&&tenantIds.size()==1){
                    //获取tenantId
                    id = tenantIds.get(0).getString("id");
                }
            }
            //将与机构代码对应的租户ID存储到关系表tenantId_bidOrg中
            expertTestDao.update("tenantId_bidOrg",Chain.make("tenantId",id),Cnd.where("bidorg","=",code));
        }

    }



}
