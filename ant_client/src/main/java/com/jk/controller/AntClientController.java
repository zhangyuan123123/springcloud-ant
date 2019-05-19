package com.jk.controller;


import com.jk.model.DeliveryList;

import com.jk.service.AntClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;

import javax.annotation.Resource;

@Controller
@RequestMapping("ant")
public class AntClientController {
    @Resource
    private AntClientService antClientService;
    @RequestMapping("toindex")
    public String toindex(){
        return "sendPage";
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
    @RequestMapping("addsubmit")
    @ResponseBody
    public String addsubmit(DeliveryList deliveryList,Integer gid){
        deliveryList.setGongsiid(gid);
        antClientService.addsubmit(deliveryList);
        return "1";
    }
    @RequestMapping("toshow")
    public String toshow(){
        return "show";
    }
    @RequestMapping("refer")
    @ResponseBody
    public HashMap<String,Object> refer(Integer page,Integer limit){
        return antClientService.refer(page,limit);
    }

    @RequestMapping("page")
    public ModelAndView page(DeliveryList formdata){
        ModelAndView mv = new ModelAndView();
        mv.addObject("formdata",formdata);
        mv.setViewName("show");
        return mv;
    }
    @RequestMapping("toindexvalue")
    public ModelAndView toindexvalue(Integer gid){
        ModelAndView mv = new ModelAndView();
        mv.addObject("gid",gid);
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("toline")
    public String totext(){
        return "line";
    }

    @RequestMapping("referline")
    @ResponseBody
    public HashMap<String,Object> referline(Integer page,Integer limit){
        return antClientService.referline(page,limit);
    }
    @RequestMapping("tocom")
    public ModelAndView tocom(Integer gid){
        ModelAndView mv = new ModelAndView();
        mv.addObject("gid",gid);
        mv.setViewName("company");
        return mv;
    }

    @RequestMapping("refertcom")
    @ResponseBody
    public HashMap<String,Object> refertcom(Integer gid){
        return antClientService.refertcom(gid);
    }

    @RequestMapping("toadd")
    public ModelAndView toadd(Integer xid){
        ModelAndView mv = new ModelAndView();
        mv.addObject("xid",xid);
        mv.setViewName("addpeople");
        return mv;
    }
    @RequestMapping("addinformation")
    @ResponseBody
    public String addinformation(Integer xid,DeliveryList deliveryList){
        antClientService.addinformation(xid,deliveryList);
        return "1";
    }
}
