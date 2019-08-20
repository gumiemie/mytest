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

/**
 * <code>Room</code>数据库映射.
 *
 * @table 	: room   
 * @version : Ver 1.0
 * @author	: <a href="huilanbo@ebnew.com">bohuilan</a>
 * @date	:  2015-11-11 14:42:23  
 */
@Table("cn/bidlink/nbl/room")
public class Room{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @描述:主键     
     * @字段:ID CHAR(32)  
     */
	@Name
	private String id;

	/**
     * @描述:包组ID     
     * @字段:PACKAGE_GROUP_ID CHAR(32)  
     */
	@Column("PACKAGE_GROUP_ID")
	private String packageGroupId;

	@Column("PACKAGE_ID")
	private String packageId;


	/**
     * @描述:大厅类型：1开标大厅；2评标大厅     
     * @字段:ROOM_TYPE BIGINT(19)  
     */
	@Column("ROOM_TYPE")
	private Integer roomType;

	/**
     * @描述:大厅状态：0：创建；1开启；2关闭;-1:异常关闭（终止大厅）
     * @字段:ROOM_STATUS BIGINT(19)  
     */
	@Column("ROOM_STATUS")
	private Integer roomStatus;

	/**
     * @描述:开标、评标方式：009001=线上开标;009002=线下开标;015001=线上评标,015002=线下评标
     * @字段:OPEN_BID_EVAL_METHOD varchar(12)  
     */
	@Column("OPEN_BID_EVAL_METHOD")
	private String openBidEvalMethod;
	/**
	 * @描述:终止原因
	 * @字段:ABANDON_REASON varchar(2000)
	 */
	@Column("ABANDON_REASON")
	private String abandonReason;
	/**
	 * @描述:终止时间
	 * @字段:ABANDON_TIME
	 */
	@Column("ABANDON_TIME")
	private Date abandonTime;
	/**
	 * @描述:终止用户id
	 * @字段:ABANDON_USER_ID
	 */
	@Column("ABANDON_USER_ID")
	private String abandonUserId;
	@Column("ABANDON_USER_NAME")
	private String abandonUserName;

	
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

	/**
	 *
	 */
	public Room(){
	}

	/**
	 * @param id 主键
	 */
	public Room(String id){
		this.id = id;
	}
	
	/**
	 * @param id 主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return 主键
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * @param packageGroupId 包组ID
	 */
	public void setPackageGroupId(String packageGroupId) {
		this.packageGroupId = packageGroupId;
	}
	
	/**
	 * @return 包组ID
	 */
	public String getPackageGroupId() {
		return this.packageGroupId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	/**
	 * @param roomType 大厅类型：1开标大厅；2评标大厅
	 */
	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}
	
	/**
	 * @return 大厅类型：1开标大厅；2评标大厅
	 */
	public Integer getRoomType() {
		return this.roomType;
	}
	
	/**
	 * @param roomStatus 大厅状态：0：创建；1开启；2关闭
	 */
	public void setRoomStatus(Integer roomStatus) {
		this.roomStatus = roomStatus;
	}
	
	/**
	 * @return 大厅状态：0：创建；1开启；2关闭
	 */
	public Integer getRoomStatus() {
		return this.roomStatus;
	}
	
	/**
	 * @param openBidEvalMethod 开标、评标方式：009001=线上开标;009002=线下开标;015001=线上评标,015002=线下评标
	 */
	public void setOpenBidEvalMethod(String openBidEvalMethod) {
		this.openBidEvalMethod = openBidEvalMethod;
	}
	
	/**
	 * @return 开标、评标方式：开标、评标方式：009001=线上开标;009002=线下开标;015001=线上评标,015002=线下评标
	 */
	public String getOpenBidEvalMethod() {
		return this.openBidEvalMethod;
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

	public String getAbandonReason() {
		return abandonReason;
	}

	public void setAbandonReason(String abandonReason) {
		this.abandonReason = abandonReason;
	}

	public Date getAbandonTime() {
		return abandonTime;
	}

	public void setAbandonTime(Date abandonTime) {
		this.abandonTime = abandonTime;
	}

	public String getAbandonUserId() {
		return abandonUserId;
	}

	public void setAbandonUserId(String abandonUserId) {
		this.abandonUserId = abandonUserId;
	}

	public String getAbandonUserName() {
		return abandonUserName;
	}

	public void setAbandonUserName(String abandonUserName) {
		this.abandonUserName = abandonUserName;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
}
