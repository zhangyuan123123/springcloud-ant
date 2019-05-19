/**
 * 此处不是注释
 * <p>
 * 业务描述
 *
 * @author 刘军谊
 * @create new Date();
 * @since 1.0.0
 */

package com.jk.bean;


import lombok.Data;

@Data
public class LineModel {

    private Integer id;
    private String wulliuxianlu;
    private Integer gongsiid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWulliuxianlu() {
        return wulliuxianlu;
    }

    public void setWulliuxianlu(String wulliuxianlu) {
        this.wulliuxianlu = wulliuxianlu;
    }

    public Integer getGongsiid() {
        return gongsiid;
    }

    public void setGongsiid(Integer gongsiid) {
        this.gongsiid = gongsiid;
    }
}
