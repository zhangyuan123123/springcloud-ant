package com.jk.service.impl;


import com.jk.mapper.AntMapper;
import com.jk.model.GongSiAndLine;
import com.jk.model.LoginUser;
import com.jk.service.AntService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Service
public class AntServiceImpl implements AntService {

    @Resource
    private AntMapper antMapper;

    @Override
    public void setBlacklist(String phoneName) {
        antMapper.setBlacklist(phoneName);
    }
    @Override
    public void setBlacklist2(String emailName) {
        antMapper.setBlacklist2(emailName);
    }

    @Override
    public LoginUser getUserByNameAndPwd(LoginUser loginUser) {
        LoginUser userByNameAndPwd = antMapper.getUserByNameAndPwd(loginUser);
        return userByNameAndPwd;
    }

    @Override
    public String findStatus(String phoneName) {
        return antMapper.findStatus(phoneName);
    }

    @Override
    public String findStatus2(String emailName) {
        return antMapper.findStatus2(emailName);
    }

    @Override
    public String addUser(LoginUser loginUser) {
        String flg="";
        Long count=antMapper.findUserByAccount(loginUser.getAccount());
        if(count==0){
            antMapper.addUser(loginUser);
            flg="1";
        }else{
            flg="3";
        }
        return flg;
    }

    @Override
    public LoginUser findUser(Integer id) {
        return antMapper.findUser(id);
    }

    @Override
    public void updatePassWord(Integer id, String password) {
        antMapper.updatePassWord(id,password);
    }

    @Override
    public HashMap findline(Integer limit, Integer page,String name) {
        HashMap hashMap = new HashMap<String,Object>();
        List<GongSiAndLine> list = antMapper.findline(limit, (page-1)*limit,name);
        Long sumSize=antMapper.getGongsiAndLineSumSize();
        hashMap.put("data",list);
        hashMap.put("count",sumSize);
        hashMap.put("code",0);
        hashMap.put("msg","");
        return hashMap;
    }

    @Override
    public void addLineUserC(Integer id, Integer lid, Integer userId) {
          antMapper.addLineUserC(id,lid,userId);
    }

    @Override
    public HashMap findcommontable(Integer limit, Integer page) {
        HashMap hashMap = new HashMap<String,Object>();
        List<GongSiAndLine> list = antMapper.findcommontable(limit, (page-1)*limit);
        Long sumSize=antMapper.getcommontableSumSize();
        hashMap.put("data",list);
        hashMap.put("count",sumSize);
        hashMap.put("code",0);
        hashMap.put("msg","");
        return hashMap;
    }

    @Override
    public HashMap findhei(Integer limit, Integer page) {
        HashMap hashMap = new HashMap<String,Object>();
        List<LoginUser> list = antMapper.findhei(limit, (page-1)*limit);
        Long sumSize=antMapper.getheiSumSize();
        hashMap.put("data",list);
        hashMap.put("count",sumSize);
        hashMap.put("code",0);
        hashMap.put("msg","");
        return hashMap;
    }

    @Override
    public void delheimingdan(Integer id) {
        antMapper.delheimingdan(id);
    }
}
