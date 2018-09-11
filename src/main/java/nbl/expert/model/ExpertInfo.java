/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package nbl.expert.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * <code>ExpExpertInfo</code>数据库映射.
 *
 * @table 	: exp_expert_info   
 * @version : Ver 1.0
 * @author	: <a href="junjiezhang@ebnew.com">zhangjunjie</a>
 * @date	:  2015-11-10 上午10:46:39  
 */
@Table("exp_expert_info")
public class ExpertInfo implements Serializable {


    private static final long serialVersionUID = -8154931185609423595L;
    /**
     * @描述:id     
     * @字段:ID CHAR(32)  
     */
    @Name
	private String id;

	/**
     * @描述:姓名
     * @字段:NAME VARCHAR(100)
     */
	@Column
	private String name;

	/**
     * @描述:编号
     * @字段:CODE CHAR(17)
     */
	@Column
	private String code;

	/**
     * @描述:专家来源
     * @字段:APP_ID VARCHAR(50)
     */
	@Column("app_id")
	private String appId;

	/**
     * @描述:电子邮箱
     * @字段:EMAIL VARCHAR(100)
     */
	@Column
	private String email;

	/**
     * @描述:手机
     * @字段:MOBILE_PHONE VARCHAR(20)
     */
	@Column("MOBILE_PHONE")
	private String mobilePhone;

	/**
     * @描述:性别 0:未知,1:男性,2:女性,9:未说明
     * @字段:SEX TINYINT(3)
     */
	@Column
	private Integer sex;

	/**
     * @描述:身份证件类型
     * @字段:CERTIFICATE_TYPE CHAR(2)
     */
	@Column("CERTIFICATE_TYPE")
	private String certificateType;

	/**
     * @描述:身份证件号码
     * @字段:CERTIFICATE_NUM VARCHAR(18)
     */
	@Column("CERTIFICATE_NUM")
	private String certificateNum;

	/**
     * @描述:出生年月
     * @字段:BIRTHDATE DATETIME(19)
     */
	@Column
	private java.util.Date birthdate;

	/** 非数据库字段，查询时使用 */
	private java.util.Date birthdateBegin;

	/** 非数据库字段，查询时使用 */
	private java.util.Date birthdateEnd;

	/**
     * @描述:所在行政区域代码
     * @字段:AREA_CODE CHAR(6)
     */
    @Column("AREA_CODE")
	private String areaCode;

	/**
     * @描述:最后毕业院校
     * @字段:GRADUATE_SCHOOL VARCHAR(100)
     */
    @Column("GRADUATE_SCHOOL")
	private String graduateSchool;

	/**
     * @描述:专业
     * @字段:MAJOR VARCHAR(100)
     */
    @Column
	private String major;

	/**
     * @描述:最高学历
     * @字段:TOP_EDUCATION VARCHAR(20)
     */
    @Column("TOP_EDUCATION")
	private String topEducation;

	/**
     * @描述:学位
     * @字段:DEGREE VARCHAR(20)
     */
    @Column
	private String degree;

	/**
     * @描述:联系电话
     * @字段:PHONE VARCHAR(100)
     */
    @Column
	private String phone;

	/**
     * @描述:传真号码
     * @字段:FAX VARCHAR(100)
     */
	@Column
	private String fax;

	/**
     * @描述:家庭电话
     * @字段:HOME_PHONE VARCHAR(100)
     */
	@Column("HOME_PHONE")
	private String homePhone;

	/**
     * @描述:通讯地址
     * @字段:ADDRESS VARCHAR(100)
     */
	@Column
	private String address;

	/**
     * @描述:邮政编码
     * @字段:ZIP_CODE CHAR(6)
     */
	@Column("ZIP_CODE")
	private String zipCode;

	/**
     * @描述:工作单位
     * @字段:WORK_UNIT VARCHAR(100)
     */
    @Column("WORK_UNIT")
	private String workUnit;

	/**
     * @描述:是否在职 0:否 1:是
     * @字段:ON_JOB CHAR(1)
     */
	@Column("ON_JOB")
	private Integer onJob;

	/**
     * @描述:职务
     * @字段:DUTY VARCHAR(100)
     */
	@Column
	private String duty;

	/**
     * @描述:工作简历
     * @字段:RESUME LONGTEXT(2147483647)
     */
    @Column
	private String resume;

	/**
     * @描述:职称
     * @字段:TITLE VARCHAR(50)
     */
    @Column
	private String title;

	/**
     * @描述:职业资格序列
     * @字段:OCCUP_QUAL_SEQ VARCHAR(50)
     */
	@Column("OCCUP_QUAL_SEQ")
	private String occupQualSeq;

