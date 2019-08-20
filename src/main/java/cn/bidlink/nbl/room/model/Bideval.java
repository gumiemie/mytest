/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package cn.bidlink.nbl.room.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("bideval")
public class Bideval {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @描述:主键ID     
     * @字段:ID CHAR(32)  
     */
	@Name
	private String id;
	/**
	 * @描述:PACKAGE_ID
	 * @字段:ID CHAR(32)
	 */
	@Column("PACKAGE_ID")
	private String packageId;
	/**
     * @描述:评标时间     
     * @字段:BIDEVAL_TIME DATETIME(19)  
     */
	@Column("BIDEVAL_TIME")
	private Date bidevalTime;

	/**
     * @描述:评标时长
     * @字段:BIDEVAL_DURATION DECIMAL(5)
     */
	@Column("BIDEVAL_DURATION")
	private java.math.BigDecimal bidevalDuration;

	/**
     * @描述:评标地点
     * @字段:BIDEVAL_ADDRESS VARCHAR(500)
     */
	@Column("BIDEVAL_ADDRESS")
	private String bidevalAddress;

	/**
     * @描述:评标大厅ID
     * @字段:ROOM_ID CHAR(32)
     */
	@Column("ROOM_ID")
	private String roomId;

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
	private Boolean isTest;
	/**
	 * @描述:状态:０有效,１无效
	 * @字段:systemStatus INT(3)
	 */
	@Column("SYSTEM_STATUS")
	private Integer systemStatus;

	private Integer source;

	private String oldId;

	/**
	 *
	 */
	public Bideval(){
	}

	/**
	 * @param id 主键ID
	 */
	public Bideval(String id){
		this.id = id;
	}

	/**
	 * @param id 主键ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 主键ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param bidevalTime 评标时间
	 */
	public void setBidevalTime(Date bidevalTime) {
		this.bidevalTime = bidevalTime;
	}

	/**
	 * @return 评标时间
	 */
	public Date getBidevalTime() {
		return this.bidevalTime;
	}

	/**
	 * @param bidevalDuration 评标时长
	 */
	public void setBidevalDuration(java.math.BigDecimal bidevalDuration) {
		this.bidevalDuration = bidevalDuration;
	}

	/**
	 * @return 评标时长
	 */
	public java.math.BigDecimal getBidevalDuration() {
		return this.bidevalDuration;
	}

	/**
	 * @param bidevalAddress 评标地点
	 */
	public void setBidevalAddress(String bidevalAddress) {
		this.bidevalAddress = bidevalAddress;
	}

	/**
	 * @return 评标地点
	 */
	public String getBidevalAddress() {
		return this.bidevalAddress;
	}

	/**
	 * @param roomId 评标大厅ID
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return 评标大厅ID
	 */
	public String getRoomId() {
		return this.roomId;
	}

	/**
	 * @param tenantId 租户ID
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return 租户ID
	 */
	public String getTenantId() {
		return this.tenantId;
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

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public Integer getSystemStatus() {
		return systemStatus;
	}

	public void setSystemStatus(Integer systemStatus) {
		this.systemStatus = systemStatus;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Boolean getTest() {
		return isTest;
	}

	public void setTest(Boolean test) {
		isTest = test;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}
}
