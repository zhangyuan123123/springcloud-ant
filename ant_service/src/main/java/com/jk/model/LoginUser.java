package com.jk.model;

import lombok.Data;

@Data
public class LoginUser {

    private Integer id;

    private String username;

    private String account;

    private String password;

    private Integer position;

    private Integer status;

    private String phone;

    private String email;

    private String phoneCode;

    private String emailCode;

    private String getIsChecked;

    private String type;
}
