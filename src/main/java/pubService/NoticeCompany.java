/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package pubService;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;

import java.io.Serializable;

/**
 * <code>NoticeCompany</code>数据库映射.
 *
 * @table 	: notice_company   
 * @version : Ver 1.0
 * @author	: <a href="guyang@ebnew.com">guyang</a>
 * @date	:  2018-10-25  
 */
public class NoticeCompany implements Serializable{
	
	private String sortColumns;

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @描述:     
     * @字段:ID VARCHAR(32)  
     */
	@Name
	private String id;

	/**
     * @描述:公告id     
     * @字段:NOTICE_ID VARCHAR(32)  
     */
	@Column("NOTICE_ID")
	private String noticeId;

	/**
	 * @描述:公司id
	 * @字段:COMPANY_ID VARCHAR(32)
	 */
	@Column("COMPANY_ID")
	private Long companyId;

	/**
     * @描述:公司名称     
     * @字段:NAME VARCHAR(200)  
     */
	@Column
	private String name;

	/**
     * @描述:公司代码类型     
     * @字段:CODE_TYPE VARCHAR(2)  
     */
	@Column("CODE_TYPE")
	private String codeType;

	/**
     * @描述:公司代码     
     * @字段:CODE VARCHAR(18)  
     */
	@Column
	private String code;

	/**
     * @描述:联系人     
     * @字段:CONTACTOR VARCHAR(100)  
     */
	@Column
	private String contactor;

	/**
     * @描述:联系电话     
     * @字段:PHONE_NUMBER VARCHAR(20)  
     */
	@Column("PHONE_NUMBER")
	private String phoneNumber;

	/**
     * @描述:电子邮箱     
     * @字段:EMAIL VARCHAR(100)  
     */
	@Column
	private String email;

	/**
     * @描述: 联系地址     
     * @字段:ADDRESS VARCHAR(200)  
     */
	@Column
	private String address;

	/**
     * @描述: 中标候选人排名     
     * @字段:ORDER_NO SMALLINT(5)
     */
	@Column("ORDER_NO")
	private Integer orderNo;

	/**
     * @描述:中标候选人响应招标文件的资格能力条件     
     * @字段:CANDIDATE_QUALIFICATION VARCHAR(200)  
     */
	@Column("CANDIDATE_QUALIFICATION")
	private String candidateQualification;

	/**
     * @描述:中标候选人 工期/交货期/服务期（天）     
     * @字段:TIME_LIMIT SMALLINT(5)  
     */
	@Column("TIME_LIMIT")
	private Integer timeLimit;

	/**
     * @描述: 类型 1 = 招标人 2=投标人 3=中标候选人     
     * @字段:TYPE SMALLINT(5)  
     */
	@Column
	private Integer type;

	/**
     * @描述:     
     * @字段:CREATE_TIME DATETIME(19)  
     */
	@Column("CREATE_TIME")
	private java.util.Date createTime;

	/**
     * @描述:     
     * @字段:CREATE_USER_ID VARCHAR(32)  
     */
	@Column("CREATE_USER_ID")
	private String createUserId;

	/**
     * @描述:     
     * @字段:TENANT_ID VARCHAR(32)  
     */
	@Column("TENANT_ID")
	private String tenantId;

	/**
	 *
	 */
	public NoticeCompany(){
	}

	/**
	 * @param id 
	 */
	public NoticeCompany(String id){
		this.id = id;
	}
	
	/**
	 * @param id 
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return 
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * @param noticeId 公告id
	 */
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	
	/**
	 * @return 公告id
	 */
	public String getNoticeId() {
		return this.noticeId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @param name 公司名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return 公司名称
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @param codeType 公司代码类型
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	/**
	 * @return 公司代码类型
	 */
	public String getCodeType() {
		return this.codeType;
	}
	
	/**
	 * @param code 公司代码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return 公司代码
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * @param contactor 联系人
	 */
	public void setContactor(String contactor) {
		this.contactor = contactor;
	}
	
	/**
	 * @return 联系人
	 */
	public String getContactor() {
		return this.contactor;
	}
	
	/**
	 * @param phoneNumber 联系电话
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return 联系电话
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
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
	 * @param address  联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return  联系地址
	 */
	public String getAddress() {
		return this.address;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @param candidateQualification 中标候选人响应招标文件的资格能力条件
	 */
	public void setCandidateQualification(String candidateQualification) {
		this.candidateQualification = candidateQualification;
	}
	
	/**
	 * @return 中标候选人响应招标文件的资格能力条件
	 */
	public String getCandidateQualification() {
		return this.candidateQualification;
	}
	
	/**
	 * @param timeLimit 中标候选人 工期/交货期/服务期（天）
	 */
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	/**
	 * @return 中标候选人 工期/交货期/服务期（天）
	 */
	public Integer getTimeLimit() {
		return this.timeLimit;
	}
	
	/**
	 * @param type  类型 1 = 招标人 2=投标人 3=中标候选人
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @return  类型 1 = 招标人 2=投标人 3=中标候选人
	 */
	public Integer getType() {
		return this.type;
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
	 * @param tenantId 
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	/**
	 * @return 
	 */
	public String getTenantId() {
		return this.tenantId;
	}
}
