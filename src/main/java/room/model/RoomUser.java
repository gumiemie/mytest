/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package room.model;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;
import java.util.List;

/**
 * <code>RoomUser</code>数据库映射.
 *
 * @table 	: room_user   
 * @version : Ver 1.0
 * @author	: <a href="huilanbo@ebnew.com">bohuilan</a>
 * @date	:  2015-11-11 14:42:23  
 */
@Table("room_user")
public class RoomUser {

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
     * @描述:用户ID
     * @字段:USER_ID CHAR(32)
     */
	@Column("USER_ID")
	private String userId;

	private List<String > companyIdList;

	private List<String> userTypeList;

	@Column("COMPANY_ID")
	private String companyId;

	/**
     * @描述:用户名称
     * @字段:USER_NAME VARCHAR(500)
     */
	@Column("USER_NAME")
	private String userName;

	/**
     * @描述:用户身份
     * @字段:USER_TYPE CHAR(20)
     */
	@Column("USER_TYPE")
	private String userType;

	/**
     * @描述:用户电话
     * @字段:USER_PHONE VARCHAR(50)
     */
	@Column("USER_PHONE")
	private String userPhone;

	/**
     * @描述:用户电子邮件
     * @字段:USER_EMAIL VARCHAR(100)
     */
	@Column("USER_EMAIL")
	private String userEmail;
	/**
     * @描述:是否是负责人：1是；0不是
     * @字段:IS_LEADER BIGINT(19)
     */
	@Column("IS_LEADER")
	private Long isLeader;

	/**
	 * @描述:是否是系统内置用户 1=是 0=否
	 * @字段:IS_SYSTEM_USER tinyint(1)
	 */
	@Column("IS_SYSTEM_USER")
	private Integer isSystemUser;

	/**
     * @描述:用户签到时间
     * @字段:USER_SIGN_TIME DATETIME(19)
     */
	@Column("USER_SIGN_TIME")
	private Date userSignTime;

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

	@Column("WORK_UNIT")
	private String workUnit;

	/**
	 * 评委签到时, 调用时间戳服务签名的字符串
	 */
	@Column("SIGN_KEY")
	private String signKey;

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	/**
	 *
	 */
	public RoomUser(){
	}

	/**
	 * @param id 主键ID
	 */
	public RoomUser(String id){
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
	 * @param userId 用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return 用户ID
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * @param userName 用户名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return 用户名称
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @param userType 用户身份
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return 用户身份
	 */
	public String getUserType() {
		return this.userType;
	}

	/**
	 * @param userPhone 用户电话
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return 用户电话
	 */
	public String getUserPhone() {
		return this.userPhone;
	}

	/**
	 * @param userEmail 用户电子邮件
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return 用户电子邮件
	 */
	public String getUserEmail() {
		return this.userEmail;
	}

	/**
	 * @param isLeader 是否是负责人：1是；0不是
	 */
	public void setIsLeader(Long isLeader) {
		this.isLeader = isLeader;
	}

	/**
	 * @return 是否是负责人：1是；0不是
	 */
	public Long getIsLeader() {
		return this.isLeader;
	}

	/**
	 * @param userSignTime 用户签到时间
	 */
	public void setUserSignTime(Date userSignTime) {
		this.userSignTime = userSignTime;
	}

	/**
	 * @return 用户签到时间
	 */
	public Date getUserSignTime() {
		return this.userSignTime;
	}

	/**
	 * @param roomId 开标大厅或者评标 大厅 ID
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return 开标大厅或者评标 大厅 ID
	 */
	public String getRoomId() {
		return this.roomId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public List<String> getCompanyIdList() {
		return companyIdList;
	}

	public void setCompanyIdList(List<String> companyIdList) {
		this.companyIdList = companyIdList;
	}

	public List<String> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<String> userTypeList) {
		this.userTypeList = userTypeList;
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

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public Integer getIsSystemUser() {
		return isSystemUser;
	}

	public void setIsSystemUser(Integer isSystemUser) {
		this.isSystemUser = isSystemUser;
	}
}
