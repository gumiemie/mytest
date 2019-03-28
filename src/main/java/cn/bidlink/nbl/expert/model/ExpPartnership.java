/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package cn.bidlink.nbl.expert.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * <code>ExpPartnership</code>数据库映射.
 *
 * @table 	: exp_partnership   
 * @version : Ver 1.0
 * @author	: <a href="junjiezhang@ebnew.com">zhangjunjie</a>
 * @date	:  2016-03-10 上午09:25:12  
 */
@Table("exp_partnership")
public class ExpPartnership {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @描述:主键id     
     * @字段:ID CHAR(32)  
     */
	@Name
	private String id;

	/**
     * @描述:专家id     
     * @字段:EXPERT_ID CHAR(32)  
     */
	@Column("EXPERT_ID")
	private String expertId;

	/**
     * @描述:租户id     
     * @字段:TENANT_ID CHAR(32)  
     */
	@Column("TENANT_ID")
	private String tenantId;

	/**
     * @描述:企业/招标机构对专家的启用/禁用状态   0:启用 1:禁用     
     * @字段:STATUS TINYINT(3)  
     */
	@Column
	private Integer status;

	/**
     * @描述:审批状态 0:待审批 1:审批中2:审批不通过3:审批通过     
     * @字段:AUDIT_STATUS TINYINT(3)  
     */
	@Column("AUDIT_STATUS")
	private Integer auditStatus;

	/**
     * @描述:来源 1:内部 2:外部     
     * @字段:ORIGIN TINYINT(3)  
     */
	@Column
	private Integer origin;

	/**
     * @描述:     
     * @字段:REASON VARCHAR(500)  
     */
	@Column
	private String reason;

	/**
     * @描述:     
     * @字段:CREATE_USER_ID CHAR(32)  
     */
	@Column("CREATE_USER_ID")
	private String createUserId;

	/**
     * @描述:     
     * @字段:UPDATE_USER_ID CHAR(32)  
     */
	@Column("UPDATE_USER_ID")
	private String updateUserId;

	/**
     * @描述:     
     * @字段:CREATE_TIME DATETIME(19)  
     */
	@Column("CREATE_TIME")
	private java.util.Date createTime;
	
	/** 非数据库字段，查询时使用 */
	private java.util.Date createTimeBegin;
	
	/** 非数据库字段，查询时使用 */
	private java.util.Date createTimeEnd;

	/**
     * @描述:     
     * @字段:UPDATE_TIME DATETIME(19)  
     */
	@Column("UPDATE_TIME")
	private java.util.Date updateTime;
	
	/** 非数据库字段，查询时使用 */
	private java.util.Date updateTimeBegin;
	
	/** 非数据库字段，查询时使用 */
	private java.util.Date updateTimeEnd;

	/**
     * @描述:     
     * @字段:ORG_CODE BIGINT(19)  
     */
	@Column("ORG_CODE")
	private Long orgCode;

	/**
	 *
	 */
	public ExpPartnership(){
	}

	/**
	 * @param id 主键id
	 */
	public ExpPartnership(String id){
		this.id = id;
	}
	
	/**
	 * @param id 主键id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return 主键id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * @param expertId 专家id
	 */
	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}
	
	/**
	 * @return 专家id
	 */
	public String getExpertId() {
		return this.expertId;
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
	
	/**
	 * @param status 企业/招标机构对专家的启用/禁用状态   0:启用 1:禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return 企业/招标机构对专家的启用/禁用状态   0:启用 1:禁用
	 */
	public Integer getStatus() {
		return this.status;
	}
	
	/**
	 * @param auditStatus 审批状态 0:待审批 1:审批中2:审批不通过3:审批通过
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	/**
	 * @return 审批状态 0:待审批 1:审批中2:审批不通过3:审批通过
	 */
	public Integer getAuditStatus() {
		return this.auditStatus;
	}
	
	/**
	 * @param origin 来源 1:内部 2:外部
	 */
	public void setOrigin(Integer origin) {
		this.origin = origin;
	}
	
	/**
	 * @return 来源 1:内部 2:外部
	 */
	public Integer getOrigin() {
		return this.origin;
	}
	
	/**
	 * @param reason 
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * @return 
	 */
	public String getReason() {
		return this.reason;
	}
	
	/**
	 * @param createUserId 
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	/**
	 * @return 
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	
	/**
	 * @param updateUserId 
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	/**
	 * @return 
	 */
	public String getUpdateUserId() {
		return this.updateUserId;
	}
	
	/**
	 * @param createTime 
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * @return 
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	/**
	 * @param createTimeBegin 开始
	 */
    public void setCreateTimeBegin(java.util.Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	
    /**
	 * @return 开始
	 */
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	/**
	 * @param createTimeEnd 结束
	 */
	public void setCreateTimeEnd(java.util.Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
	/**
	 * @return 结束
	 */
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}	
	
	/**
	 * @param updateTime 
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return 
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	/**
	 * @param updateTimeBegin 开始
	 */
    public void setUpdateTimeBegin(java.util.Date updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
	}
	
    /**
	 * @return 开始
	 */
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	/**
	 * @param updateTimeEnd 结束
	 */
	public void setUpdateTimeEnd(java.util.Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}
	
	/**
	 * @return 结束
	 */
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}	
	
	/**
	 * @param orgCode 
	 */
	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}
	
	/**
	 * @return 
	 */
	public Long getOrgCode() {
		return this.orgCode;
	}
}
