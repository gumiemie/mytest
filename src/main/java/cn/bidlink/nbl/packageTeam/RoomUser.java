package cn.bidlink.nbl.packageTeam;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/10/25 10:29$
 */
public class RoomUser {
    @Name
    private String id;
    @Column("room_id")
    private String roomId;
    @Column("user_id")
    private String userId;
    @Column("user_type")
    private String userType;
    @Column("user_name")
    private String userName;
    @Column("user_phone")
    private String userPhone;
    @Column("user_email")
    private String userEmail;
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
    @Column("source")
    private Integer source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
