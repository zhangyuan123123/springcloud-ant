package com.jk.controller;


import com.jk.bean.City;
import com.jk.bean.DeliveryList;
import com.jk.bean.Top;
import com.jk.service.AntService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("ant")
public class AntController {

    @Resource
    private AntService antService;

    @Resource
    private StringRedisTemplate redis;


    /**
     * 获取导航条内容
     * @return
     */
    @RequestMapping("getTop")
    @ResponseBody
    public List<Top> getTop(){
        return antService.getTop();
    }


    /**
     * 分站城市查询
     */
    @RequestMapping("findCity")
    @ResponseBody
    public List<City> findCity(){
        return antService.findCity();
    }




    @RequestMapping("getprovinces")
    @ResponseBody
    public HashMap<String,Object> getprovinces(){
        return antService.getprovinces();
    }
    @RequestMapping("getcity")
    @ResponseBody
    public HashMap<String,Object> getcity(String cid,String cname){
        redis.opsForValue().set("city",cname);
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


    /**
     * 公司查询  findCompany
     */
    @RequestMapping("findCompany")
    @ResponseBody
    public Integer findCompany(String name){
        Integer cid=antService.findCompany(name);
        return cid;
    }


    /**
     * 线路专线查询，返回公司ID
     */
    @RequestMapping("findGsId")
    @ResponseBody
    public Long findGsId(String provenance,String origin){
        Long did=antService.findGsId(provenance,origin);
        System.out.print("sssss");
        return did;
    }




}
