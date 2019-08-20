package cn.bidlink.nbl.packageTeam;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description package_team表映射类.
 * @date 2017/10/24 11:34$
 */
public class PackageTeam {
    @Column("EXPERT_COUNT")
    private Integer expertCount;
    @Column("TENDER_REPRESENT_COUNT")
    private Integer tenderRepresentCount;
    @Name
    private String id;
    @Column("PACKAGE_ID")
    private String packageId;
    @Column("PACKAGE_GROUP_ID")
    private String packageGroupId;
    @Column
    private String type;
    @Column("work_time")
    private java.util.Date workTime;
    @Column("work_address")
    private String workAddress;
    @Column("work_duration")
    private Float workDuration;
    @Column("create_type")
    private Integer createType;
    @Column("create_user_id")
    private String createUserId;
    @Column("create_time")
    private java.util.Date createTime;
    @Column("update_user_id")
    private String updateUserId;
    @Column("update_time")
    private java.util.Date updateTime;
    @Column("tenant_id")
    private String tenantId;
    @Column("org_code")
    private Long orgCode;
    @Column("is_test")
    private Integer isTest;
    @Column("system_status")
    private Integer systemStatus;
    @Column
    private Integer source;

    public Integer getExpertCount() {
        return expertCount;
    }

    public void setExpertCount(Integer expertCount) {
        this.expertCount = expertCount;
    }

    public Integer getTenderRepresentCount() {
        return tenderRepresentCount;
    }

    public void setTenderRepresentCount(Integer tenderRepresentCount) {
        this.tenderRepresentCount = tenderRepresentCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageGroupId() {
        return packageGroupId;
    }

    public void setPackageGroupId(String packageGroupId) {
        this.packageGroupId = packageGroupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public Float getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(Float workDuration) {
        this.workDuration = workDuration;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }

    public Integer getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Integer systemStatus) {
        this.systemStatus = systemStatus;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

}
