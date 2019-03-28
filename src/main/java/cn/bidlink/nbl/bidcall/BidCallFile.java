package cn.bidlink.nbl.bidcall;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description bidCallFile映射类
 * @date 2018/9/29 14:04$
 */
@Table("bidcall_files")
public class BidCallFile {

    /**
     * @描述:uuid
     * @字段:ID CHAR(32)
     */
    @Name
    private java.lang.String id;

    /**
     * @描述:名称
     * @字段:NAME VARCHAR(255)
     */
    @Column
    private java.lang.String name;

    /**
     * @描述:文档id
     * @字段:DOC_ID CHAR(32)
     */
    @Column("DOC_ID")
    private java.lang.String docId;

    /**
     * @描述: 云存储文件md5值
     * @字段:DOC_MD5 CHAR(32)
     */
    @Column("DOC_MD5")
    private java.lang.String docMd5;


    /**
     * 招标文件的签名值
     */
    @Column("SIGN_KEY")
    private String signKey;


    /**
     * @描述:文件类型 1：招标文件 2：标书（多个招标文件压缩件）3：公告附件
     * @字段:TYPE INT(10)
     */
    @Column
    private java.lang.Integer type;

    /**
     * @描述:状态 1: 打开 0：关闭
     * @字段:STATUS INT(10)
     */
    @Column
    private java.lang.Integer status;

    /**
     * @描述:包组id
     * @字段:PACKAGE_GROUP_ID CHAR(32)
     */
    @Column("PACKAGE_GROUP_ID")
    private java.lang.String packageGroupId;

    /**
     * @描述:包id
     * @字段:PACKAGE_ID CHAR(32)
     */
    @Column("PACKAGE_ID")
    private java.lang.String packageId;

    /**
     * @描述:租户ID
     * @字段:TENANT_ID VARCHAR(32)
     */
    @Column("TENANT_ID")
    private String tenantId;
    /**
     * @描述:部门
     * @字段:ORG_CODE BIGINT(19)
     */
    @Column("ORG_CODE")
    private Long orgCode;
    /**
     * @描述:创建人
     * @字段:CREATE_USER_ID CHAR(32)
     */
    @Column("CREATE_USER_ID")
    private String createUserId;
    /**
     * @描述:创建时间
     * @字段:CREATE_TIME DATETIME(19)
     */
    @Column("CREATE_TIME")
    private Date createTime;
    /**
     * @描述:最后更新人
     * @字段:UPDATE_USER_ID CHAR(32)
     */
    @Column("UPDATE_USER_ID")
    private String updateUserId;
    /**
     * @描述:更新时间
     * @字段:UPDATE_TIME DATETIME(19)
     */
    @Column("UPDATE_TIME")
    private Date updateTime;
    /**
     * @描述:是否测试
     * @字段:isTest tinyint(1)
     */
    @Column("IS_TEST")
    private java.lang.Boolean isTest;
    /**
     * @描述:状态:０有效,１无效
     * @字段:systemStatus INT(3)
     */
    @Column("SYSTEM_STATUS")
    private java.lang.Integer systemStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocMd5() {
        return docMd5;
    }

    public void setDocMd5(String docMd5) {
        this.docMd5 = docMd5;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public Integer getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(Integer systemStatus) {
        this.systemStatus = systemStatus;
    }
}
