package pubService.notice;

import cn.bidlink.cms.send.dto.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import pubService.*;
import utils.DBUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pubService.Utils.*;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 公告发布工具中的公告，重新生成并存储到supervise_send_log中
 * @date 2019/2/27 10:02$
 */
public class NoticeProcessor {

    DBUtils dbUtils = new DBUtils();

    private void insertLogByNoticeId(String noticeId) {

        NutDao oaDao = dbUtils.getOaDao();
        List<NoticeModel> noticeModelList = oaDao.query(NoticeModel.class, Cnd.where("id", "=", noticeId));
        NoticeModel noticeModel = noticeModelList.get(0);

        String content = oaDao.query("notice_content", Cnd.where("notice_id", "=", noticeId)).get(0).getString("content");

        TenderMessageModel param = new TenderMessageModel();
        //公告标题
        String docTitle = noticeModel.getDocTitle();
        param.setBulletinName(docTitle);
        //公告内容
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
        param.setBulletinContent(content);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        //发布时间
        param.setIssueTime(format.format(new Date()));
        Map<String, String> numbers = Utils.processNumber(noticeModel.getBidCode());
        //招标项目编号
        param.setTenderProjectCode(numbers.get("tenderNumber"));
        //招标项目名称
        param.setTenderProjectName(docTitle);
        //标段（包）编号，如果包含"/"，截取，如果不包含补0
        String packageNumber = numbers.get("packageNumber");
        param.setBidSectionCodes(packageNumber);

        //userId和userName
        String createUserId = noticeModel.getCreateUserId();
        NutDao nblUserDao = dbUtils.getNblUserDao();
        List<Record> users = nblUserDao.query("user", Cnd.where("id", "=", createUserId));
        Record user = users.get(0);
        param.setUserId((Long) (user.get("Old_Id")));
        param.setUserName(user.getString("Login_Name"));
        param.setIssueTime(format.format(new Date()));
        //版本号
        param.setVersion(format.format(new Date()));
        //公开类型
        param.setOpenType("1");
        //网址
        param.setUrl("http://www.ebnew.com/");
        param.setNoticeMedia("必联网");
        param.setObjectionMethod("必联网");
        //设置tenderModeEnum
        String infoclassId = noticeModel.getInfoclassId();
        param.setTenderModeEnum(Utils.getTenderModeEnum(infoclassId));

        List<NoticeExt> noticeExts = oaDao.query(NoticeExt.class, Cnd.where("notice_id", "=", noticeId));
        NoticeExt noticeExt = noticeExts.get(0);
        List<NoticeCompany> noticeCompanies = oaDao.query(NoticeCompany.class, Cnd.where("notice_Id", "=", noticeId));
        //招标公告及资格预审公告
        if (CmsInfoClassEnum.BID_INFOCLASS_CODE_GG.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_PR.getNewInfoClassId().equals(infoclassId)) {
            //评审方法,是否支持联合体投标
            param.setQualType(noticeExt.getQualType());//评审方法
            param.setSyndicatedFlag(noticeExt.getSyndicatedFlag() == 1 ? "是" : "否");//是否支持联合体投标
            param.setDocGetStartTime(dateFormat(noticeModel.getBidBookStartTime()));//领购开始时间
            param.setDocGetEndTime(dateFormat(noticeModel.getBidBookEndTime()));//领购截止时间
            param.setDocGetMethod(noticeModel.getBidBookGetMethod());//招标文件获取方法
            param.setBidDocReferEndTime(dateFormat(noticeModel.getEndDate()));//递交截止时间
            param.setBidDocReferMethod(noticeExt.getBidDocReferMethod());//递交方法
            param.setBidOpenTime(dateFormat(noticeModel.getEndDate()));//开标时间
            param.setBidOpenMethod(noticeExt.getIsOnlineOpen() == 1 ? "网上" : "网下");//开标方法
            param.setBulletinCode(packageNumber + "15");
            param.setTenderMessageTypeEnum(TenderMessageTypeEnum.TENDER_BULLETIN);
            if (CmsInfoClassEnum.INFOCLASS_CODE_PR.getNewInfoClassId().equals(infoclassId)) {
                param.setTenderMode("2");
            } else {
                param.setTenderMode("023006".equals(noticeModel.getProcurementTypeCode()) ? "1" : "9");
            }
            if (noticeModel.getNoticeEndTime() != null) {
                param.setEndTime(format.format(noticeModel.getNoticeEndTime()));
            } else {
                param.setEndTime("00000000000000");
            }
        } else if (CmsInfoClassEnum.BID_INFOCLASS_CODE_BG.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_BG_PR.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_BG_ZH.getNewInfoClassId().equals(infoclassId)) {
            //变更公告及资格预审变更公告
            param.setQualType(noticeExt.getQualType());//评审方法
            param.setSyndicatedFlag(noticeExt.getSyndicatedFlag() == 1 ? "是" : "否");//是否支持联合体投标
            param.setDocGetStartTime(dateFormat(noticeModel.getBidBookStartTime()));//领购开始时间
            param.setDocGetEndTime(dateFormat(noticeModel.getBidBookEndTime()));//领购截止时间
            param.setDocGetMethod(noticeModel.getBidBookGetMethod());//招标文件获取方法
            param.setBidDocReferEndTime(dateFormat(noticeModel.getEndDate()));//递交截止时间
            param.setBidDocReferMethod(noticeExt.getBidDocReferMethod());//递交方法
            param.setBidOpenTime(dateFormat(noticeModel.getEndDate()));//开标时间
            param.setBidOpenMethod(noticeExt.getIsOnlineOpen() == 1 ? "网上" : "网下");//开标方法
            param.setTenderMessageTypeEnum(TenderMessageTypeEnum.AMEND_BULLETIN);
            param.setBulletinCode(packageNumber + "16");
            param.setOriginalBulletinCode(packageNumber + "15");
            // 028006 变更公告
            if ("028006".equals(infoclassId)) {
                param.setOriginalBulletinType("1");
            } else if ("028004".equals(infoclassId)) {
                // 028004 资格预审变更公告
                param.setOriginalBulletinType("2");
            } else {
                param.setOriginalBulletinType("9");
            }
        } else if (CmsInfoClassEnum.BID_INFOCLASS_CODE_GS.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_GS_ZH.getNewInfoClassId().equals(infoclassId)) {
            //公示及变更中示
            param.setTenderMessageTypeEnum(TenderMessageTypeEnum.WIN_CANDIDATE);
            //公示类型 1 正常 2 更正 9 其他
            Integer isHasWinner = noticeExt.getIsHasWinner();
            //无中标候选人 类型填9
            param.setPublicityType(isHasWinner != null && isHasWinner == 1 ? "1" : "9");
            param.setStartTime(format.format(new Date()));//发布时间即当前时间
            if (noticeModel.getNoticeEndTime() != null) {
                param.setEndTime(format.format(noticeModel.getNoticeEndTime()));
            } else {
                param.setEndTime("00000000000000");
            }
            param.setBulletinCode("0");
            //中标候选人组
            param.setSectionsWinCandidateDtoList(processSectionsWinCandidateDto(packageNumber, noticeCompanies));
        } else if (CmsInfoClassEnum.BID_INFOCLASS_CODE_ZB.getNewInfoClassId().equals(infoclassId)
                || CmsInfoClassEnum.INFOCLASS_CODE_ZB_ZH.getNewInfoClassId().equals(infoclassId)) {
            //成交结果公告及变更
            param.setTenderMessageTypeEnum(TenderMessageTypeEnum.WIN_BID_BULLETIN);
            param.setBulletinCode(packageNumber + "18");
            // 公告类型: 1 招标公告 ,2 资格预审公告, 9 其他 无中标人选9
            Integer isHasWinner = noticeExt.getIsHasWinner();
            param.setBulletinType(isHasWinner != null && isHasWinner == 1 ? "1" : "9");
            //中标人组
            param.setSectionsBidderDtoList(generateBidders(packageNumber, noticeCompanies));
        }

        SuperviseSendLog superviseSendLog = new SuperviseSendLog();
        superviseSendLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        superviseSendLog.setCreateTime(new Date());
        superviseSendLog.setIsSend(false);
        superviseSendLog.setNoticeId(noticeId);
        //20号令招标项目
        TenderMessageModel projectModel = generateProjectMessageModel(noticeModel, noticeExt, noticeCompanies, user);
        if (projectModel != null) {
            projectModel.setTenderMessageTypeEnum(TenderMessageTypeEnum.TENDER_PROJECT);
            superviseSendLog.setType(2);
            superviseSendLog.setData(JSON.toJSONString(projectModel));
            oaDao.insert(superviseSendLog);
        }

        //10号令,使用公告发布工具发布的公告,生成项目信息
        if (projectModel != null) {
            projectModel.setTenderMessageTypeEnum(TenderMessageTypeEnum.TENDER_PROJECT_PHASE);
            superviseSendLog.setType(12);
            superviseSendLog.setData(JSON.toJSONString(projectModel));
            oaDao.insert(superviseSendLog);
        }


        /*//推送10号令公告
        TenderMessageModel model = tenderMessageService.generateTenderMessageModel(param, noticeModel);
        logger.info("消息队列：{} ======== 消息准备发送：{}", newDestinationName, JSONObject.toJSON(model));
        superviseSendRemoteService.saveAndSend(noticeId, model);

        //20号令公告
        param.setNoticeType(model.getNoticeType());//公告类型
        param.setNoticeNature(model.getNoticeNature());//公告性质
        logger.info("消息队列：{} ======== 消息准备发送：{}", destinationName, JSONObject.toJSON(param));
        superviseSendRemoteService.saveAndSend(noticeId, param);*/

    }


