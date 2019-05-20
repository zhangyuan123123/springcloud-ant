package com.jk.service;

import com.jk.model.City;
import com.jk.model.LoginUser;

import com.jk.model.DeliveryList;

import com.jk.model.Top;
import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.ZhaoBiaoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@FeignClient("service")
public interface AntClientService {
    @RequestMapping("ant/findStatus")
    String findStatus(String phoneName);
    @RequestMapping("ant/findStatus2")
    String findStatus2(@RequestParam("emailName")String emailName);
    @RequestMapping("ant/setBlacklist")
    void setBlacklist(@RequestParam("phoneName") String phoneName);
    @RequestMapping("ant/setBlacklist2")
    void setBlacklist2(@RequestParam("emailName") String emailName);
    @RequestMapping("ant/getUserByNameAndPwd")
    LoginUser getUserByNameAndPwd(@RequestBody LoginUser loginUser);
    @RequestMapping("ant/addUser")
    String addUser(@RequestBody LoginUser loginUser);
    @RequestMapping("ant/findUser")
    LoginUser findUser(@RequestParam("id") Integer id);
    @RequestMapping("ant/updatePassWord")
    void updatePassWord(@RequestParam("id") Integer id,@RequestParam("password") String password);
    @RequestMapping("ant/findline")
    HashMap findline(@RequestParam("limit") Integer limit,@RequestParam("page") Integer page,@RequestParam("name") String name);
    @RequestMapping("ant/addLineUserC")
    void addLineUserC(@RequestParam("id") Integer id,@RequestParam("lid") Integer lid,@RequestParam("userId") Integer userId);
    @RequestMapping("ant/findcommontable")
    HashMap findcommontable(@RequestParam("limit") Integer limit,@RequestParam("page") Integer page);
    @RequestMapping("ant/findhei")
    HashMap findhei(@RequestParam("limit") Integer limit,@RequestParam("page") Integer page);
    @RequestMapping("ant/delheimingdan")
    void delheimingdan(@RequestParam("id") Integer id);
    @RequestMapping("ant/getprovinces")
    HashMap<String,Object> getprovinces();
    @RequestMapping("ant/getcity")
    HashMap<String,Object> getcity(@RequestParam("cid") String cid, @RequestParam("cname") String cname);
    @RequestMapping("ant/getcounty")
    HashMap<String,Object> getcounty(@RequestParam("cid") String cid);
    @RequestMapping("ant/getoftencity")
    HashMap<String,Object> getoftencity();
    @RequestMapping("ant/addsubmit")
    void addsubmit(@RequestBody DeliveryList deliveryList);
    @RequestMapping("ant/refer")
    @ResponseBody
    HashMap<String,Object> refer(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);
    @RequestMapping("ant/referline")
    @ResponseBody
    HashMap<String,Object> referline(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @RequestMapping("ant/refertcom")
    HashMap<String,Object> refertcom(@RequestParam("gid") Integer gid);
    @RequestMapping("ant/addinformation")
    void addinformation(@RequestParam("xid") Integer xid, @RequestBody DeliveryList deliveryList);
    @RequestMapping("/ant/getTop")
    List<Top> getTop();

    @RequestMapping("/ant/findCity")
    List<City> findCity();




    @RequestMapping("ant/findCompany")
    Integer findCompany(@RequestParam("name")String name);


    @RequestMapping("ant/findGsId")
    Long findGsId(@RequestParam("provenance")String provenance, @RequestParam("origin")String origin);
    @RequestMapping("ant/get")
    public String get();

    @RequestMapping("ant/getCity")
    List<CityModel> getCity();

    @RequestMapping("ant/getShi")
    List<CityModel> getShi(@RequestParam("id") Integer id);

    @RequestMapping("ant/getAll")
    @ResponseBody
    HashMap<String,Object> getAll(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit,@RequestParam("id") Integer id);

    @RequestMapping("ant/getXiang")
    @ResponseBody
    HashMap<String,Object> getXiang(@RequestParam("id") Integer id);

    @RequestMapping("ant/getZhaoBiao")
    @ResponseBody
    HashMap<String,Object> getZhaoBiao(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit,@RequestParam("biaoti") String biaoti);


    @RequestMapping("ant/addzhaobiao")
    void addzhaobiao(@RequestBody ZhaoBiaoModel zhaoBiaoModel);
}
