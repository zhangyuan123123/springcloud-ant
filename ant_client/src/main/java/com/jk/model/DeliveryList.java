/**
 * 此处不是注释
 * <p>
 * 业务描述
 *
 * @author 刘军谊
 * @create new Date();
 * @since 1.0.0
 */

package com.jk.model;

import lombok.Data;

@Data
public class DeliveryList {

    private Integer id;
    private String origin;
    private String provenance;
    private String contact;
    private String phone;
    private String nameCommodity;
    private Integer cargoInformation;
    private Integer gongsiid;
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameCommodity() {
        return nameCommodity;
    }

    public void setNameCommodity(String nameCommodity) {
        this.nameCommodity = nameCommodity;
    }

    public Integer getCargoInformation() {
        return cargoInformation;
    }

    public void setCargoInformation(Integer cargoInformation) {
        this.cargoInformation = cargoInformation;
    }

    public Integer getGongsiid() {
        return gongsiid;
    }

    public void setGongsiid(Integer gongsiid) {
        this.gongsiid = gongsiid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
