package shjd.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/7/27 16:17$
 */
@Table("bm_tenderee")
public class BmTenderee {
    //cname,manager_cname,company_type,manager_mobile,belong_industry,valid_status
    @Column
    private String cname;
    @Column
    private String manager_cname;
    @Column
    private String company_type;
    @Column
    private String manager_mobile;
    @Column
    private String belong_industry;
    @Column
    private Integer valid_status;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getManager_cname() {
        return manager_cname;
    }

    public void setManager_cname(String manager_cname) {
        this.manager_cname = manager_cname;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getManager_mobile() {
        return manager_mobile;
    }

    public void setManager_mobile(String manager_mobile) {
        this.manager_mobile = manager_mobile;
    }

    public String getBelong_industry() {
        return belong_industry;
    }

    public void setBelong_industry(String belong_industry) {
        this.belong_industry = belong_industry;
    }

    public Integer getValid_status() {
        return valid_status;
    }

    public void setValid_status(Integer valid_status) {
        this.valid_status = valid_status;
    }
}
