import java.io.Serializable;
import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 公共服务平台>国内招标采购（包含招标和非招标）消息对象
 * @date 2017/12/14 14:35$
 */
public class CnMessageModel implements Serializable {
    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 招标项目编号
     */
    private String tenderProjectCode;

    /**
     * 标段（包）编号,可能是多个,多个用英文,号分隔
     */
    private String bidSectionCodes;

    /**
     * 交易平台项目编号 ebnew+项目编号
     */
    private String tradePlatfProjectCode;

    /**
     * 交易平台招标项目编号 ebnew+项目编号
     */
    private String tradePlatfTenderProjectCode;

    /**
     * 交易平台标段（包）编号 ebnew+标段（包）编号
     */
    private String tradePlatfBidSectionCode;

    /**
     * 招标组织形式 1自行招标；2委托招标；3自行集中；9其他；
     */
    private String tenderOrganizeForm;

    /**
     * 招标方式 《电子招标投标系统技术规范（第1部分）》3.8 招标方式代码
     */
    private String tenderMode;

    /**
     * 招标项目名称
     */
    private String projectName;

    /**
     * 标段（包）名称
     */
    private String bidSectionName;

    /**
     * 招标项目所在行政区域代码 采用GB/T 2260-2007《中华人民共和国行政区划代码》的市级代码
     */
    private String regionCode;

    /**
     * 项目行业分类 GB/T 4754-2011，取1位行业门类字母码+2位大类数字码
     */
    private String industriesType;

    /**
     * 招标项目分类代码/标段（包）分类代码
     * 《电子招标投标系统技术规范（第1部分）》3.6标段（包）分类代码 备注：对于不足7位的，需要用0补齐
     */
    private String tenderProjectClassifyCode;

    /**
     * 交易平台标识码:《电子招投标系统技术规范》附录B.3.1交易平台标识代码；
     */
    private String platformCode;


    /**
     * 组织代码类型 包括招标人，投标人，中标人，招标代理机构，均使用此字段。
     * 参照《01 CEB_JIONT_PROF_1》参看3.19主体机构代码类型
     */
    private String companyCodeType;

    /**
     * 公司代码 包括招标人，投标人，中标人，招标代理机构，均使用此字段。
     * 参照《01 CEB_JIONT_PROF_1》参看3.20主体机构代码
     */
    private String companyCode;

    /**
     * 公司名称 包括招标人，投标人，中标人，招标代理机构，均使用此字段。
     */
    private String companyName;

    /**
     * 基本信息版本号 采用组合码，编码长度为14位数时间戳
     */
    private String basicInfoVersion;

    /**
     * 信息申报责任人联系电话（招标人联系方式）:联系人手机号
     */
    private String infoReporterContactNumber;

    /**
     * 机构联系电话:固定电话
     */
    private String contactNumber;

    /**
     * 国别/地区:采用GB/T 2659-2000 中的3位数字码
     */
    private String countryRegion;

    /**
     * 行业代码:所属行业
     */
    private String industryCode;

    /**
     * 电子邮箱
     */
    private String email;


    /**
     * 公告公示编号,招标公告，变更公告，中标公示，结果公告都使用此字段。
     */
    private String bulletinCode;

    /**
     * 公告名称，招标公告，变更公告，中标公示，结果公告都使用此字段。
     */
    private String bulletinName;

    /**
     * 发布时间:YYYYMMDDhhmmss
     * 招标公告，变更公告，中标公示，结果公告都使用此字段。
     */
    private Date issueTime;

    /**
     * 开始时间:YYYYMMDDhhmmss
     * 招标公告，变更公告，中标公示，结果公告都使用此字段。
     */
    private Date startTime;

    /**
     * 结束时间:YYYYMMDDhhmmss
     * 招标公告，变更公告，中标公示，结果公告都使用此字段。
     */
    private Date endTime;

    /**
     * 公告内容，招标公告，变更公告，中标公示，结果公告都使用此字段。
     */
    private String bulletinContent;

    /**
     * 源URL：默认值 http://www.ebnew.com/
     * 招标公告，变更公告，中标公示，结果公告都使用此字段。
     */
    private String Url;

    /**
     * 原公告类型
     */
    private String originalBulletinType;

    /**
     * 原公告编号 项目编号+15
     */
    private String originalBulletinCode;

    /**
     * 公示类型(1 正常)
     */
    private String publicityType;

    /**
     * 公告类型（9 其他）值显示“其他”
     */
    private String bulletinType;

    /**
     * 中标人编号 值为“0”
     */
    private String winBidderCode;

    /**
     * 中标人是否为联合体
     */
    private String isUnion;

