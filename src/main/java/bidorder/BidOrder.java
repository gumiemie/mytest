package bidorder;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/9/1 13:50$
 */
@Table("bid_order")
public class BidOrder {
    @Name
    private String id;
    @Column("PACKAGE_GROUP_NAME")
    private String packageGroupName;
    @Column("PACKAGE_GROUP_NUMBER")
    private String packageGroupNumber;
    @Column("PACKAGE_GROUP_ID")
    private String packageGroupId;
    @Column("NOTICE_ID")
    private String noticeId;
    @Column("PROJECT_TYPE")
    private Integer projectType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageGroupName() {
        return packageGroupName;
    }

    public void setPackageGroupName(String packageGroupName) {
        this.packageGroupName = packageGroupName;
    }

    public String getPackageGroupNumber() {
        return packageGroupNumber;
    }

    public void setPackageGroupNumber(String packageGroupNumber) {
        this.packageGroupNumber = packageGroupNumber;
    }

    public String getPackageGroupId() {
        return packageGroupId;
    }

    public void setPackageGroupId(String packageGroupId) {
        this.packageGroupId = packageGroupId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }
}
