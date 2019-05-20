package com.jk.controller;


import com.jk.model.City;
import com.jk.model.LoginUser;
import com.jk.model.Top;
import com.jk.service.AntService;

import com.jk.model.DeliveryList;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.ZhaoBiaoModel;
import com.jk.service.AntService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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


    @Resource
    private StringRedisTemplate redis;


    @RequestMapping("setBlacklist")
    @ResponseBody
    public void setBlacklist(String phoneName){
        antService.setBlacklist(phoneName);
    }
    @RequestMapping("setBlacklist2")
    @ResponseBody
    public void setBlacklist2(String emailName){
        antService.setBlacklist2(emailName);
    }
    @RequestMapping("getUserByNameAndPwd")
    @ResponseBody
    public LoginUser getUserByNameAndPwd(@RequestBody LoginUser loginUser){
       return antService.getUserByNameAndPwd(loginUser);
    }
    @RequestMapping("findStatus")
    @ResponseBody
    public String findStatus(String phoneName){
        return antService.findStatus(phoneName);
    }
    @RequestMapping("findStatus2")
    @ResponseBody
    public String findStatus2(String emailName){
        return antService.findStatus2(emailName);
    }

    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(@RequestBody LoginUser loginUser){
        return antService.addUser(loginUser);
    }

    @RequestMapping("findUser")
    @ResponseBody
    public LoginUser findUser(Integer id){
        return antService.findUser(id);
    }
    @RequestMapping("updatePassWord")
    @ResponseBody
    public void updatePassWord(Integer id,String password){
          antService.updatePassWord(id,password);
    }
    @RequestMapping("findline")
    @ResponseBody
    public HashMap findline(Integer limit, Integer page,String name){
        return antService.findline(limit,page,name);
    }
    @RequestMapping("addLineUserC")
    @ResponseBody
    public void addLineUserC(Integer id,Integer lid,Integer userId){
         antService.addLineUserC(id,lid,userId);
    }
    @RequestMapping("findcommontable")
    @ResponseBody
    public HashMap findcommontable(Integer limit,Integer page){
        return antService.findcommontable(limit,page);
    }
    @RequestMapping("findhei")
    @ResponseBody
    public HashMap findhei(Integer limit,Integer page){
        return antService.findhei(limit,page);
    }
    @RequestMapping("delheimingdan")
    @ResponseBody
    public String delheimingdan(Integer id){
        antService.delheimingdan(id);
        return null;
    }

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


    /**
     * 获取导航条内容
     * @return
     */
    @RequestMapping("getTop")
    @ResponseBody
    public List<Top> getTop(){
        List<Top> list = antService.getTop();
        return list;
    }


    /**
     * 分站城市查询
     */
    @RequestMapping("findCity")
    @ResponseBody
    public List<City> findCity(){
        List<City> list = antService.findCity();
        return list;
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