	/**
     * @描述:职业资格等级
     * @字段:OCCUP_QUAL_LEVEL VARCHAR(50)
     */
	@Column("OCCUP_QUAL_LEVEL")
	private String occupQualLevel;

	/**
     * @描述:从业年限
     * @字段:WORK_LIFE TINYINT(3)
     */
	@Column("WORK_LIFE")
	private Integer workLife;

	/**
     * @描述:专家基本状态  正常:1停用:3，此处是专家本身属性，不与企业/招标机构关联
     * @字段:STATUS TINYINT(3)
     */
	@Column
	private Integer status;

	/**
     * @描述:是否为公共专家
     * @字段:IS_COMMON TINYINT(3)
     */
	@Column("IS_COMMON")
	private Integer isCommon;

	/**
     * @描述:是否激活 0:未激活 1:已激活
     * @字段:IS_ACTIVE TINYINT(3)
     */
	@Column("IS_ACTIVE")
	private Integer isActive;

	/**
     * @描述:创建用户id
     * @字段:CREATE_USER_ID CHAR(32)
     */
	@Column("CREATE_USER_ID")
	private String createUserId;

	/**
     * @描述:创建时间
     * @字段:CREATE_TIME DATETIME(19)
     */
	@Column("CREATE_TIME")
	private java.util.Date createTime;

	/** 非数据库字段，查询时使用 */
	private java.util.Date createTimeBegin;

	/** 非数据库字段，查询时使用 */
	private java.util.Date createTimeEnd;

	/**
     * @描述:最后更新用户id
     * @字段:UPDATE_USER_ID CHAR(32)
     */
	@Column("UPDATE_USER_ID")
	private String updateUserId;

	/**
     * @描述:最后更新时间
     * @字段:UPDATE_TIME DATETIME(19)
     */
	@Column("UPDATE_TIME")
	private java.util.Date updateTime;

	/** 非数据库字段，查询时使用 */
	private java.util.Date updateTimeBegin;

	/** 非数据库字段，查询时使用 */
	private java.util.Date updateTimeEnd;

	/**
     * @描述:组织编号
     * @字段:ORG_CODE INT(10)
     */
	@Column("ORG_CODE")
	private Long orgCode;

	/**
     * @描述:租户id
     * @字段:TENANT_ID CHAR(32)
     */
	@Column("TENANT_ID")
	private String tenantId;

	@Column("CENTER_USER_ID")
    private Long centerUserId;

	/**
	 * 审计状态
	 */
	@Column("AUDIT_STATUS")
    private Integer auditStatus;

	@Column("OLD_ID")
	private Long oldId;

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getCenterUserId() {
        return centerUserId;
    }

    public void setCenterUserId(Long centerUserId) {
        this.centerUserId = centerUserId;
    }

    /**
	 *
	 */
	public ExpertInfo(){
	}

	/**
	 * @param id id
	 */
	public ExpertInfo(String id){
		this.id = id;
	}

	/**
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param name 姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 姓名
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param code 编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 编号
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param appId 专家来源
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return 专家来源
	 */
	public String getAppId() {
		return this.appId;
	}

	/**
	 * @param email 电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return 电子邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param mobilePhone 手机
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return 手机
	 */
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	/**
	 * @param sex 性别 0:未知,1:男性,2:女性,9:未说明
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return 性别 0:未知,1:男性,2:女性,9:未说明
	 */
	public Integer getSex() {
		return this.sex;
	}

	/**
	 * @param certificateType 身份证件类型
	 */
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	/**
	 * @return 身份证件类型
	 */
	public String getCertificateType() {
		return this.certificateType;
	}

	/**
	 * @param certificateNum 身份证件号码
	 */
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	/**
	 * @return 身份证件号码
	 */
	public String getCertificateNum() {
		return this.certificateNum;
	}

