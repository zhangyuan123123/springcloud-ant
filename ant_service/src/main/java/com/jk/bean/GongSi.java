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
public class GongSi {

    private Integer id;
    private String name;
    private String phone;
    private String dizhi;
    private String jianjie;
    private Integer type;
    private String img;
    private Integer lightprice;
    private Integer heavyprice;
    private Integer qibujia;
    private String shixiao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getLightprice() {
        return lightprice;
    }

    public void setLightprice(Integer lightprice) {
        this.lightprice = lightprice;
    }

    public Integer getHeavyprice() {
        return heavyprice;
    }

    public void setHeavyprice(Integer heavyprice) {
        this.heavyprice = heavyprice;
    }

    public Integer getQibujia() {
        return qibujia;
    }

    public void setQibujia(Integer qibujia) {
        this.qibujia = qibujia;
    }

    public String getShixiao() {
        return shixiao;
    }

    public void setShixiao(String shixiao) {
        this.shixiao = shixiao;
    }
}
