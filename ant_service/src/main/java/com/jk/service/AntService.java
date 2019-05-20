package com.jk.service;

import com.jk.model.City;
import com.jk.model.LoginUser;

import java.util.HashMap;

import com.jk.model.DeliveryList;

import com.jk.model.Top;

import java.util.List;

import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.ZhaoBiaoModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

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
    List<Top> getTop();

    List<City> findCity();



    Integer findCompany(String name);

    Long findGsId(String provenance,String origin);
    List<CityModel> getCity();

    List<CityModel> getShi(Integer id);

    HashMap<String,Object> getAll(Integer page,Integer limit,Integer id);

    HashMap<String,Object> getXiang(Integer id);

    HashMap<String,Object> getZhaoBiao(Integer page, Integer limit ,String biaoti);

    void addzhaobiao(ZhaoBiaoModel zhaoBiaoModel);
}
