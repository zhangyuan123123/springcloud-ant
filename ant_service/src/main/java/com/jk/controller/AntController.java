package com.jk.controller;


import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.ZhaoBiaoModel;
import com.jk.service.AntService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("ant")
public class AntController {
    @Resource
    private AntService antService;


    //物流公司的
    @RequestMapping("get")
    @ResponseBody
    public String get(){
        return "123";
    }

    //查询省的
    @RequestMapping("getCity")
    @ResponseBody
    public List<CityModel> getCity(){

        return antService.getCity();
    }
    //根据省的查询北京市下的
    @RequestMapping("getShi")
    @ResponseBody
    public List<CityModel> getShi(Integer id){
        return antService.getShi(id);
    }

    //查询物流公司
    @RequestMapping("getAll")
    @ResponseBody
    public HashMap<String,Object> getAll(Integer page,Integer limit,Integer id){
        //@RequestBody GongSiModel gongSiModel,
        return antService.getAll(page,limit,id);
    }
    // 根据物流公司的id详情查询 getXiang
    @RequestMapping("getXiang")
    @ResponseBody
    public HashMap<String,Object> getXiang(Integer id){
        HashMap<String,Object> all = antService.getXiang(id);
        return all;
    }

    //个人中心  招标管理页面 getZhaoBiao
    @RequestMapping("getZhaoBiao")
    @ResponseBody
    public HashMap<String,Object> getZhaoBiao(Integer page,Integer limit,String biaoti){
        HashMap<String,Object> all = antService.getZhaoBiao(page,limit,biaoti);
        return all;
    }


    @RequestMapping("addzhaobiao")
    @ResponseBody
    public String addzhaobiao(@RequestBody ZhaoBiaoModel zhaoBiaoModel){
        antService.addzhaobiao(zhaoBiaoModel);
        return null;
    }

}