    /**
     * 建立时间 YYYYMMDDhhmmss
     */
    private Date createTime;

    /**
     * 通用字段：版本号 采用组合码，编码长度为14位数时间戳：YYYYMMDDhhmmss
     */
    private Date version;

    /**
     * 公开类型 0--依法公开；1--授权使用；2--不公开;默认传值：1
     */
    private String openType;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTenderProjectCode() {
        return tenderProjectCode;
    }

    public void setTenderProjectCode(String tenderProjectCode) {
        this.tenderProjectCode = tenderProjectCode;
    }

    public String getBidSectionCodes() {
        return bidSectionCodes;
    }

    public void setBidSectionCodes(String bidSectionCodes) {
        this.bidSectionCodes = bidSectionCodes;
    }

    public String getTradePlatfProjectCode() {
        return tradePlatfProjectCode;
    }

    public void setTradePlatfProjectCode(String tradePlatfProjectCode) {
        this.tradePlatfProjectCode = tradePlatfProjectCode;
    }

    public String getTradePlatfTenderProjectCode() {
        return tradePlatfTenderProjectCode;
    }

    public void setTradePlatfTenderProjectCode(String tradePlatfTenderProjectCode) {
        this.tradePlatfTenderProjectCode = tradePlatfTenderProjectCode;
    }

    public String getTradePlatfBidSectionCode() {
        return tradePlatfBidSectionCode;
    }

    public void setTradePlatfBidSectionCode(String tradePlatfBidSectionCode) {
        this.tradePlatfBidSectionCode = tradePlatfBidSectionCode;
    }

    public String getTenderOrganizeForm() {
        return tenderOrganizeForm;
    }

    public void setTenderOrganizeForm(String tenderOrganizeForm) {
        this.tenderOrganizeForm = tenderOrganizeForm;
    }

    public String getTenderMode() {
        return tenderMode;
    }

    public void setTenderMode(String tenderMode) {
        this.tenderMode = tenderMode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBidSectionName() {
        return bidSectionName;
    }

    public void setBidSectionName(String bidSectionName) {
        this.bidSectionName = bidSectionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getIndustriesType() {
        return industriesType;
    }

    public void setIndustriesType(String industriesType) {
        this.industriesType = industriesType;
    }

    public String getTenderProjectClassifyCode() {
        return tenderProjectClassifyCode;
    }

    public void setTenderProjectClassifyCode(String tenderProjectClassifyCode) {
        this.tenderProjectClassifyCode = tenderProjectClassifyCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getCompanyCodeType() {
        return companyCodeType;
    }

    public void setCompanyCodeType(String companyCodeType) {
        this.companyCodeType = companyCodeType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBasicInfoVersion() {
        return basicInfoVersion;
    }

    public void setBasicInfoVersion(String basicInfoVersion) {
        this.basicInfoVersion = basicInfoVersion;
    }

    public String getInfoReporterContactNumber() {
        return infoReporterContactNumber;
    }

    public void setInfoReporterContactNumber(String infoReporterContactNumber) {
        this.infoReporterContactNumber = infoReporterContactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBulletinCode() {
        return bulletinCode;
    }

    public void setBulletinCode(String bulletinCode) {
        this.bulletinCode = bulletinCode;
    }

    public String getBulletinName() {
        return bulletinName;
    }

    public void setBulletinName(String bulletinName) {
        this.bulletinName = bulletinName;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBulletinContent() {
        return bulletinContent;
    }

    public void setBulletinContent(String bulletinContent) {
        this.bulletinContent = bulletinContent;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getOriginalBulletinType() {
        return originalBulletinType;
    }

    public void setOriginalBulletinType(String originalBulletinType) {
        this.originalBulletinType = originalBulletinType;
    }

    public String getOriginalBulletinCode() {
        return originalBulletinCode;
    }

    public void setOriginalBulletinCode(String originalBulletinCode) {
        this.originalBulletinCode = originalBulletinCode;
    }

    public String getPublicityType() {
        return publicityType;
    }

    public void setPublicityType(String publicityType) {
        this.publicityType = publicityType;
    }

    public String getBulletinType() {
        return bulletinType;
    }

    public void setBulletinType(String bulletinType) {
        this.bulletinType = bulletinType;
    }

    public String getWinBidderCode() {
        return winBidderCode;
    }

    public void setWinBidderCode(String winBidderCode) {
        this.winBidderCode = winBidderCode;
    }

    public String getIsUnion() {
        return isUnion;
    }

    public void setIsUnion(String isUnion) {
        this.isUnion = isUnion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }
}
