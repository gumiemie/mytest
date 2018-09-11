package oms;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/4/27 18:04$
 */
@Table("BMS_PROJECT_EDIT_APPLICATION")
public class OldApply {
    /**
     * 主键 ID
     * @hibernate.id generator-class="native"
     */
    @Column
    private Long id;
    /**
     * 项目 ID
     * @hibernate.property column="PROJECT_ID"
     */
    @Column("PROJECT_ID")
    private String projectId;
    /**
     * 项目性质（依法必招/非依法必招）
     * @hibernate.property column="PROJECT_PROPERTIE"
     */
    @Column("PROJECT_PROPERTIE")
    private String projectNatureS;
    /**
     * 项目编号
     * @hibernate.property column="PROJECT_NUM"
     */
    @Column("PROJECT_NUM")
    private String projectCode;
    /**
     * 项目名称
     * @hibernate.property column="PROJECT_NAME"
     */
    @Column("PROJECT_NAME")
    private String projectName;

    /**
     * 建项人登录名
     * @hibernate.property column="PROJECT_CREATOR_LOGIN_NAME"
     */
    @Column("PROJECT_CREATOR_LOGIN_NAME")
    private String projectCreateName;

    /**
     * 招标方名称
     * @hibernate.property column="BIDDING_NAME"
     */
    @Column("BIDDING_NAME")
    private String tenderName;
    /**
     * 申请时间
     * @hibernate.property column="APPLICATION_TIME"
     */
    @Column("APPLICATION_TIME")
    private Date applyTime;
    /**
     * 当前处理状态（0:待处理，1:待批复，2:已批复，3:待修改，4:已修改，5:已完成，6:已撤销）
     * @hibernate.property column="CURRENT_STATUS"
     */
    @Column("CURRENT_STATUS")
    private Integer currentStatus;
    /**
     * 修改类别（1:修改项目信息项，2:替换文件，3:沿用专家，4:替换专家，5:指定专家，6:回退项目节点，7:放开3家限制，8:归档后翻案，9:其他）
     * @hibernate.property column="EDIT_CATEGORY"
     */
    @Column("EDIT_CATEGORY")
    private Integer modifyType;

    /**
     * 客服经理 id
     * @hibernate.property column="SERVER_ID"
     */
    @Column("SERVER_ID")
    private Long supportId;

    /**
     * 客服经理姓名
     * @hibernate.property column="SERVER_NAME"
     */
    @Column("SERVER_NAME")
    private String supportName;

    /**
     * 增加监督部门
     * @hibernate.property column="SUPERVISION_DEPARTMENT"
     */
    @Column("SUPERVISION_DEPARTMENT")
    private String regulatoryDepartment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectNatureS() {
        return projectNatureS;
    }

    public void setProjectNatureS(String projectNatureS) {
        this.projectNatureS = projectNatureS;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCreateName() {
        return projectCreateName;
    }

    public void setProjectCreateName(String projectCreateName) {
        this.projectCreateName = projectCreateName;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Integer getModifyType() {
        return modifyType;
    }

    public void setModifyType(Integer modifyType) {
        this.modifyType = modifyType;
    }

    public Long getSupportId() {
        return supportId;
    }

    public void setSupportId(Long supportId) {
        this.supportId = supportId;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public String getRegulatoryDepartment() {
        return regulatoryDepartment;
    }

    public void setRegulatoryDepartment(String regulatoryDepartment) {
        this.regulatoryDepartment = regulatoryDepartment;
    }
}
