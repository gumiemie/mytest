package cn.bidlink.nbl.expert.utils;

import com.alibaba.druid.pool.DruidDataSource;
import cn.bidlink.nbl.expert.model.Exp_Ind_Mapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 更新专家的行业信息
 * @date 2017/5/12 15:17$
 */
public class ImportExpert_IndustryMap {

    private static NutDao expertDao;

    static {
        //nbl_expert正式库
        DruidDataSource expertDruidDataSource = new DruidDataSource();
        expertDruidDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/nbl_expert?useUnicode=true");
        expertDruidDataSource.setUsername("ebnewuser");
        expertDruidDataSource.setPassword("ebnewuserbid6link!@#$");
        expertDao = new NutDao(expertDruidDataSource);
    }

    @Test
    public void execute() throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\湖南国联专家（销售已整理）.xls")));
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 1;i<sheet.getLastRowNum();i++){
            String name = sheet.getRow(i).getCell(0).getStringCellValue();
            String industryCodes = sheet.getRow(i).getCell(2).getStringCellValue();
            industryCodes=industryCodes.replace("，",",");
            String[] inCodes = StringUtils.isNotBlank(industryCodes)?industryCodes.split(","):null;
            //获取专家Id
            List<String> expIds = this.fetchExpId(expertDao, name);
            if (expIds!=null&&expIds.size()>0){
                for (String expId:expIds){
                    if (inCodes!=null&&inCodes.length>0){
                        for (int j=0;j<inCodes.length;j++){
                            Exp_Ind_Mapping param = new Exp_Ind_Mapping();
                            param.setId(UUID.randomUUID().toString().replace("-",""));
                            param.setExpertId(expId);
                            param.setIndustryId(inCodes[j]);
                            expertDao.insert(param);
                        }
                    }
                }
            }

        }


    }

    private List<String> fetchExpId(NutDao dao,String name){
        Sql sql = Sqls.create("select a.id from exp_expert_info a left join exp_partnership b " +
                "on a.id = b.EXPERT_ID where b.TENANT_ID = '6423c48ca493455fa22fefef15cea818' AND a.name = @name");
        sql.setParam("name",name);
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<String> ids = new ArrayList<String>();
                if (rs.next()){
                    ids.add(rs.getString("id"));
                }
                return ids;
            }
        });
        dao.execute(sql);
        return sql.getList(String.class);
    }


}
