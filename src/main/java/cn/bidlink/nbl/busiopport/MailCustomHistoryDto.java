package cn.bidlink.nbl.busiopport;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 商机订阅历史纪录>映射类
 * @date 2017/4/10 14:07$
 */
@Table("mail_custom_history")
public class MailCustomHistoryDto {

    @Id
    private  Long id;

    @Column("USER_LOGIN_NAME")
    private String userLoginName;

    @Column("MAIL_NAMES")
    private String mails;

    @Column("INFOCLASS_IDS")
    private String infoClassCodes;

    @Column("BIDMODELS")
    private String bidModels;

    @Column("DOCSOURCES")
    private String docSources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) {
        this.mails = mails;
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
}
