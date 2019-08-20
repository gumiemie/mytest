package cn.bidlink.nbl.packageTeam;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description room表 映射表
 * @date 2017/10/25 10:16$
 */
@Table("cn/bidlink/nbl/room")
public class Room {
    @Name
    private String id;
    @Column("package_group_id")
    private String packageGroupId;
    @Column("package_id")
    private String packageId;
    @Column("romm_type")
    private Integer roomType;
    @Column("room_status")
    private Integer roomStatus;
    @Column("OPEN_BID_EVAL_METHOD")
    private String openBidEvalMethod;
    @Column("CREATE_USER_ID")
    private String createUserId;
    @Column("CREATE_TIME")
    private Date createTime;
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
    @Column("source")
    private Integer source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageGroupId() {
        return packageGroupId;
    }

    public void setPackageGroupId(String packageGroupId) {
        this.packageGroupId = packageGroupId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getOpenBidEvalMethod() {
        return openBidEvalMethod;
    }

    public void setOpenBidEvalMethod(String openBidEvalMethod) {
        this.openBidEvalMethod = openBidEvalMethod;
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
