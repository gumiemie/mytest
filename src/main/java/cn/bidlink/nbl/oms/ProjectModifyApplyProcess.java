package cn.bidlink.nbl.oms;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import utils.DBUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description oms_项目修改申请历史数据处理
 * @date 2018/4/27 15:09$
 */
public class ProjectModifyApplyProcess {
    NutDao oldTestOmsDao = new DBUtils().getOldTestOmsDao();
    NutDao newTestOmsDao = new DBUtils().getNewTestOmsDao();
    NutDao testCenterDao = new DBUtils().getTestCenterDao();

    NutDao omsDao = new DBUtils().getNewOmsDao();


    @Test
    public void execute() {
        //查询所有申请
        List<OldApply> oldApplies = oldTestOmsDao.query(OldApply.class, null);
        for (int index = 0; index < oldApplies.size(); index++) {
            OldApply oldApply = oldApplies.get(index);
            NewApply newApply = new NewApply();
            try {
                ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                BeanUtils.copyProperties(newApply, oldApply);
                String projectNatureS = oldApply.getProjectNatureS();
                newApply.setProjectNature(StringUtils.isNotBlank(projectNatureS) ? projectNatureS.contains("非") ? 2 : 1 : null);
                String projectCreatorLoginName = oldApply.getProjectCreateName();
                if (StringUtils.isNotBlank(projectCreatorLoginName)) {
                    List<Record> users = testCenterDao.query("t_reg_user", Cnd.where("LOGIN_NAME", "=", projectCreatorLoginName));
                    if (users != null && users.size() > 0) {
                        newApply.setProjectCreateUserId((Long) users.get(0).get("ID"));
                    }
                }
                newApply.setStatus(convertStatus(oldApply.getCurrentStatus()));
                newTestOmsDao.insert(newApply);
                //查询本次申请相关的数据流转记录
                List<OldRecord> oldRecords = oldTestOmsDao.query(OldRecord.class, Cnd.where("applyId", "=", oldApply.getId()));
                for (OldRecord oldRecord : oldRecords) {
                    NewRecord newRecord = new NewRecord();
                    BeanUtils.copyProperties(newRecord, oldRecord);
                    newTestOmsDao.insert(newRecord);
                }
            } catch (Exception e) {
                System.out.println(index);
                System.out.println(oldApply.getId());
                newTestOmsDao.clear(NewApply.class, Cnd.where("id", "=", newApply.getId()));
                newTestOmsDao.clear(NewRecord.class, Cnd.where("apply_Id", "=", newApply.getId()));
                e.printStackTrace();
                break;
            }
        }

    }

    public Integer convertStatus(Integer status) {
        //当前处理状态（0:待处理，1:待批复，2:已批复，3:待修改，4:已修改，5:已完成，6:已撤销）
        //1,待处理 2,待修改 3,已修改 4,待批复  5,已批复  6,已撤销  7,已完成
        switch (status) {
            case 0:
                return 1;
            case 1:
                return 4;
            case 2:
                return 5;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 7;
            case 6:
                return 6;
            default:
                return -1;
        }
    }

    @Test
    public void processSupportId() {
        //查询所有申请
        List<OldApply> oldApplies = oldTestOmsDao.query(OldApply.class, null);

        for (int index = 0; index < oldApplies.size(); index++) {
            OldApply oldApply = oldApplies.get(index);
            String supportId = "";
            String supportName = oldApply.getSupportName();
            if (StringUtils.isNotBlank(supportName)) {
                if (supportName.equals("张雪梅")) {
                    supportId = "000001632506680e742bddc82b67984f";
                } else if (supportName.equals("方小兰")) {
                    supportId = "0000015494a79c43e24dd91ac72d807a";
                } else if (supportName.equals("张伟")) {
                    supportId = "000001632506cf917566de7099955a82";
                }
                newTestOmsDao.update(NewApply.class, Chain.make("supportId", supportId), Cnd.where("id", "=", oldApply.getId()));
            }
        }
    }

    @Test
    public void processApplyId() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\oms历史数据处理\\未处理\\2014_oms.xlsx"));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        if (workbook == null) {
            return;
        }
        Sheet sheet = workbook.getSheetAt(0);
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            long applyId = (long) (row.getCell(0).getNumericCellValue());
            long projectId = (long) (row.getCell(1).getNumericCellValue());
            List<NewApply> newApplies = newTestOmsDao.query(NewApply.class, Cnd.where("project_id", "=", projectId));
            if (newApplies != null && newApplies.size() > 0) {
                if (newApplies.size() == 1) {
                    Long omsId = newApplies.get(0).getId();
                    newTestOmsDao.update(NewApply.class, Chain.make("id", applyId), Cnd.where("project_id", "=", projectId));
                    List<NewRecord> newRecords = newTestOmsDao.query(NewRecord.class, Cnd.where("apply_id", "=", omsId));
                    for (NewRecord newRecord : newRecords) {
                        newTestOmsDao.update(NewRecord.class, Chain.make("apply_id", applyId), Cnd.where("id", "=", newRecord.getId()));
                    }
                } else {
                    //从文件中获取申请时间，时间精度不带秒
                    Date applyDate = row.getCell(3).getDateCellValue();
                    for (NewApply newApply : newApplies) {
                        //把数据库中的时间精度转换成一致的
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        Date applyTime = newApply.getApplyTime();
                        String timeString = format.format(applyTime);
                        Date equalsTime = format.parse(timeString);
                        //如果库里的时间与文件中时间一致,则可以对应
                        if (equalsTime.equals(applyDate)) {
                            Long omsId = newApply.getId();
                            newTestOmsDao.update(NewApply.class, Chain.make("id", applyId), Cnd.where("project_id", "=", projectId).and("apply_time", "=", applyTime));
                            List<NewRecord> newRecords = newTestOmsDao.query(NewRecord.class, Cnd.where("apply_id", "=", omsId));
                            for (NewRecord newRecord : newRecords) {
                                newTestOmsDao.update(NewRecord.class, Chain.make("apply_id", applyId), Cnd.where("id", "=", newRecord.getId()));
                            }
                            break;
                        }
                    }
                }
            }
        }

    }

    @Test
    public void execute2() {
        List<Record> records = omsDao.query("project_modify_apply", Cnd.where("status", "=", 7));
        Pager pager = new Pager();
        pager.setPageSize(1);
        pager.setPageNumber(1);
        for (Record record : records) {
            String id = record.getString("id");
            List<Record> recordList = omsDao.query("project_regulatory_record", Cnd.where("apply_id", "=", id).orderBy("opt_time", "desc"), pager);
            if (recordList != null && recordList.size() > 0) {
                omsDao.update("project_modify_apply", Chain.make("finish_time", recordList.get(0).getString("opt_time")), Cnd.where("id", "=", id));
            }
        }
    }


}
