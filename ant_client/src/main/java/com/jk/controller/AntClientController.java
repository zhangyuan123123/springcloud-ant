package com.jk.controller;

import com.jk.bean.City;
import com.jk.bean.Top;
import com.jk.service.AntClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 跳转index页面
     */
    @RequestMapping("goindex")
    public String toindex(){
        return "index";
    }


    /**
     * 跳转index页面
     */
    @RequestMapping("sendPage")
    public String sendPage(){
        return "sendPage";
    }



    /**
     * 查询导航条内容
     */
    @RequestMapping("getTop")
    @ResponseBody
    public List<Top> getTop(){

        return antClientService.getTop();
    }


    /**
     * 分站城市查询
     */
    @RequestMapping("findCity")
    @ResponseBody
    public List<City> findCity(){
        return antClientService.findCity();
    }




    @RequestMapping("getprovinces")
    @ResponseBody
    public HashMap<String,Object> getprovinces(){
        return antClientService.getprovinces();
    }

    @RequestMapping("getcity")
    @ResponseBody
    public HashMap<String,Object> getcity(String cid,String cname){
        return antClientService.getcity(cid,cname);
    }
    @RequestMapping("getcounty")
    @ResponseBody
    public HashMap<String,Object> getcounty(String cid){
        return antClientService.getcounty(cid);
    }
    @RequestMapping("getoftencity")
    @ResponseBody
    public HashMap<String,Object> getoftencity(){
        return antClientService.getoftencity();
    }


    /**
     * 承运商查询
     */
    @RequestMapping("findCompany")
    public ModelAndView findCompany(String name){

        Integer cid=antClientService.findCompany(name);

        ModelAndView mv=new ModelAndView();
        mv.addObject("cid",cid);
        mv.setViewName("company");
        return mv;
    }


    /**
     * 线路查询，返回id
     */
    @RequestMapping("findGsId")
    @ResponseBody
    public Long findGsId(String provenance,String origin){
        Long did=antClientService.findGsId(provenance,origin);
        System.out.print("sss");
        return did;
    }


}
