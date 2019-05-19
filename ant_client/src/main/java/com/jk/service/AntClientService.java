package com.jk.service;

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
