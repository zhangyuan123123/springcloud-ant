package com.jk.controller;



import com.jk.model.DeliveryList;
import com.jk.service.AntService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;



@Controller
@RequestMapping("ant")
public class AntController {

    @Resource
    private StringRedisTemplate redis;
    @Resource
    private AntService antService;
    //选择城市
    @RequestMapping("getprovinces")
    @ResponseBody
    public HashMap<String,Object> getprovinces(){
        return antService.getprovinces();
    }
    @RequestMapping("getcity")
    @ResponseBody
    public HashMap<String,Object> getcity(String cid,String cname){
        return antService.getcity(cid);
    }
    @RequestMapping("getcounty")
    @ResponseBody
    public HashMap<String,Object> getcounty(String cid){
        return antService.getcounty(cid);
    }

    @RequestMapping("getoftencity")
    @ResponseBody
    public HashMap<String,Object> getoftencity(){
        String city = redis.opsForValue().get("city");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("rows",city);
        return hashMap;
    }
    //重新写这个方法
    @RequestMapping("addsubmit")
    @ResponseBody
    public String addsubmit(@RequestBody DeliveryList deliveryList){

        antService.addsubmit(deliveryList);
        return "1";
    }
    @RequestMapping("refer")
    @ResponseBody
    public HashMap<String,Object> refer(Integer page,Integer limit){

        return  antService.refer(page,limit);
    }
    @RequestMapping("referline")
    @ResponseBody
    public HashMap<String,Object> referline(Integer page,Integer limit){
        return  antService.referline(page,limit);
    }

    @RequestMapping("refertcom")
    @ResponseBody
    public HashMap<String,Object> refertcom(Integer gid){
        return antService.refertcom(gid);
    }
    @RequestMapping("addinformation")
    @ResponseBody
    public String addinformation(Integer xid,@RequestBody DeliveryList deliveryList){
        antService.addinformation(xid,deliveryList);
        return null;
    }
}
