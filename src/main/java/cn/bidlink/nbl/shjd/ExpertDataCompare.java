package cn.bidlink.nbl.shjd;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 对比上海机电系统中的专家和msg_data表中的数据是否一致.
 * @date 2017/11/2 17:07$
 */
public class ExpertDataCompare {

    DBUtils dbUtils = new DBUtils();

    @Test
    public void execute()throws Exception{
        NutDao shjdDao = dbUtils.getShjdDao();
        NutDao msgCenterDao = dbUtils.getMsgCenterDao();
        Map<String,Integer> commenderNumbers = new HashMap();
        int shjdExpertTotal = 0;
        List<Record> msgs = msgCenterDao.query("msg_data", Cnd.where("event_type", "=", "expert_add").and("acknowledge_date", ">", "2017-01-01"));
        for (Record msg:msgs){
            String data = msg.getString("data");
            Map expertInfo = (Map) JSON.parse(data);
            String name = (String) expertInfo.get("name");
            List<Record> shjdExperts = shjdDao.query("bm_experts", Cnd.where("name", "=", name));
            if (shjdExperts!=null&&shjdExperts.size()>0){
                shjdExpertTotal++;
                String commender = (String) expertInfo.get("commender");
                Integer integer = commenderNumbers.get(commender);
                if (integer==null){
                    commenderNumbers.put(commender,1);
                }else {
                    commenderNumbers.put(commender,integer+1);
                }
            }

        }
        System.out.println("2017年至今,新增专家的消费记录共"+msgs.size()+" ---全部被上海机电消费.");
        System.out.println("其中有"+shjdExpertTotal+" 位专家出现在上海机电数据库中");
        System.out.println("上海机电拉取到的专家中:推荐人及其推荐的数量分别为:");
        for (String key:commenderNumbers.keySet()){
            System.out.println(key+"----"+commenderNumbers.get(key));
        }

    }
}
