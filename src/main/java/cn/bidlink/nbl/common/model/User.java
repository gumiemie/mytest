package cn.bidlink.nbl.common.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description nbl_user库中的user表
 * @date 2018/11/13 10:42$
 */
@Table("user")
public class User {
    /**
     * @描述:主键
     * @字段:ID CHAR(32)
     */
    @Name
    private java.lang.String id;

    /**
     * @描述:组织ID
     * @字段:ORG_ID CHAR(32)
     */
    @Column("ORG_ID")
    private java.lang.String orgId;

    /**
     * @描述:姓名（真实姓名）
     * @字段:NAME VARCHAR(100)
     */
    @Column
    private java.lang.String name;

    /**
     * @描述:类型（）
     * @字段:TYPE INT(10)
     */
    @Column
    private java.lang.Integer type;

    /**
     * @描述:登录名
     * @字段:LOGIN_NAME VARCHAR(100)
     */
    @Column("LOGIN_NAME")
    private java.lang.String loginName;

    /**
     * @描述:登陆邮箱
     * @字段:LOGIN_EMIAL VARCHAR(100)
     */
    @Column("LOGIN_EMIAL")
    private java.lang.String loginEmail;

    /**
     * @描述:登陆手机
     * @字段:LOGIN_MOBILE VARCHAR(20)
     */
    @Column("LOGIN_MOBILE")
    private java.lang.String loginMobile;

    /**
     * @描述:年龄
     * @字段:AGE INT(10)
     */
    @Column
    private java.lang.Integer age;

    /**
     * @描述:性别（女=1，男=2）
     * @字段:SEX SMALLINT(5)
     */
    @Column
    private java.lang.Integer sex;

    /**
     * @描述:固定电话
     * @字段:TEL VARCHAR(20)
     */
    @Column
    private java.lang.String tel;

    /**
     * @描述:传真
     * @字段:FAX VARCHAR(50)
     */
    @Column
    private java.lang.String fax;

    /**
     * @描述:注册来源
     * @字段:REGISTER_SOURCE VARCHAR(20)
     */
    @Column("REGISTER_SOURCE")
    private java.lang.String registerSource;

    /**
     * @描述:用户来源
     * @字段:USER_ORIGIN VARCHAR(20)
     */
    @Column("USER_ORIGIN")
    private java.lang.String userOrigin;

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

    /**
     * @描述:是否是主账号
     * @字段:IS_SUBJECT SMALLINT(5)
     */
    @Column("IS_SUBJECT")
    private java.lang.Integer isSubject;

    /**
     * @描述:状态
     * @字段:STATUS INT(10)
     */
    @Column
    private java.lang.Integer status;

    @Column("OLD_COMP_ID")
    private java.lang.Long oldCompanyId;

    /**
     * @描述:状态（是否测试数据）
     * @字段:IS_TEST SMALLINT(5)
     */
    @Column("IS_TEST")
    private java.lang.Integer isTest;

    /**
     * @描述:
     * @字段:OLD_ID BIGINT(19)
     */
    @Column("OLD_ID")
    private java.lang.Long oldId;

    @Column("TENANT_ID")
    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginMobile() {
        return loginMobile;
    }

    public void setLoginMobile(String loginMobile) {
        this.loginMobile = loginMobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(String registerSource) {
        this.registerSource = registerSource;
    }

    public String getUserOrigin() {
        return userOrigin;
    }

    public void setUserOrigin(String userOrigin) {
        this.userOrigin = userOrigin;
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

    public Integer getIsSubject() {
        return isSubject;
    }

    public void setIsSubject(Integer isSubject) {
        this.isSubject = isSubject;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getOldCompanyId() {
        return oldCompanyId;
    }

    public void setOldCompanyId(Long oldCompanyId) {
        this.oldCompanyId = oldCompanyId;
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