    /**
     * 使用公告发布工具发布公告时生成20号令招标项目
     *
     * @param noticeDetailDto
     * @return
     */
    private TenderMessageModel generateProjectMessageModel(NoticeModel noticeDetailDto, NoticeExt noticeExt, List<NoticeCompany> companies, Record user) {
        //以下是生成10号令招标项目对象，规则依据是schema文件
        TenderMessageModel projectModel = new TenderMessageModel();
        Map<String, String> numbers = processNumber(noticeDetailDto.getBidCode());
        String projectNumber = numbers.get("projectNumber");//项目编号
        String tenderNumber = numbers.get("tenderNumber");//招标项目编号
        String packageNumber = numbers.get("packageNumber");//包编号;

        // 项目名称
        String projectName = noticeDetailDto.getProjectName();

        //项目相关信息
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectCode(projectNumber);//项目编号
        projectDto.setProjectName(projectName);//项目名称
        projectDto.setRegionCode(noticeDetailDto.getProvCode());//项目所在行政区域代码
        //TODO 再确认
        projectDto.setIndustryType("000");//项目行业分类,规范与必联网行业不一致,以000代替
        String fundsSource = noticeDetailDto.getFundSource();
        if (StringUtils.isNotBlank(fundsSource)) {
            projectDto.setFundSource(fundsSource);//资金来源
        } else {
            projectDto.setFundSource("-");//资金来源
        }
        projectDto.setProjectScale(noticeExt.getProjectScale());//项目规模
        ArrayList<ProjectDto> projectDtos = new ArrayList();
        projectDtos.add(projectDto);
        projectModel.setProjectDtoList(projectDtos);

        //招标项目
        projectModel.setProjectCode(projectNumber);//项目编号
        projectModel.setTenderProjectCode(tenderNumber);//招标项目编号
        projectModel.setTenderProjectName(projectName);//招标项目名称
        //发公告行业要改成国际标的行业
        projectModel.setTenderProjectIndustriesType(processIndustryCode(noticeDetailDto.getIndustryCode()));//所属行业
        projectModel.setRegionCode(noticeDetailDto.getProvCode());//招标项目所在行政区域代码
        projectModel.setTenderContent(validateData(noticeExt.getTenderContent()));//招标内容与范围
        projectModel.setSuperviseDeptName(noticeExt.getSuperviseDeptName());//监管部门
        //招标人组相关信息
        ArrayList<TendererDto> tendererDtos = genereateTendererLists(companies);

        projectModel.setTendererDtoList(tendererDtos);
        //招标机构组
        projectModel.setTenderAgencyDtoList(getTenderAgencyDtoList((Long) user.get("OldCompanyId")));

        //招标方式
        projectModel.setTenderMode(processTenderMode(noticeDetailDto.getProcurementTypeCode()));

        projectModel.setTenderOrganizeForm("2");//招标组织形式，1自行招标；2委托招标；3自行集中；9其他；
        projectModel.setCreationTime(dateFormat(noticeDetailDto.getProjectCreateTime()));//项目创建时间
        projectModel.setSuperviseDeptName(noticeExt.getSuperviseDeptName());//监管部门
        projectModel.setReportDuty(user.getString("name"));//申报责任人
        //projectModel.setTradePlat(BaseConstant.TRADE_PLAT_CODE);//交易平台或发布工具标识码
        //projectModel.setPubServicePlatCode(BaseConstant.PUB_SERVICE_PLAT_CODE);//公共服务平台标识码
        projectModel.setSubmitTimeStamp(new Date());//数据时间戳
        projectModel.setUserId((Long) (user.get("old_id")));//用户id
        projectModel.setUserName(user.getString("Login_Name"));//用户名称

        //标段包
        ArrayList<BidSectionDto> bidSecionDtos = new ArrayList();
        BidSectionDto bidSectionDto = new BidSectionDto();
        bidSectionDto.setBidSectionCode(packageNumber);//标段（包）编号
        bidSectionDto.setBidSectionName(projectName);//名称
        bidSectionDto.setBidSectionContent(noticeExt.getTenderContent());//内容
        bidSectionDto.setBidSectionClassifyCode("0000000");//标段（包）分类代码
        bidSectionDto.setBiddingEligibility(validateData(noticeDetailDto.getBidCondition()));//投标人资格条件
        bidSecionDtos.add(bidSectionDto);
        projectModel.setBidSectionDtoList(bidSecionDtos);

        //附件集
        //todo 附件集与schema规范要求不符
        /*ArrayList<AttachmentDto> attachmentDtos = new ArrayList<>();
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setAttachmentCode("-");
        attachmentDtos.add(attachmentDto);
        projectModel.setAttachmentDtoList(attachmentDtos);*/

        return projectModel;
    }

