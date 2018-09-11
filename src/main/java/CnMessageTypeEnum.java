import java.io.Serializable;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 类型枚举
 * @date 2017/12/14 17:40$
 */
public enum CnMessageTypeEnum implements Serializable {

    PROJECT("项目", 1),
    TENDER_PROJECT("招标项目", 2),
    BID_SECTION("标段（包）", 3),
    TENDER_BULLETIN("招标公告", 4),
    AMEND_BULLETIN("变更公告", 5),
    WIN_CANDIDATE("中标候选人公示", 6),
    WIN_BIDDER("中标人", 7),
    WIN_BID_BULLETIN("中标结果公告", 8),
    TENDERER("招标人", 9),
    TENDER_AGENCY("招标代理机构", 10),
    BIDDER("投标人", 11);


    private String name;
    private Integer value;

    CnMessageTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
