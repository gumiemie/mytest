package cn.bidlink.nbl.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2016/11/10 15:03$
 */
@Table("base_area")
public class Area {

    @Column("ID")
    private String id;
    @Column("NAME")
    private String name;
    @Column("LEVEL")
    private int level;
    @Column("CODE")
    private String code;
    @Column("PCODE")
    private String pcode;
    @Column("CODE_E")
    private String codeE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCodeE() {
        return codeE;
    }

    public void setCodeE(String codeE) {
        this.codeE = codeE;
    }
}
