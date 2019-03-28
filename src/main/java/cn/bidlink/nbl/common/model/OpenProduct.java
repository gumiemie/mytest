package cn.bidlink.nbl.common.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description nbl_user库中开通产品表open_product
 * @date 2018/11/13 10:18$
 */
@Table("open_product")
public class OpenProduct {

    /**
     * @描述:主键uuid
     * @字段:ID CHAR(32)
     */
    @Name
    private String id;

    /**
     * @描述:中心库 公司id
     * @字段:COMPANY_ID BIGINT(19)
     */
    @Column("COMPANY_ID")
    private Long companyId;
    /**
     * 登录名
     */
    @Column("USER_LOGIN_NAME")
    private String userLoginName;
    /**
     * @描述:产品id
     * @字段:PRODUCT_ID CHAR(32)
     */
    @Column("PRODUCT_ID")
    private String productId;

    /**
     * @描述:产品开通开始时间
     * @字段:START_TIME DATETIME(19)
     */
    @Column("START_TIME")
    private Date startTime;

    /**
     * @描述:产品开通截止时间
     * @字段:END_TIME DATETIME(19)
     */
    @Column("END_TIME")
    private Date endTime;

    /**
     * @描述:状态：0：待开通 1：已开通 2：停用
     * @字段:STATUS INT(10)
     */
    @Column
    private Integer status;

    /**
     * @author dengchunli
     * @deprecated:产品平台:1-国际招标；2-国内招标
     */
    @Column
    private Integer platform;

    @Column("COMPANY_NAME")
    private String companyName;
    /**
     * 用户类型
     */
    @Column("USER_TYPE")
    private java.lang.Integer userType;
    /**
     * @author dengchunli
     * @deprecated:记录标志位：1-已删除；0-未删除
     */
    @Column("IS_DELETE")
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
