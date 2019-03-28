package cn.bidlink.nbl.expert.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/5/12 15:37$
 */
@Table("exp_expert_industry_map")
public class Exp_Ind_Mapping {

    @Name
    private String id;
    @Column("EXPERT_ID")
    private String expertId;
    @Column("INDUSTRY_ID")
    private String industryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }
}
