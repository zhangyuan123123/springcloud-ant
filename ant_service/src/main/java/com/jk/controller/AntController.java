package com.jk.controller;


import com.jk.model.LoginUser;
import com.jk.service.AntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;


@Controller
@RequestMapping("ant")
public class AntController {

    @Resource
    private AntService antService;

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
}
