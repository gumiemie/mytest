package busiopport;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用于映射商机订阅数据库
 * @date 2017/3/29 13:59$
 */
@Table("mail_custom")
public class MailcustomDto {
    @Id
    private Long id;
    @Column("INDUSTRY_CODES")
    private String industryCodes;
    @Column("INDUSTRY_NAMES")
    private String industryNames;
    @Column("INFOCLASS_IDS")
    private String infoClassCodes;
    @Column("BIDMODELS")
    private String bidModels;
    @Column("DOCSOURCES")
    private String docSources;
    @Column("MAIL_NAMES")
    private String mailNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndustryCodes() {
        return industryCodes;
    }

    public void setIndustryCodes(String industryCodes) {
        this.industryCodes = industryCodes;
    }

    public String getIndustryNames() {
        return industryNames;
    }

    public void setIndustryNames(String industryNames) {
        this.industryNames = industryNames;
    }

    public String getInfoClassCodes() {
        return infoClassCodes;
    }

    public void setInfoClassCodes(String infoClassCodes) {
        this.infoClassCodes = infoClassCodes;
    }

    public String getBidModels() {
        return bidModels;
    }

    public void setBidModels(String bidModels) {
        this.bidModels = bidModels;
    }

    public String getDocSources() {
        return docSources;
    }

    public void setDocSources(String docSources) {
        this.docSources = docSources;
    }

    public String getMailNames() {
        return mailNames;
    }

    public void setMailNames(String mailNames) {
        this.mailNames = mailNames;
    }
}