	/**
	 * @param birthdate 出生年月
	 */
	public void setBirthdate(java.util.Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return 出生年月
	 */
	public java.util.Date getBirthdate() {
		return this.birthdate;
	}

	/**
	 * @param birthdateBegin 出生年月开始
	 */
    public void setBirthdateBegin(java.util.Date birthdateBegin) {
		this.birthdateBegin = birthdateBegin;
	}

    /**
	 * @return 出生年月开始
	 */
	public java.util.Date getBirthdateBegin() {
		return this.birthdateBegin;
	}

	/**
	 * @param birthdateEnd 出生年月结束
	 */
	public void setBirthdateEnd(java.util.Date birthdateEnd) {
		this.birthdateEnd = birthdateEnd;
	}

	/**
	 * @return 出生年月结束
	 */
	public java.util.Date getBirthdateEnd() {
		return this.birthdateEnd;
	}

	/**
	 * @param areaCode 所在行政区域代码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return 所在行政区域代码
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * @param graduateSchool 最后毕业院校
	 */
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	/**
	 * @return 最后毕业院校
	 */
	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	/**
	 * @param major 专业
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return 专业
	 */
	public String getMajor() {
		return this.major;
	}

	/**
	 * @param topEducation 最高学历
	 */
	public void setTopEducation(String topEducation) {
		this.topEducation = topEducation;
	}

	/**
	 * @return 最高学历
	 */
	public String getTopEducation() {
		return this.topEducation;
	}

	/**
	 * @param degree 学位
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return 学位
	 */
	public String getDegree() {
		return this.degree;
	}

	/**
	 * @param phone 联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return 联系电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * @param fax 传真号码
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return 传真号码
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * @param homePhone 家庭电话
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @return 家庭电话
	 */
	public String getHomePhone() {
		return this.homePhone;
	}

	/**
	 * @param address 通讯地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return 通讯地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @param zipCode 邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return 邮政编码
	 */
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * @param workUnit 工作单位
	 */
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	/**
	 * @return 工作单位
	 */
	public String getWorkUnit() {
		return this.workUnit;
	}

	/**
	 * @param onJob 是否在职
	 */
	public void setOnJob(Integer onJob) {
		this.onJob = onJob;
	}

	/**
	 * @return 是否在职
	 */
	public Integer getOnJob() {
		return this.onJob;
	}

	/**
	 * @param duty 职务
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}

	/**
	 * @return 职务
	 */
	public String getDuty() {
		return this.duty;
	}

	/**
	 * @param resume 工作简历
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	/**
	 * @return 工作简历
	 */
	public String getResume() {
		return this.resume;
	}

	/**
	 * @param title 职称
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return 职称
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param occupQualSeq 职业资格序列
	 */
	public void setOccupQualSeq(String occupQualSeq) {
		this.occupQualSeq = occupQualSeq;
	}

	/**
	 * @return 职业资格序列
	 */
	public String getOccupQualSeq() {
		return this.occupQualSeq;
	}

	/**
	 * @param occupQualLevel 职业资格等级
	 */
	public void setOccupQualLevel(String occupQualLevel) {
		this.occupQualLevel = occupQualLevel;
	}

	/**
	 * @return 职业资格等级
	 */
	public String getOccupQualLevel() {
		return this.occupQualLevel;
	}

	/**
	 * @param workLife 从业年限
	 */
	public void setWorkLife(Integer workLife) {
		this.workLife = workLife;
	}

	/**
	 * @return 从业年限
	 */
	public Integer getWorkLife() {
		return this.workLife;
	}

	/**
	 * @param status 专家基本状态 正常/停用/请假，此处是专家本身属性，不与企业/招标机构关联 0:未激活 1:正常 2:请假 3:停用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return 专家基本状态 正常/停用/请假，此处是专家本身属性，不与企业/招标机构关联 0:未激活 1:正常 2:请假 3:停用
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * @param isCommon 是否为公共专家
	 */
	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
	}

	/**
	 * @return 是否为公共专家
	 */
	public Integer getIsCommon() {
		return this.isCommon;
	}

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
	 * @param createUserId 创建用户id
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return 创建用户id
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param createTime 创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @param createTimeBegin 创建时间开始
	 */
    public void setCreateTimeBegin(java.util.Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

    /**
	 * @return 创建时间开始
	 */
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}

	/**
	 * @param createTimeEnd 创建时间结束
	 */
	public void setCreateTimeEnd(java.util.Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
	 * @return 创建时间结束
	 */
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	/**
	 * @param updateUserId 最后更新用户id
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * @return 最后更新用户id
	 */
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * @param updateTime 最后更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 最后更新时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * @param updateTimeBegin 最后更新时间开始
	 */
    public void setUpdateTimeBegin(java.util.Date updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
	}

    /**
	 * @return 最后更新时间开始
	 */
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}

	/**
	 * @param updateTimeEnd 最后更新时间结束
	 */
	public void setUpdateTimeEnd(java.util.Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	/**
	 * @return 最后更新时间结束
	 */
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

	/**
	 * @param orgCode 组织编号
	 */
	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return 组织编号
	 */
	public Long getOrgCode() {
		return this.orgCode;
	}

	/**
	 * @param tenantId 租户id
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return 租户id
	 */
	public String getTenantId() {
		return this.tenantId;
	}

	public Long getOldId() {
		return oldId;
	}

	public void setOldId(Long oldId) {
		this.oldId = oldId;
	}
}
