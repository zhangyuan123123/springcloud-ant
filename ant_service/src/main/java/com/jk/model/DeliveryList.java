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

}
