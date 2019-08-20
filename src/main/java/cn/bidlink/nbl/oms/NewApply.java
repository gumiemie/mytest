package cn.bidlink.nbl.oms;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 新的项目修改申请备案映射类
 * @date 2018/4/27 15:46$
 */
@Table("project_modify_apply")
public class NewApply {

    /**
     * @描述:
     * @字段:id BIGINT(19)
     */
    @Column
    private Long id;

    /**
     * @描述:项目性质 1,依法招标 2,非依法招标
     * @字段:project_nature TINYINT(3)
     */
    @Column("project_nature")
    private Integer projectNature;

    /**
     * @描述:项目id
     * @字段:project_id BIGINT(19)
     */
    @Column("project_id")
    private Long projectId;

    /**
     * @描述:项目编号
     * @字段:project_code VARCHAR(100)
     */
    @Column("project_code")
    private String projectCode;

    /**
     * @描述:项目名称
     * @字段:project_name VARCHAR(200)
     */
    @Column("project_name")
    private String projectName;

    /**
     * @描述:项目创建人
     * @字段:project_create_name VARCHAR(100)
     */
    @Column("project_create_name")
    private String projectCreateName;

    /**
     * @描述:项目创建人Id
     * @字段:project_create_user_id BIGINT(20)
     */
    @Column("project_create_user_id")
    private Long projectCreateUserId;

    /**
     * @描述:修改类型：1修改项目信息项,2替换文件,3沿用专家,4替换专家,5指定专家,6回退项目节点,7放开3家限制,8归档后翻案,9其他
     * @字段:modifyType TINYINT(3)
     */
    @Column
    private Integer modifyType;

    /**
     * @描述:招标方名称
     * @字段:tender_name VARCHAR(200)
     */
    @Column("tender_name")
    private String tenderName;

    /**
     * @描述:监管部门
     * @字段:regulatory_department VARCHAR(100)
     */
    @Column("regulatory_department")
    private String regulatoryDepartment;

    /**
     * @描述:申请时间
     * @字段:apply_time DATETIME(19)
     */
    @Column("apply_time")
    private java.util.Date applyTime;

    /**
     * @描述:客服id
     * @字段:support_id BIGINT(19)
     */
    @Column("support_id")
    private String supportId;

    /**
     * @描述:客服名称
     * @字段:support_name VARCHAR(100)
     */
    @Column("support_name")
    private String supportName;

    /**
     * @描述:记录创建时间
     * @字段:create_time DATETIME(19)
     */
    @Column("create_time")
    private java.util.Date createTime;

    /**
     * @描述:状态: 1,待处理 2,待修改 3,已修改 4,待批复  5,已批复  6,已撤销  7,已完成
     * @字段:status TINYINT(3)
     */
    @Column
    private Integer status;

    /**
     * @描述:运维人员id
     * @字段:operation_id VARCHAR(32)
     */
    @Column("operation_id")
    private String operationId;

    /**
     * @描述:运维人员名称
     * @字段:operation_name VARCHAR(100)
     */
    @Column("operation_name")
    private String operationName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectNature() {
        return projectNature;
    }

    public void setProjectNature(Integer projectNature) {
        this.projectNature = projectNature;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public Long getProjectCreateUserId() {
        return projectCreateUserId;
    }

    public void setProjectCreateUserId(Long projectCreateUserId) {
        this.projectCreateUserId = projectCreateUserId;
    }

    public Integer getModifyType() {
        return modifyType;
    }

    public void setModifyType(Integer modifyType) {
        this.modifyType = modifyType;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getRegulatoryDepartment() {
        return regulatoryDepartment;
    }

    public void setRegulatoryDepartment(String regulatoryDepartment) {
        this.regulatoryDepartment = regulatoryDepartment;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getSupportId() {
        return supportId;
    }

    public void setSupportId(String supportId) {
        this.supportId = supportId;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
