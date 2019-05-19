package com.jk.service;

import com.jk.model.LoginUser;

import com.jk.model.DeliveryList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


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
}
