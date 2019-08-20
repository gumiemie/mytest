package cn.bidlink.nbl.pubService.model;
import org.apache.commons.lang3.StringUtils;

/**
 * @desc :
 * @author : <a href="mailto:yijundai@ebnew.com">yijundai</a>
 * @date : 2016年8月16日 下午4:14:22
 */
public enum CmsInfoClassEnum {
    INFOCLASS_CODE_PR("028002","0103","资格预审公告","028002","资格预审公告"),
    INFOCLASS_CODE_BG_PR("028004","0104","资格预审变更","028004","资格预审变更"),
    INFOCLASS_CODE_GG_ZH("028001","0105","招标公告","028001","公告"),
    INFOCLASS_CODE_BG_ZH("028006","0106","招标变更","028006","变更公告"),
    INFOCLASS_CODE_GS_ZH("028007","0107","评标公示","028007","评标公示"),
    INFOCLASS_CODE_YG("028003","0102","招标预告","028003","预告"),
    INFOCLASS_CODE_ZB_ZH("028008","0108","中标结果/成交结果/结果公告","028008","结果公告"),

    BID_INFOCLASS_CODE_YG("003001","0102","预告","028003","预告"),
    BID_INFOCLASS_CODE_GG("003002","0105","公告","028001","公告"),
    BID_INFOCLASS_CODE_BG("003003","0106","变更公告","028006","变更公告"),
    //BID_INFOCLASS_CODE_GS("003004","0107,0109","评标公示"),
    BID_INFOCLASS_CODE_GS("003004","0107","评标公示","028007","评标公示"),
    BID_INFOCLASS_CODE_ZB("003005","0108","结果公告","028008","结果公告");

    private final String code;
    private final String oldCode;
    private final String name;
    private final String newInfoClassId;
    private final String newInfoClassName;

    private CmsInfoClassEnum(String code,String oldCode, String name,String newInfoClassId, String newInfoClassName) {
        this.code = code;
        this.oldCode = oldCode;
        this.name = name;
        this.newInfoClassId = newInfoClassId;
        this.newInfoClassName = newInfoClassName;
    }
    public String getOldCode() {
        return oldCode;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getNewInfoClassId() {
        return newInfoClassId;
    }
    public String getNewInfoClassName() {
        return newInfoClassName;
    }
    public static CmsInfoClassEnum getCmsInfoClassEnumByCode(String code){
        if( StringUtils.isBlank( code )){
            return null;
        }
        CmsInfoClassEnum[] cmsArray = CmsInfoClassEnum.values();
        CmsInfoClassEnum tempEnum = null ;
        for (int i = 0; i < cmsArray.length; i++) {
            tempEnum = cmsArray[i];
            if( tempEnum.getCode().equals( code ) ){
                break;
            }
        }
        return tempEnum;
    }
}
