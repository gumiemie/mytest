/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有 
 */
package pubService;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * <code>SuperviseSendLog</code>数据库映射.
 *
 * @version : Ver 1.0
 * @table : supervise_send_log
 * @author    : <a href="quanyouwan@ebnew.com">wanquanyou</a>
 * @date    : 2018-01-15 上午10:43:02
 */
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
     * @字段:package_id VARCHAR(32)
     */
    @Column("package_id")
    private String packageId;

    /**
     * @描述:
     * @字段:project_id VARCHAR(32)
     */
    @Column("project_id")
    private String projectId;

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
     * @param packageId
     */
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    /**
     * @return
     */
    public String getPackageId() {
        return this.packageId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return
     */
    public String getProjectId() {
        return this.projectId;
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
}
