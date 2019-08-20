package cn.bidlink.nbl.pubService.model;

import cn.bidlink.cms.send.dto.*;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2019/2/27 10:16$
 */
public class Utils {

    /**
     * 处理项目编号，招标项目编号，包编号
     *
     * @param number
     * @return
     */
    public static Map<String, String> processNumber(String number) {
        HashMap<String, String> result = new HashMap();

        String projectNumber = "";//项目编号
        String tenderNumber = "";//招标项目编号
        String packageNumber = "";//包子编号/后的编号;

        int lastIndex = number.lastIndexOf("/");
        if (lastIndex > -1) {
            projectNumber = number.substring(0, lastIndex);
            packageNumber = number.substring(lastIndex + 1);
            StringBuilder stringBuilder = new StringBuilder(projectNumber);
            int lengthFirst = stringBuilder.length();
            int lengthSecond = packageNumber.length();
            while (lengthFirst + lengthSecond != 23) {
                stringBuilder.append("0");
                lengthFirst++;
            }
            stringBuilder.append(packageNumber);
            projectNumber = projectNumber.length() > 17 ? projectNumber.substring(projectNumber.length() - 17) : stringBuilder.substring(0, 17);
            tenderNumber = stringBuilder.substring(0, 20);
            packageNumber = stringBuilder.substring(20);
        } else {
            projectNumber = number;
            packageNumber = "000";
            //项目编号长度
            int length = projectNumber.length();
            if (length >= 17) {
                projectNumber = projectNumber.substring(length - 17);
                tenderNumber = String.format("%-20s", projectNumber).replaceAll(" ", "0");
            } else {
                projectNumber = String.format("%-17s", projectNumber).replaceAll(" ", "0");
                tenderNumber = String.format("%-20s", projectNumber).replaceAll(" ", "0");
            }
        }
        // 标段(包)编号
        packageNumber = tenderNumber + packageNumber;
        result.put("projectNumber", projectNumber);
        result.put("tenderNumber", tenderNumber);
        result.put("packageNumber", packageNumber);
        return result;
    }

    /**
     * 根据公告类型 获取类型枚举
     *
     * @param infoclassId
     * @return
     */
    public static TenderModeEnum getTenderModeEnum(String infoclassId) {
        if (StringUtils.isBlank(infoclassId)) {
            return null;
        }
        //公告
        if (infoclassId.equals(CmsInfoClassEnum.INFOCLASS_CODE_GG_ZH.getNewInfoClassId())) {
            return TenderModeEnum.ANNOUNCEMEBT;
        }
        //变更公告
        if (infoclassId.equals(CmsInfoClassEnum.INFOCLASS_CODE_BG_ZH.getNewInfoClassId())) {
            return TenderModeEnum.AMEND_BULLETIN;
        }
        //公示
        if (infoclassId.equals(CmsInfoClassEnum.INFOCLASS_CODE_GS_ZH.getNewInfoClassId())) {
            return TenderModeEnum.BID_EVALUATION_PUBLIC_INDICTION;
        }
        //中标结果公告
        if (infoclassId.equals(CmsInfoClassEnum.INFOCLASS_CODE_ZB_ZH.getNewInfoClassId())) {
            return TenderModeEnum.WIN_BID_BULLETIN;
        }
        //资格预审公告
        if (infoclassId.equals(CmsInfoClassEnum.INFOCLASS_CODE_PR.getNewInfoClassId())) {
            return TenderModeEnum.QUALIFY_BULLETIN_PHASE;
        }
        //资格预审变更公告
        if (infoclassId.equals(CmsInfoClassEnum.INFOCLASS_CODE_BG_PR.getNewInfoClassId())) {
            return TenderModeEnum.QUALIFY_AMEND_BULLETIN_PHASE;
        }
        return null;
    }

    /**
     * 将时间按yyyyMMddHHmmss格式转换成字符串
     *
     * @param date
     * @return String
     */
    public static String dateFormat(Date date) {
        return dateFormat(date, "yyyyMMddHHmmss");
    }

