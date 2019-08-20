package cn.bidlink.nbl.pubService.project;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import cn.bidlink.nbl.pubService.model.CmsInfoClassEnum;
import cn.bidlink.nbl.pubService.model.Utils;
import utils.DBUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 对接公服, 流程公告补发到公服
 * @date 2018/7/9 10:23$
 */
public class SendToPubService {

    DBUtils dbUtils = new DBUtils();

    @Test
    public void resend() throws Exception {
        NutDao oaDao = dbUtils.getOaDao();
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\ss.xls"));
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        HashSet notSuccess = new HashSet();
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            String stringCellValue = sheet.getRow(i).getCell(0).getStringCellValue();
            HashMap maps = JSON.parseObject(stringCellValue, HashMap.class);
            String messageType = (String) maps.get("tenderMessageTypeEnum");
            if ("TENDER_BULLETIN".equals(messageType) || "AMEND_BULLETIN".equals(messageType) ||
                    "WIN_CANDIDATE".equals(messageType) || "WIN_BID_BULLETIN".equals(messageType)) {
                List<Record> records = oaDao.query("notice", Cnd.where("DOC_TITLE", "like", "%" + maps.get("bulletinName") + "%"));
                if (records != null) {
                    if (records.size() == 1) {
                        Record record = records.get(0);
                        Integer docSource = (Integer) record.get("DOC_SOURCE");
                        if (docSource == 131) {
                            String noticeId = (String) record.get("id");
                            insert(noticeId);
                        } else {
                            notSuccess.add(maps.get("bulletinName"));
                        }
                    } else {
                        notSuccess.add(maps.get("bulletinName"));
                    }
                }

            } else {
                notSuccess.add(maps.get("bidSectionCodes"));
            }
        }
        Iterator iterator = notSuccess.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }


    public void insertLogByNotice(){
        NutDao oaDao = dbUtils.getOaDao();
        List<Record> records = oaDao.query("notice", Cnd.where("DOC_SOURCE", "=", 131).and("create_time", ">=", "2018-08-31 11:00:00")
                .and("create_time", "<", "2018-09-31 11:00:00"));
        for (Record record:records ){

        }

    }

    @Test
    public void execute(){
        insert("f13b6bb4e76c4681b02b5b975c02420a");
    }

    private void insert(String noticeId) {
        NutDao oaDao = dbUtils.getOaDao();
        List<Record> records = oaDao.query("notice", Cnd.where("id", "=", noticeId));
        Record record = records.get(0);

        String content = oaDao.query("notice_content", Cnd.where("notice_id", "=", noticeId)).get(0).getString("content");
        if (StringUtils.isNotBlank(content)) {
            content = content.replaceAll("&", "&amp;");
            content = content.replaceAll("<", "&lt;");
            content = content.replaceAll(">", "&gt;");
        }
        if (content.length() > 65535) {
            Pattern pattern = Pattern.compile("&\\w+(?!;)");
            String endString = content.substring(65494, 65500);
            Matcher matcher = pattern.matcher(endString);
            if (matcher.find()) {
                content = content.substring(0, 65494 + endString.lastIndexOf("&"));
            } else {
                content = content.substring(0, 65500);
            }
            content = content + "请到必联网查看详情,http://www.ebnew.com";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowDate = format.format(new Date());
        HashMap<String, Object> datas = new HashMap();
        String bidCode = record.getString("BID_CODE");
        Map<String, String> map = Utils.processNumber(bidCode);
        String packageNumber = map.get("packageNumber");
        datas.put("bidSectionCodes", packageNumber);
        datas.put("bulletinContent", content);
        datas.put("bulletinName", record.getString("DOC_TITLE"));
        datas.put("issueTime", nowDate);
        datas.put("openType", 1);
        datas.put("tenderProjectCode", map.get("tenderNumber"));
        datas.put("tenderProjectName", record.getString("DOC_TITLE"));
        datas.put("url", "http://www.ebnew.com");
        NutDao userDao = dbUtils.getNblUserDao();
        List<Record> users = userDao.query("user", Cnd.where("id", "=", record.getString("CREATE_USER_ID")));
        datas.put("userId", users.get(0).getString("OLD_ID"));
        datas.put("userName", users.get(0).getString("LOGIN_NAME"));
        datas.put("version", nowDate);

        //招标公告及资格预审公告
        String tenderMessageTypeEnum = "";
        String infoclassId = record.getString("INFOCLASS_ID");
        if (CmsInfoClassEnum.BID_INFOCLASS_CODE_GG.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_GG_ZH.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_PR.getNewInfoClassId().equals(infoclassId)) {
            datas.put("bulletinCode", packageNumber + "15");
            tenderMessageTypeEnum = "TENDER_BULLETIN";
            datas.put("tenderMessageTypeEnum", tenderMessageTypeEnum);
            if (CmsInfoClassEnum.INFOCLASS_CODE_PR.getNewInfoClassId().equals(infoclassId)) {
                datas.put("tenderMode", "2");
            } else {
                datas.put("tenderMode", "023006".equals(record.getString("PROCUREMENT_TYPE_CODE")) ? "1" : "9");
            }
            if (record.getTimestamp("NOTICE_END_TIME") != null) {
                datas.put("endTime", format.format(record.getTimestamp("NOTICE_END_TIME")));
            } else {
                datas.put("endTime", "00000000000000");
            }
        } else if (CmsInfoClassEnum.BID_INFOCLASS_CODE_BG.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_BG_PR.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_BG_ZH.getNewInfoClassId().equals(infoclassId)) {
            //变更公告及资格预审变更公告
            tenderMessageTypeEnum = "AMEND_BULLETIN";
            datas.put("tenderMessageTypeEnum", tenderMessageTypeEnum);
            datas.put("bulletinCode", packageNumber + "16");
            datas.put("originalBulletinCode", packageNumber + "15");
            // 028006 变更公告
            if ("028006".equals(infoclassId)) {
                datas.put("originalBulletinType", "1");
            } else if ("028004".equals(infoclassId)) {
                // 028004 资格预审变更公告
                datas.put("originalBulletinType", "2");
            } else {
                datas.put("originalBulletinType", "9");
            }
        } else if (CmsInfoClassEnum.BID_INFOCLASS_CODE_GS.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_GS_ZH.getNewInfoClassId().equals(infoclassId)) {
            //公示及变更中示
            tenderMessageTypeEnum = "WIN_CANDIDATE";
            datas.put("tenderMessageTypeEnum", tenderMessageTypeEnum);
            //公示类型 1 正常 2 更正 9 其他
            datas.put("publicityType", "1");
            datas.put("startTime", format.format(record.getTimestamp("PUB_DATE")));
            if (record.getTimestamp("NOTICE_END_TIME") != null) {
                datas.put("endTime", format.format(record.getTimestamp("NOTICE_END_TIME")));
            } else {
                datas.put("endTime", "00000000000000");
            }
            datas.put("bulletinCode", "0");
        } else if (CmsInfoClassEnum.BID_INFOCLASS_CODE_ZB.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_ZB_ZH.getNewInfoClassId().equals(infoclassId)) {
            //成交结果公告及变更
            tenderMessageTypeEnum = "WIN_BID_BULLETIN";
            datas.put("tenderMessageTypeEnum", tenderMessageTypeEnum);
            datas.put("bulletinCode", packageNumber + "18");
            // 公告类型: 1 招标公告 ,2 资格预审公告, 9 其他
            datas.put("bulletinType", "023006".equals(record.getString("PROCUREMENT_TYPE_CODE")) ? "1" : "9");
        }

        SuperviseSendLog superviseSendLog = new SuperviseSendLog();
        superviseSendLog.setCreateTime(new Date());
        superviseSendLog.setData(JSON.toJSONString(datas));
        superviseSendLog.setId(UUID.randomUUID().toString().replace("-", ""));
        superviseSendLog.setIsSend(false);
        superviseSendLog.setPackageId(UUID.randomUUID().toString().replace("-", ""));
        if ("TENDER_BULLETIN".equals(tenderMessageTypeEnum)) {
            superviseSendLog.setType(4);
        }
        if ("AMEND_BULLETIN".equals(tenderMessageTypeEnum)) {
            superviseSendLog.setType(5);
        }
        if ("WIN_CANDIDATE".equals(tenderMessageTypeEnum)) {
            superviseSendLog.setType(6);
        }
        if ("WIN_BID_BULLETIN".equals(tenderMessageTypeEnum)) {
            superviseSendLog.setType(8);
        }
        System.out.println(superviseSendLog.getPackageId());
        NutDao biddingDao = dbUtils.getBiddingDao();
        biddingDao.insert(superviseSendLog);
    }

    @Test
    public void resendFromNotice() {
        String noticeId = "7b90b938fae1468db10ffbdb8532af3f";
        insert(noticeId);
        //Map<String, String> map = ("0610-1840NF010520/02");
    }

    public void testProcessNumber(){
        Map<String, String> stringStringMap = Utils.processNumber("0701-18JZ02090001/2");
        System.out.println(111);
    }


}
