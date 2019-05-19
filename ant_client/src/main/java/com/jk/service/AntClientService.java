package com.jk.service;


import com.jk.model.DeliveryList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


@FeignClient("service")
public interface AntClientService {
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