    /**
     * 按格式将时间转换成字符串 默认的格式为yyyyMMddHHmmss
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyyMMddHHmmss";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 获取中标候选人组
     *
     * @param packageNumber
     * @param companies
     * @return
     */
    public static List<SectionsWinCandidateDto> processSectionsWinCandidateDto(String packageNumber, List<NoticeCompany> companies) {
        ArrayList<SectionsWinCandidateDto> sectionsWinCandidateDtos = new ArrayList();
        SectionsWinCandidateDto sectionsWinCandidateDto = new SectionsWinCandidateDto();
        sectionsWinCandidateDto.setBidSectionCode(packageNumber);
        ArrayList<WinCandidatesDto> winCandidatesDtos = new ArrayList();
        for (NoticeCompany company : companies) {
            if (NoticeCompanyTypeEnum.WIN_CANDIDATE.getValue() == company.getType()) {
                WinCandidatesDto winCandidatesDto = new WinCandidatesDto();
                winCandidatesDto.setPmName(company.getContactor());
                winCandidatesDto.setTimeLimit(company.getTimeLimit() + "");
                winCandidatesDto.setWinCandidateName(company.getName());
                winCandidatesDto.setWinCandidateCode(company.getCode());
                winCandidatesDto.setWinCandidateCodeType(company.getCodeType());
                winCandidatesDto.setWinCandidateOrder(company.getOrderNo().toString());
                winCandidatesDto.setWinCandidateQualification(company.getCandidateQualification());
                winCandidatesDtos.add(winCandidatesDto);
            }
        }
        //如果中标候选人为空,组装一份数据以满足schema规范
        if (winCandidatesDtos.size() == 0) {
            WinCandidatesDto winCandidatesDto = new WinCandidatesDto();
            winCandidatesDto.setPmName("0");
            winCandidatesDto.setTimeLimit("0");
            winCandidatesDto.setWinCandidateName("0");
            winCandidatesDto.setWinCandidateCode("0");
            winCandidatesDto.setWinCandidateCodeType("96");
            winCandidatesDto.setWinCandidateOrder("1");
            winCandidatesDto.setWinCandidateQualification("0");
            winCandidatesDtos.add(winCandidatesDto);
        }
        sectionsWinCandidateDto.setWinCandidatesDtoList(winCandidatesDtos);
        sectionsWinCandidateDtos.add(sectionsWinCandidateDto);
        return sectionsWinCandidateDtos;
    }

    /**
     * 生成中标人
     *
     * @param noticeCompanies
     * @return
     */
    public static List<SectionsBidderDto> generateBidders(String packageNumber, List<NoticeCompany> noticeCompanies) {
        List<SectionsBidderDto> dtos = new ArrayList();
        SectionsBidderDto sectionsBidderDto = new SectionsBidderDto();
        sectionsBidderDto.setBidSectionCode(packageNumber);
        List<BidderDto> bidderDtos = new ArrayList();
        for (NoticeCompany company : noticeCompanies) {
            if (NoticeCompanyTypeEnum.SECTIONS_BIDDER.getValue() == company.getType()) {
                BidderDto bidderDto = new BidderDto();
                bidderDto.setBidderCode(company.getCode());
                bidderDto.setBidderCodeType(company.getCodeType());
                bidderDto.setBidderName(company.getName());
                bidderDtos.add(bidderDto);
            }
        }
        if (bidderDtos.size() == 0) {
            BidderDto bidderDto = new BidderDto();
            bidderDto.setBidderName("0");
            bidderDto.setBidderCode("0");
            bidderDto.setBidderCodeType("96");
            bidderDtos.add(bidderDto);
        }
        sectionsBidderDto.setBidder(bidderDtos);
        dtos.add(sectionsBidderDto);
        return dtos;
    }

    /**
     * 校验参数值，如果为空返回“-”
     *
     * @param object
     * @return
     */
    public static String validateData(Object object) {
        if (object != null) {
            if (object instanceof String) {
                if (StringUtils.isNotBlank((String) object)) {
                    return (String) object;
                } else {
                    return "-";
                }
            }
        }

        return "-";
    }

    /**
     * 获取招标方式
     *
     * @param procurementTypeCode
     * @return
     */
    public static String processTenderMode(String procurementTypeCode) {
        if (StringUtils.isNotBlank(procurementTypeCode)) {
            if (procurementTypeCode.equals("023006")) {
                return "1";
            }
        }
        return "9";
    }

    /**
     * 处理招标行业分类编码
     *
     * @param code
     * @return
     */
    public static String processIndustryCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            if (code.length() == 1) {
                return "00" + code;
            } else if (code.length() == 2) {
                return "0" + code;
            }
            return code.substring(0, 2);
        }

        return "000";
    }

    /**
     * 处理招标人
     *
     * @param noticeCompanies
     * @return
     */
    public static ArrayList<TendererDto> genereateTendererLists(List<NoticeCompany> noticeCompanies) {
        ArrayList<TendererDto> tendererDtos = new ArrayList();
        if (noticeCompanies == null || noticeCompanies.size() == 0) {
            TendererDto tendererDto = new TendererDto();
            tendererDto.setTendererName("-");
            tendererDto.setTendererCode("0");
            tendererDto.setTenderCodeType("95");
            tendererDtos.add(tendererDto);
        } else {
            for (NoticeCompany company : noticeCompanies) {
                if (1 == company.getType()) {
                    TendererDto tendererDto = new TendererDto();
                    tendererDto.setTendererName(company.getName());
                    tendererDto.setTendererCode(company.getCode());
                    tendererDto.setTenderCodeType(company.getCodeType());
                    tendererDto.setTendererAddress(company.getAddress());
                    tendererDto.setTendererContactor(company.getContactor());
                    tendererDto.setTendererPhoneNumber(company.getPhoneNumber());
                    tendererDto.setTendererEmail(company.getEmail());
                    tendererDtos.add(tendererDto);
                }
            }
        }

        return tendererDtos;
    }

}
