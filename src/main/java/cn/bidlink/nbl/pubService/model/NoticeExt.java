/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package cn.bidlink.nbl.pubService.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * <code>NoticeExt</code>数据库映射.
 *
 * @table 	: notice_ext   
 * @version : Ver 1.0
 * @author	: <a href="guyang@ebnew.com">guyang</a>
 * @date	:  2018-11-12  
 */
@Table("Notice_Ext")
public class NoticeExt implements Serializable{
	
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
     * @描述:公告ID     
     * @字段:NOTICE_ID VARCHAR(32)  
     */
	@Column("NOTICE_ID")
	private String noticeId;

	/**
     * @描述:监管名称     
     * @字段:SUPERVISE_DEPT_NAME VARCHAR(200)  
     */
	@Column("SUPERVISE_DEPT_NAME")
	private String superviseDeptName;

	/**
     * @描述:监管人代码     
     * @字段:SUPERVISE_DEPT_CODE VARCHAR(18)  
     */
	@Column("SUPERVISE_DEPT_CODE")
	private String superviseDeptCode;

	/**
     * @描述:招标内容     
     * @字段:TENDER_CONTENT LONGTEXT(2147483647)  
     */
	@Column("TENDER_CONTENT")
	private String tenderContent;

	/**
     * @描述:项目规模     
     * @字段:PROJECT_SCALE VARCHAR(1000)  
     */
	@Column("PROJECT_SCALE")
	private String projectScale;

	/**
     * @描述:投标人资格条件     
     * @字段:BIDDING_ELIGIBILITY LONGTEXT(2147483647)  
     */
	@Column("BIDDING_ELIGIBILITY")
	private String biddingEligibility;

	/**
     * @描述:投标文件递交方法     
     * @字段:BID_DOC_REFER_METHOD VARCHAR(500)  
     */
	@Column("BID_DOC_REFER_METHOD")
	private String bidDocReferMethod;

	/**
     * @描述:是否支持联合体投标     
     * @字段:SYNDICATED_FLAG SMALLINT(5)  
     */
	@Column("SYNDICATED_FLAG")
	private Integer syndicatedFlag;

	/**
     * @描述:是否在线投标/开标     
     * @字段:IS_ONLINE_OPEN SMALLINT(5)  
     */
	@Column("IS_ONLINE_OPEN")
	private Integer isOnlineOpen;

	/**
     * @描述:评审办法     
     * @字段:QUAL_TYPE VARCHAR(10)  
     */
	@Column("QUAL_TYPE")
	private String qualType;


	/**
     * @描述:是否有中标候选人/中标人,根据公告类型区分
     * @字段:IS_HAS_WINNER SAMLLINT(1)
     */
	@Column("IS_HAS_WINNER")
	private Integer isHasWinner;

	/**
     * @描述:     
     * @字段:TENANT_ID VARCHAR(32)  
     */
	@Column("TENANT_ID")
	private String tenantId;

	/**
	 *
	 */
	public NoticeExt(){
	}

	/**
	 * @param id 
	 */
	public NoticeExt(String id){
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
	 * @param noticeId 公告ID
	 */
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	
	/**
	 * @return 公告ID
	 */
	public String getNoticeId() {
		return this.noticeId;
	}
	
	/**
	 * @param superviseDeptName 监管名称
	 */
	public void setSuperviseDeptName(String superviseDeptName) {
		this.superviseDeptName = superviseDeptName;
	}
	
	/**
	 * @return 监管名称
	 */
	public String getSuperviseDeptName() {
		return this.superviseDeptName;
	}
	
	/**
	 * @param superviseDeptCode 监管人代码
	 */
	public void setSuperviseDeptCode(String superviseDeptCode) {
		this.superviseDeptCode = superviseDeptCode;
	}
	
	/**
	 * @return 监管人代码
	 */
	public String getSuperviseDeptCode() {
		return this.superviseDeptCode;
	}
	
	/**
	 * @param tenderContent 招标内容
	 */
	public void setTenderContent(String tenderContent) {
		this.tenderContent = tenderContent;
	}
	
	/**
	 * @return 招标内容
	 */
	public String getTenderContent() {
		return this.tenderContent;
	}
	
	/**
	 * @param projectScale 项目规模
	 */
	public void setProjectScale(String projectScale) {
		this.projectScale = projectScale;
	}
	
	/**
	 * @return 项目规模
	 */
	public String getProjectScale() {
		return this.projectScale;
	}
	
	/**
	 * @param biddingEligibility 投标人资格条件
	 */
	public void setBiddingEligibility(String biddingEligibility) {
		this.biddingEligibility = biddingEligibility;
	}
	
	/**
	 * @return 投标人资格条件
	 */
	public String getBiddingEligibility() {
		return this.biddingEligibility;
	}
	
	/**
	 * @param bidDocReferMethod 投标文件递交方法
	 */
	public void setBidDocReferMethod(String bidDocReferMethod) {
		this.bidDocReferMethod = bidDocReferMethod;
	}
	
	/**
	 * @return 投标文件递交方法
	 */
	public String getBidDocReferMethod() {
		return this.bidDocReferMethod;
	}
	
	/**
	 * @param syndicatedFlag 是否支持联合体投标
	 */
	public void setSyndicatedFlag(Integer syndicatedFlag) {
		this.syndicatedFlag = syndicatedFlag;
	}
	
	/**
	 * @return 是否支持联合体投标
	 */
	public Integer getSyndicatedFlag() {
		return this.syndicatedFlag;
	}
	
	/**
	 * @param isOnlineOpen 是否在线投标/开标
	 */
	public void setIsOnlineOpen(Integer isOnlineOpen) {
		this.isOnlineOpen = isOnlineOpen;
	}
	
	/**
	 * @return 是否在线投标/开标
	 */
	public Integer getIsOnlineOpen() {
		return this.isOnlineOpen;
	}
	
	/**
	 * @param qualType 评审办法
	 */
	public void setQualType(String qualType) {
		this.qualType = qualType;
	}
	
	/**
	 * @return 评审办法
	 */
	public String getQualType() {
		return this.qualType;
	}

	public Integer getIsHasWinner() {
		return isHasWinner;
	}

	public void setIsHasWinner(Integer isHasWinner) {
		this.isHasWinner = isHasWinner;
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
