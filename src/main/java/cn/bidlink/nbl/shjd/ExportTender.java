package cn.bidlink.nbl.shjd;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import cn.bidlink.nbl.shjd.model.BmTenderee;
import utils.DBUtils;
import utils.ExcelFileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 导出采购商
 * @date 2018/7/27 14:52$
 */
public class ExportTender {

    @Test
    public void execute() {
        DBUtils dbUtils = new DBUtils();
        NutDao shjdDao = dbUtils.getShjdDao();
        List<BmTenderee> bms = shjdDao.query(BmTenderee.class, Cnd.where("webtype", "=", "SHJD"));
        String headers = "采购商名称,联系人,企业性质,联系方式,行业,认证状态";
        String fields = "cname,manager_cname,company_type,manager_mobile,belong_industry,valid_status";
        String validStatusJsonValues = "{0:'未认证',1:'通过',2:'未通过',-1:'停用',-2:'删除'}";//0未认证 1通过 2未通过 -1停用  -2删除
        HashMap<String, Map> valueMap = new HashMap();
        valueMap.put("valid_status", JSON.parseObject(validStatusJsonValues,HashMap.class));
        ExcelFileUtil.exportFile("上海机电采购商.xls", headers, fields, bms, valueMap, null);
    }

}
