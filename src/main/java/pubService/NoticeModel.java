package pubService;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2019/2/27 10:38$
 */
@Table("notice")
public class NoticeModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @描述:主键
     * @字段:ID CHAR(32)
     */
    @Name
    private java.lang.String id;

    /**
     * @描述:标题
     * @字段:DOC_TITLE VARCHAR(1000)
     */
    @Column("DOC_TITLE")
    private java.lang.String docTitle;

    /**
     * @描述:信息来源{0:采集(国内),90:国际标,130:国内标,131:必联网(国内),133:云平台}
     * @字段:DOC_SOURCE INT(10)
     */
    @Column("DOC_SOURCE")
    private java.lang.Integer docSource;

    /**
     * @描述:关键词
     * @字段:DOC_KEYWORDS VARCHAR(200)
     */
    @Column("DOC_KEYWORDS")
    private java.lang.String docKeywords;

    /**
     * @描述:发布",0 已发待审",1未发待审",2 已发布",3 审核未通过",4 撤回",5 撤回待审",6 取消撤回",7 撤回已否",8 同意撤回",9 重新发布",10 暂存",11
     * @字段:DOC_STATUS INT(10)
     */
    @Column("DOC_STATUS")
    private java.lang.Integer docStatus;

    /**
     * @描述:发布时间
     * @字段:PUB_DATE DATETIME(19)
     */
    @Column("PUB_DATE")
    private java.util.Date pubDate;

    /**
     * @描述:信息类型ID
     * @字段:INFOCLASS_ID VARCHAR(64)
     */
    @Column("INFOCLASS_ID")
    private java.lang.String infoclassId;

    /**
     * @描述:信息类型(具体值参考数据字典003)
     * @字段:INFOCLASS VARCHAR(256)
     */
    @Column
    private java.lang.String infoclass;

    /**
     * @描述:项目名称
     * @字段:PROJECT_NAME VARCHAR(128)
     */
    @Column("PROJECT_NAME")
    private java.lang.String projectName;

    /**
     * @描述:招标编号
     * @字段:BID_CODE VARCHAR(128)
     */
    @Column("BID_CODE")
    private java.lang.String bidCode;

    /**
     * @描述:招标文件领购开始时间
     * @字段:BID_BOOK_ENDTIME DATETIME(19)
     */
    @Column("BID_BOOK_ENDTIME")
    private java.util.Date bidBookStartTime;

    /**
     * @描述:招标文件领购截止时间
     * @字段:BID_BOOK_ENDTIME DATETIME(19)
     */
    @Column("BID_BOOK_ENDTIME")
    private java.util.Date bidBookEndTime;

    /**
     * @描述:标书文件领购条件
     * @字段:BID_CONDITION VARCHAR(500)
     */
    @Column("BID_CONDITION")
    private String bidCondition;

    /**
     * @描述:标书文件售卖方式 007001=网上，007002=网下
     * @字段:BID_BOOK_SELL_MODE VARCHAR(6)
     */
    @Column("BID_BOOK_SELL_MODE")
    private String bidBookSellMode;

    /**
     * @描述:标书文件获取方式 0=现场领购，1=在线获取， 0,1=两者都选
     * @字段:BID_BOOK_GET_METHOD VARCHAR(3)
     */
    @Column("BID_BOOK_GET_METHOD")
    private String bidBookGetMethod;

    /**
     * @描述:标书售价
     * @字段:BID_BOOK_SELL_PRICE DECIMAL(10, 2)
     */
    @Column("BID_BOOK_SELL_PRICE")
    private Double bidBookSellPrice;

    /**
     * @描述:标书售卖帐号在bankAccount系统中的id
     * @字段:BANK_ID VARCHAR(32)
     */
    @Column("BANK_ID")
    private String bankId;

    /**
     * @描述:标书售卖帐号银行名称
     * @字段:BANK_NAME VARCHAR(100)
     */
    @Column("BANK_NAME")
    private String bankName;

    /**
     * @描述:标书售卖银行帐号
     * @字段:BANK_NUMBER VARCHAR(50)
     */
    @Column("BANK_NUMBER")
    private String bankNumber;


    /**
     * @描述:公告结束时间
     * @字段:NOTICE_END_TIME DATETIME(19)
     */
    @Column("NOTICE_END_TIME")
    private java.util.Date noticeEndTime;

    /**
     * @描述:投标截止时间（开标时间）
     * @字段:END_DATE DATETIME(19)
     */
    @Column("END_DATE")
    private java.util.Date endDate;

    /**
     * @描述:招标人
     * @字段:TENDERER VARCHAR(256)
     */
    @Column
    private java.lang.String tenderer;

    /**
     * @描述:代理机构
     * @字段:AGENCY VARCHAR(64)
     */
    @Column
    private java.lang.String agency;

    /**
     * @描述:项目性质 国际招标 01 中央投资 02 建设工程 03 政府采购04 其他 05
     * @字段:PROJ_NATURE VARCHAR(64)
     */
    @Column("PROJ_NATURE")
    private java.lang.String projNature;

    /**
     * @描述:项目性质 国际招标 01 中央投资 02 建设工程 03 政府采购04 其他 05
     * @字段:PROJ_NATURE_CODE VARCHAR(64)
     */
    @Column("PROJ_NATURE_CODE")
    private java.lang.String projNatureCode;

    /**
     * @描述:
     * @字段:PROCUREMENT_TYPE VARCHAR(32)
     */
    @Column("PROCUREMENT_TYPE")
    private java.lang.String procurementType;

    /**
     * @描述:采购类型 公开招标 01 竞争性谈判 05 竞争性磋商 10 询价采购 04 单一来源采购 06 邀请 03
     * @字段:PROCUREMENT_TYPE_CODE VARCHAR(10)
     */
    @Column("PROCUREMENT_TYPE_CODE")
    private java.lang.String procurementTypeCode;

    /**
     * @描述:项目类别
     * @字段:PROJ_TYPE VARCHAR(64)
     */
    @Column("PROJ_TYPE")
    private java.lang.String projType;

    /**
     * @描述:项目类别code
     * @字段:PROJ_TYPE_CODE VARCHAR(64)
     */
    @Column("PROJ_TYPE_CODE")
    private java.lang.String projTypeCode;

    /**
     * @描述:项目创建时间
     * @字段:PROJECT_CREATE_TIME DATETIME(19)
     */
    @Column("PROJECT_CREATE_TIME")
    private Date projectCreateTime;

    /**
     * @描述:国家代码
     * @字段:COUNTRY_CODE VARCHAR(32)
     */
    @Column("COUNTRY_CODE")
    private java.lang.String countryCode;

    /**
     * @描述:国家
     * @字段:COUNTRY VARCHAR(256)
     */
    @Column
    private java.lang.String country;

    /**
     * @描述:地区代码
     * @字段:PROV_CODE VARCHAR(128)
     */
    @Column("PROV_CODE")
    private java.lang.String provCode;

    /**
     * @描述:地区名称
     * @字段:PROV_NAME VARCHAR(128)
     */
    @Column("PROV_NAME")
    private java.lang.String provName;

    /**
     * @描述:资金来源代码
     * @字段:FUND_SOURCE_CODE VARCHAR(64)
     */
    @Column("FUND_SOURCE_CODE")
    private java.lang.String fundSourceCode;

    /**
     * @描述:资金来源
     * @字段:FUND_SOURCE VARCHAR(128)
     */
    @Column("FUND_SOURCE")
    private java.lang.String fundSource;

    /**
     * @描述:新行业分类code
     * @字段:INDUSTRY_CODE VARCHAR(500)
     */
    @Column("INDUSTRY_CODE")
    private java.lang.String industryCode;

    /**
     * @描述:分类名称
     * @字段:INDUSTRY_NAME VARCHAR(1000)
     */
    @Column("INDUSTRY_NAME")
    private java.lang.String industryName;

    /**
     * @描述:拒绝原因
     * @字段:REJECT_REASON TEXT(65535)
     */
    @Column("REJECT_REASON")
    private java.lang.String rejectReason;

    /**
     * @描述:公告数据类型：0正式公告；1测试公告；-1无效公告
     * @字段:IS_TEST INT(10)
     */
    @Column("IS_TEST")
    private java.lang.Boolean isTest;

    /**
     * @描述:张岩CMS返回的公告ID
     * @字段:OLD_CMS_ID BIGINT(19)
     */
    @Column("OLD_CMS_ID")
    private java.lang.Long oldCmsId;

    /**
     * @描述:包组ID
     * @字段:PACKAGE_GROUP_ID CHAR(32)
     */
    @Column("PACKAGE_GROUP_ID")
    private java.lang.String packageGroupId;

    /**
     * @描述:创建时间
     * @字段:CREATE_TIME DATETIME(19)
     */
    @Column("CREATE_TIME")
    private java.util.Date createTime;

    /**
     * @描述:
     * @字段:CREATE_USER_ID CHAR(32)
     */
    @Column("CREATE_USER_ID")
    private java.lang.String createUserId;

    @Column("IS_LINE_BUY")
    private java.lang.Boolean isLineBuy;

    /**
     * @描述:更新人ID
     * @字段:UPDATE_USER_ID BIGINT(19);
     */
    @Column("UPDATE_USER_ID")
    private java.lang.String updateUserId;

    /**
     * @描述:
     * @字段:SYSTEM_STATUS INT(10)
     */
    @Column("SYSTEM_STATUS")
    private java.lang.Integer systemStatus;

    /**
     * @描述:更新时间
     * @字段:UPDATE_TIME DATETIME(19)
     */
    @Column("UPDATE_TIME")
    private java.util.Date updateTime;

    /**
     * @描述:租户ID
     * @字段:TENANT_ID CHAR(32)
     */
    @Column("TENANT_ID")
    private java.lang.String tenantId;

    /**
     * @描述:组织代码
     * @字段:ORG_CODE BIGINT(19)
     */
    @Column("ORG_CODE")
    private java.lang.Long orgCode;

    /**
     * @描述:提交OMS审批时间
     * @字段:NOTICE_COMMIT_TIME DATETIME(19)
     */
    @Column("NOTICE_COMMIT_TIME")
    private java.util.Date noticeCommitTime;

    /**
     * @描述:OMS审批操作时间
     * @字段:NOTICE_PROCUREMENT_TIME DATETIME(19)
     */
    @Column("NOTICE_PROCUREMENT_TIME")
    private java.util.Date noticeProcurementTime;

    /**
     * @描述:公告内容签名值
     * @字段:SIGNATURE text
     */
    @Column
    private java.lang.String signature;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public Integer getDocSource() {
        return docSource;
    }

    public void setDocSource(Integer docSource) {
        this.docSource = docSource;
    }

    public String getDocKeywords() {
        return docKeywords;
    }

    public void setDocKeywords(String docKeywords) {
        this.docKeywords = docKeywords;
    }

    public Integer getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(Integer docStatus) {
        this.docStatus = docStatus;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getInfoclassId() {
        return infoclassId;
    }

    public void setInfoclassId(String infoclassId) {
        this.infoclassId = infoclassId;
    }

    public String getInfoclass() {
        return infoclass;
    }

    public void setInfoclass(String infoclass) {
        this.infoclass = infoclass;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBidCode() {
        return bidCode;
    }

    public void setBidCode(String bidCode) {
        this.bidCode = bidCode;
    }

    public Date getBidBookStartTime() {
        return bidBookStartTime;
    }

    public void setBidBookStartTime(Date bidBookStartTime) {
        this.bidBookStartTime = bidBookStartTime;
    }

    public Date getBidBookEndTime() {
        return bidBookEndTime;
    }

    public void setBidBookEndTime(Date bidBookEndTime) {
        this.bidBookEndTime = bidBookEndTime;
    }

    public String getBidCondition() {
        return bidCondition;
    }

    public void setBidCondition(String bidCondition) {
        this.bidCondition = bidCondition;
    }

    public String getBidBookSellMode() {
        return bidBookSellMode;
    }

    public void setBidBookSellMode(String bidBookSellMode) {
        this.bidBookSellMode = bidBookSellMode;
    }

    public String getBidBookGetMethod() {
        return bidBookGetMethod;
    }

    public void setBidBookGetMethod(String bidBookGetMethod) {
        this.bidBookGetMethod = bidBookGetMethod;
    }

    public Double getBidBookSellPrice() {
        return bidBookSellPrice;
    }

    public void setBidBookSellPrice(Double bidBookSellPrice) {
        this.bidBookSellPrice = bidBookSellPrice;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public Date getNoticeEndTime() {
        return noticeEndTime;
    }

    public void setNoticeEndTime(Date noticeEndTime) {
        this.noticeEndTime = noticeEndTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTenderer() {
        return tenderer;
    }

    public void setTenderer(String tenderer) {
        this.tenderer = tenderer;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getProjNature() {
        return projNature;
    }

    public void setProjNature(String projNature) {
        this.projNature = projNature;
    }

    public String getProjNatureCode() {
        return projNatureCode;
    }

    public void setProjNatureCode(String projNatureCode) {
        this.projNatureCode = projNatureCode;
    }

    public String getProcurementType() {
        return procurementType;
    }

    public void setProcurementType(String procurementType) {
        this.procurementType = procurementType;
    }

    public String getProcurementTypeCode() {
        return procurementTypeCode;
    }

    public void setProcurementTypeCode(String procurementTypeCode) {
        this.procurementTypeCode = procurementTypeCode;
    }

    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    public String getProjTypeCode() {
        return projTypeCode;
    }

    public void setProjTypeCode(String projTypeCode) {
        this.projTypeCode = projTypeCode;
    }

    public Date getProjectCreateTime() {
        return projectCreateTime;
    }

    public void setProjectCreateTime(Date projectCreateTime) {
        this.projectCreateTime = projectCreateTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getFundSourceCode() {
        return fundSourceCode;
    }

    public void setFundSourceCode(String fundSourceCode) {
        this.fundSourceCode = fundSourceCode;
    }

    public String getFundSource() {
        return fundSource;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public Long getOldCmsId() {
        return oldCmsId;
    }

    public void setOldCmsId(Long oldCmsId) {
        this.oldCmsId = oldCmsId;
    }

    public String getPackageGroupId() {
        return packageGroupId;
    }

    public void setPackageGroupId(String packageGroupId) {
        this.packageGroupId = packageGroupId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Boolean getLineBuy() {
        return isLineBuy;
    }

    public void setLineBuy(Boolean lineBuy) {
        isLineBuy = lineBuy;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Integer systemStatus) {
        this.systemStatus = systemStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Long orgCode) {
        this.orgCode = orgCode;
    }

    public Date getNoticeCommitTime() {
        return noticeCommitTime;
    }

    public void setNoticeCommitTime(Date noticeCommitTime) {
        this.noticeCommitTime = noticeCommitTime;
    }

    public Date getNoticeProcurementTime() {
        return noticeProcurementTime;
    }

    public void setNoticeProcurementTime(Date noticeProcurementTime) {
        this.noticeProcurementTime = noticeProcurementTime;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}


