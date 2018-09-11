package oms;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/4/27 18:34$
 */
@Table("BMS_TRANSACTION_FLOW")
public class OldRecord {

    /**
     * 主键 ID
     *
     * @hibernate.id generator-class="native"
     */
    @Column
    private Long id;
    /**
     * 项目修改申请 ID
     *
     * @hibernate.property column="PROJECT_EDIT_APPLICATION_ID"
     */
    @Column("PROJECT_EDIT_APPLICATION_ID")
    private Long applyId;
    /**
     * 用户类别
     *
     * @hibernate.property column="SERVER_CLASS"
     */
    @Column("SERVER_CLASS")
    private String optUserType;
    /**
     * 经办人
     *
     * @hibernate.property column="SERVER_NAME"
     */
    @Column("SERVER_NAME")
    private String optUserName;
    /**
     * 操作选择
     *
     * @hibernate.property column="OPERATE_CHOICE"
     */
    @Column("OPERATE_CHOICE")
    private String optChoice;
    /**
     * 经办时间
     *
     * @hibernate.property column="OPERATE_TIME"
     */
    @Column("OPERATE_TIME")
    private Date optTime;
    /**
     * 处理说明
     *
     * @hibernate.property column="PROCESSING_INSTRUCTION"
     */
    @Column("PROCESSING_INSTRUCTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getOptUserType() {
        return optUserType;
    }

    public void setOptUserType(String optUserType) {
        this.optUserType = optUserType;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }

    public String getOptChoice() {
        return optChoice;
    }

    public void setOptChoice(String optChoice) {
        this.optChoice = optChoice;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
