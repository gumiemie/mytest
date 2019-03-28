package cn.bidlink.nbl.expert.utils;

import cn.bidlink.nbl.expert.model.ExpPartnership;
import cn.bidlink.nbl.expert.model.Exp_Ind_Mapping;
import cn.bidlink.nbl.expert.model.ExpertInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/5/11 17:09$
 */
public class ProcessExpertDataFromExcel {
    NutDao expertDao = new DBUtils().getExpertDao();

    @Test
    public void execute() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\RSC_EXPERTS.xls"));
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        ExpertInfo expertInfo = new ExpertInfo();
        expertInfo.setIsCommon(0);//是否为公共专家
        expertInfo.setIsActive(0);//是否激活
        expertInfo.setAuditStatus(3);//审批状态 0:待审批 2:审批不通过3:审批通过
        expertInfo.setCreateUserId("system");
        expertInfo.setOrgCode(null);
        expertInfo.setTenantId("bms_import");
        expertInfo.setAreaCode("110000");
        expertInfo.setStatus(1);

        for (int i = 1; i < lastRowNum; i++) {
            Row row = sheet.getRow(i);
            expertInfo.setId(UUID.randomUUID().toString().replace("-", ""));
            expertInfo.setOldId((long) row.getCell(1).getNumericCellValue());//oldId
            expertInfo.setName(row.getCell(2).getStringCellValue());//姓名
            expertInfo.setEmail(row.getCell(9).getStringCellValue());//邮箱
            expertInfo.setMobilePhone(row.getCell(13).getStringCellValue());//手机
            expertInfo.setTitle(row.getCell(15).getStringCellValue());//职称
            expertInfo.setSex("MALE".equalsIgnoreCase(row.getCell(19).getStringCellValue()) ? 1 : 2);//性别
            expertInfo.setWorkUnit(row.getCell(21).getStringCellValue());//工作单位
            expertInfo.setCreateTime(new Date());
            //存专家
            expertDao.insert(expertInfo);

            ExpPartnership expPartnership = new ExpPartnership();
            expPartnership.setId(UUID.randomUUID().toString().replace("-", ""));//ID
            expPartnership.setExpertId(expertInfo.getId());//专家ID
            expPartnership.setTenantId("508fd9fce76111e59ef090b11c392655");//租户ID
            expPartnership.setStatus(0);//招标机构对专家的启用/禁用状态, 0:启用 1:禁用
            expPartnership.setAuditStatus(3);//审核状态 0:待审批 1:审批中 2:审批不通过3:审批通过
            expPartnership.setOrigin(1);//来源 1:内部 2:外部
            expPartnership.setCreateTime(new Date());
            //存对应关系
            expertDao.insert(expPartnership);
        }

    }

    @Test
    public void execute2() {

        List<ExpertInfo> expertInfos = expertDao.query(ExpertInfo.class, Cnd.where("tenantId", "=", "bms_import").and("createTime", ">", "2018-05-11"));
        for (ExpertInfo expertInfo : expertInfos) {
            Exp_Ind_Mapping expIndMapping = new Exp_Ind_Mapping();
            expIndMapping.setId(UUID.randomUUID().toString().replace("-", ""));
            expIndMapping.setExpertId(expertInfo.getId());
            expIndMapping.setIndustryId("102100111108");
            expertDao.insert(expIndMapping);
            expIndMapping.setId(UUID.randomUUID().toString().replace("-", ""));
            expIndMapping.setIndustryId("102100111110");
            expertDao.insert(expIndMapping);
            expIndMapping.setId(UUID.randomUUID().toString().replace("-", ""));
            expIndMapping.setIndustryId("102100111");
            expertDao.insert(expIndMapping);
        }


    }
}
