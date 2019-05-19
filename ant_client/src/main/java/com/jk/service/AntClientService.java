package com.jk.service;

import com.jk.bean.City;
import com.jk.bean.DeliveryList;
import com.jk.bean.Top;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@FeignClient("service")
public interface AntClientService {

    @RequestMapping("ant/getTop")
    List<Top> getTop();

    @RequestMapping("ant/findCity")
    List<City> findCity();


    @RequestMapping("ant/getprovinces")
    HashMap<String,Object> getprovinces();
    @RequestMapping("ant/getcity")
    HashMap<String,Object> getcity(@RequestParam("cid") String cid, @RequestParam("cname") String cname);
    @RequestMapping("ant/getcounty")
    HashMap<String,Object> getcounty(@RequestParam("cid") String cid);
    @RequestMapping("ant/getoftencity")
    HashMap<String,Object> getoftencity();

    @RequestMapping("ant/findCompany")
    Integer findCompany(@RequestParam("name")String name);


    @RequestMapping("ant/findGsId")
    Long findGsId(@RequestParam("provenance")String provenance, @RequestParam("origin")String origin);
}