    /**
     * 根据公司id生成招标机构组
     *
     * @param companyId
     * @return
     */
    private List getTenderAgencyDtoList(Long companyId) {
        List<TenderAgencyDto> tenderAgencyDtos = new ArrayList();
        TenderAgencyDto tenderAgencyDto = new TenderAgencyDto();
        if (companyId == null) {
            return tenderAgencyDtos;
        }
        NutDao centerDao = dbUtils.getCenterDao();
        List<Record> query = centerDao.query("t_reg_company", Cnd.where("id", "=", companyId));
        Record company = query.get(0);
        tenderAgencyDto.setTenderAgencyAddress(company.getString("address"));
        tenderAgencyDto.setTenderAgencyContactor(company.getString("contact"));
        tenderAgencyDto.setTenderAgencyEmail("");
        tenderAgencyDto.setTenderAgencyName(company.getString("name"));
        tenderAgencyDto.setTenderAgencyPhoneNumber(company.getString("tel"));

        tenderAgencyDtos.add(tenderAgencyDto);
        return tenderAgencyDtos;
    }

    @Test
    public void execute() {
        String noticeId = "7b90b938fae1468db10ffbdb8532af3f";
        Integer type = 13;
        regenarateLog(noticeId, type);
    }

    /**
     * 更新日志中的公告内容
     *
     * @param noticeId
     * @param type
     */
    public void regenarateLog(String noticeId, Integer type) {
        if (StringUtils.isBlank(noticeId)){
            return;
        }
        NutDao oaDao = dbUtils.getOaDao();
        String content = oaDao.query("notice_content", Cnd.where("notice_id", "=", noticeId)).get(0).getString("content");

        content = content.replaceAll("&", "&amp;");
        content = content.replaceAll("<", "&lt;");
        content = content.replaceAll(">", "&gt;");
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

        List<SuperviseSendLog> logs = oaDao.query(SuperviseSendLog.class, Cnd.where("notice_id", "=", noticeId).and("type", "=", type));
        if (logs != null && logs.size() != 0) {
            String data = logs.get(0).getData();
            HashMap hashMap = JSONObject.parseObject(data, HashMap.class);
            hashMap.put("bulletinContent", content);

            oaDao.update(SuperviseSendLog.class, Chain.make("data", JSON.toJSONString(hashMap)), Cnd.where("id", "=", logs.get(0).getId()));
        }
    }

}
