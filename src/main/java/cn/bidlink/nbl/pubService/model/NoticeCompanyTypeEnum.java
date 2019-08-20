package cn.bidlink.nbl.pubService.model;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 公司类型
 * @date 2018/11/23 16:26$
 */
public enum NoticeCompanyTypeEnum {
    TENDERER("招标人",1),
    SECTIONS_BIDDER("中标人",2),
    WIN_CANDIDATE("中标候选人",3),
    ;
    private String name;
    private int value;

    NoticeCompanyTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
