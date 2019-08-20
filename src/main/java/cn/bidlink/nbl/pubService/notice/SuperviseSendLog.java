/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package cn.bidlink.nbl.pubService.notice;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("supervise_send_log")
public class SuperviseSendLog {

    /**
     * @描述:
     * @字段:id varchar(32)
     */
    @Name
    private String id;

    /**
     * @描述:
     * @字段:notice_id VARCHAR(32)
     */
    @Column("notice_id")
    private String noticeId;

    /**
     * @描述:
     * @字段:data LONGTEXT(2147483647)
     */
    @Column
    private String data;

    /**
     * @描述:
     * @字段:type tinyint(4)
     */
    @Column
    private Integer type;

    /**
     * @描述:
     * @字段:is_send BIT(0)
     */
    @Column("is_send")
    private Boolean isSend;

    /**
     * 创建时间
     */
    @Column("create_Time")
    private Date createTime;

    /**
     *
     */
    public SuperviseSendLog() {
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
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return
     */
    public String getData() {
        return this.data;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * @param isSend
     */
    public void setIsSend(Boolean isSend) {
        this.isSend = isSend;
    }

    /**
     * @return
     */
    public Boolean getIsSend() {
        return this.isSend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }
}
