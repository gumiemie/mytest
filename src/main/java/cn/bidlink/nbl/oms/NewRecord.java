package cn.bidlink.nbl.oms;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/4/27 16:07$
 */
@Table("project_regulatory_record")
public class NewRecord {
    /**
     * @描述:
     * @字段:id BIGINT(19)
     */
    @Column
    private Long id;

    /**
     * @描述:修改申请id
     * @字段:apply_id BIGINT(19)
     */
    @Column("apply_id")
    private Long applyId;

    /**
     * @描述:用户类别
     * @字段:opt_user_type VARCHAR(100)
     */
    @Column("opt_user_type")
    private String optUserType;

    /**
     * @描述:经办人id
     * @字段:opt_user_id VARCHAR(32)
     */
    @Column("opt_user_id")
    private String optUserId;

    /**
     * @描述:经办人名称
     * @字段:opt_user_name VARCHAR(50)
     */
    @Column("opt_user_name")
    private String optUserName;

    /**
     * @描述:操作选择
     * @字段:opt_choice VARCHAR(50)
     */
    @Column("opt_choice")
    private String optChoice;

    /**
     * @描述:操作时间
     * @字段:opt_time DATETIME(19)
     */
    @Column("opt_time")
    private java.util.Date optTime;

    /**
     * @描述:说明
     * @字段:description VARCHAR(2000)
     */
    @Column
    private String description;

    /**
     * @描述:记录生成时间
     * @字段:create_time DATETIME(19)
     */
    @Column("create_time")
    private java.util.Date createTime;

    /**
     * @描述:
     * @字段:attachment VARCHAR(32)
     */
    @Column
    private String attachment;

    /**
     * @描述:
     * @字段:attachmentName VARCHAR(100)
     */
    @Column
    private String attachmentName;

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

    public String getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(String optUserId) {
        this.optUserId = optUserId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
