package com.jk.service;

import com.jk.model.LoginUser;

import java.util.HashMap;

import com.jk.model.DeliveryList;

import java.util.HashMap;

public interface AntService {


    void setBlacklist(String phoneName);

    void setBlacklist2(String emailName);

    LoginUser getUserByNameAndPwd(LoginUser loginUser);

    String findStatus(String phoneName);

    String findStatus2(String emailName);

    String addUser(LoginUser loginUser);

    LoginUser findUser(Integer id);

    void updatePassWord(Integer id, String password);

    HashMap findline(Integer limit, Integer page,String name);

    void addLineUserC(Integer id, Integer lid, Integer userId);

    HashMap findcommontable(Integer limit, Integer page);

    HashMap findhei(Integer limit, Integer page);

    void delheimingdan(Integer id);
    HashMap<String,Object> getprovinces();

    HashMap<String,Object> getcity(String cid);

    HashMap<String,Object> getcounty(String cid);

    void addsubmit(DeliveryList deliveryList);

    HashMap<String,Object> refer(Integer page, Integer limit);

    HashMap<String,Object> referline(Integer page, Integer limit);

    HashMap<String,Object> refertcom(Integer gid);

    void addinformation(Integer xid, DeliveryList deliveryList);
}
