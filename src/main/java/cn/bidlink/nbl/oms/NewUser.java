package cn.bidlink.nbl.oms;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 新oms用户映射类
 * @date 2018/4/27 16:16$
 */
@Table("system_user")
public class NewUser {

    /**
     * @描述:主键
     * @字段:ID CHAR(32)
     */
    @Name
    private java.lang.String id;

    /**
     * @描述:用户名
     * @字段:USER_NAME VARCHAR(20)
     */
    @Column("USER_NAME")
    private java.lang.String userName;

    /**
     * @描述:用户邮箱
     * @字段:USER_EMAIL VARCHAR(100)
     */
    @Column("USER_EMAIL")
    private java.lang.String userEmail;

    /**
     * @描述:用户手机
     * @字段:USER_MOBILE VARCHAR(100)
     */
    @Column("USER_MOBILE")
    private java.lang.String userMobile;

    /**
     * @描述:用户密码
     * @字段:USER_PWD VARCHAR(64)
     */
    @Column("USER_PWD")
    private java.lang.String userPwd;

    /**
     * @描述:启用禁用（1：启用；2：禁用）
     * @字段:ENABLE_DISABLE TINYINT(3)
     */
    @Column("ENABLE_DISABLE")
    private java.lang.Integer enableDisable;

    /**
     * @描述:最后登陆IP
     * @字段:LAST_LOGIN_IP VARCHAR(50)
     */
    @Column("LAST_LOGIN_IP")
    private java.lang.String lastLoginIp;

    /**
     * @描述:最后登陆时间
     * @字段:LAST_LOGIN_TIME DATETIME(19)
     */
    @Column("LAST_LOGIN_TIME")
    private java.util.Date lastLoginTime;

    /**
     * @描述:创建时间
     * @字段:CREATE_TIME DATETIME(19)
     */
    @Column("CREATE_TIME")
    private java.util.Date createTime;

    /**
     * @描述:更新时间
     * @字段:UPDATE_TIME DATETIME(19)
     */
    @Column("UPDATE_TIME")
    private java.util.Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getEnableDisable() {
        return enableDisable;
    }

    public void setEnableDisable(Integer enableDisable) {
        this.enableDisable = enableDisable;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
