package cn.bidlink.nbl.busiopport;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 商机行业转换映射类
 * @date 2017/3/29 9:23$
 */
@Table("categoryb2c")
public class CategoryMapping {

    //B行业code
    @Column("code_b")
    private String codeb;
    //B行业name
    @Column("name_b")
    private String nameb;
    //C行业code
    @Column("code_c")
    private String codec;
    //C行业name
    @Column("name_c")
    private String namec;

    public String getCodeb() {
        return codeb;
    }

    public void setCodeb(String codeb) {
        this.codeb = codeb;
    }

    public String getNameb() {
        return nameb;
    }

    public void setNameb(String nameb) {
        this.nameb = nameb;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getNamec() {
        return namec;
    }

    public void setNamec(String namec) {
        this.namec = namec;
    }
}
