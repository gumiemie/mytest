package cn.bidlink.nbl.shjd.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description bm_message表映射类.
 * @date 2017/8/11 16:52$
 */
@Table("bm_message")
public class Message {

    @Name
    private String id;

    @Column("target_addr")
    private String targetAddr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gettargetAddr() {
        return targetAddr;
    }

    public void settargetAddr(String targetAddr) {
        this.targetAddr = targetAddr;
    }
}
