package com.jk.controller;

import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.ZhaoBiaoModel;
import com.jk.service.AntClientService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("ant")
public class AntClientController {

    @Resource
    private AntClientService antClientService;

    //简单测试框架
    @RequestMapping("get")
    @ResponseBody
    public String get(){
        return antClientService.get();
    }
    //公司页面
    @RequestMapping("company")
    public String getCompany(){
        return "company";
    }

    //公司页面
    @RequestMapping("toindex222")
    public String index(){
        return "index";
    }

    //查询省的
    @RequestMapping("getCity")
    @ResponseBody
    public List<CityModel> getCity(){
        return antClientService.getCity();
    }

    //查询市的  报错
    @RequestMapping("getShi")
    @ResponseBody
    public List<CityModel> getShi(Integer id){
        return antClientService.getShi(id);
    }

    //物流分页查询
    @RequestMapping("getAll")
    @ResponseBody
    public HashMap<String,Object> getAll(Integer page,Integer limit,Integer id){
        //@RequestBody GongSiModel gongSiModel,
        HashMap<String,Object> all = antClientService.getAll(page,limit,id);
        return all;
    }
    // 根据物流公司的id详情查询 getXiang
    @RequestMapping("getXiang")
    @ResponseBody
    public HashMap<String,Object> getXiang(Integer id){
        //@RequestBody GongSiModel gongSiModel,
        HashMap<String,Object> all = antClientService.getXiang(id);
        return all;
    }

    //转发页面
    @RequestMapping("tofindPage")
    public String findPage(Integer id, Model model){
        model.addAttribute("id",id);
        return "findpage";
    }

    //招标管理页面
    @RequestMapping("tozhaobiao")
    public String zhaobiao(){
        return "zhaobiao";
    }


    //个人中心  招标管理页面 getZhaoBiao
    @RequestMapping("getZhaoBiao")
    @ResponseBody
    public HashMap<String,Object> getZhaoBiao(Integer page,Integer limit,String biaoti){
        HashMap<String,Object> all = antClientService.getZhaoBiao(page,limit,biaoti);
        return all;
    }

    //转发到新增页面 addPage
    @RequestMapping("addPage")
    public ModelAndView addPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addPage");
        return mv;
    }

    //新增招标信息
    @RequestMapping("addzhaobiao")
    @ResponseBody
    public String addzhaobiao(ZhaoBiaoModel zhaoBiaoModel){
        antClientService.addzhaobiao(zhaoBiaoModel);
        return null;
    }


}
