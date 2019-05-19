package com.jk.service.impl;


import com.jk.mapper.AntMapper;
import com.jk.model.GongSiAndLine;
import com.jk.model.LoginUser;

import com.jk.model.*;
import com.jk.service.AntService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

import java.util.LinkedHashMap;



@Service
public class AntServiceImpl implements AntService {
    @Resource
    private AntMapper antMapper;
    @Override
    public HashMap<String,Object> getprovinces() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<LinkedHashMap<String,Object>> list=antMapper.getprovinces();
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public HashMap<String,Object> getcity(String cid) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<LinkedHashMap<String,Object>> list=antMapper.getcity(cid);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> getcounty(String cid) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<LinkedHashMap<String,Object>> list=antMapper.getcounty(cid);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public void addsubmit(DeliveryList deliveryList) {
        antMapper.addsubmit(deliveryList);
    }

    @Override
    public HashMap<String, Object> refer(Integer page,Integer limit) {
        List<GongSiModel> list=antMapper.refer((page-1)*limit,limit);
        long count=antMapper.count();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data",list);
        hashMap.put("code",0);
        hashMap.put("msg","");
        hashMap.put("count",count);
        return hashMap;
    }


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
    @Override
    public HashMap<String, Object> referline(Integer page, Integer limit) {
        List<LineModel> list=antMapper.referline((page-1)*limit,limit);
        long count=antMapper.countline();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data",list);
        hashMap.put("code",0);
        hashMap.put("msg","");
        hashMap.put("count",count);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> refertcom(Integer gid) {
        List<DataModel> list=antMapper.refertcom(gid);
        long count=antMapper.countcom();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data",list);
        hashMap.put("code",0);
        hashMap.put("msg","");
        hashMap.put("count",count);
        return hashMap;
    }

    @Override
    public void addinformation(Integer xid, DeliveryList deliveryList) {
        SupperModel supperModel=antMapper.referall(xid);
        deliveryList.setGongsiid(supperModel.getGongsiid());
        deliveryList.setOrigin(supperModel.getChufadi());
        deliveryList.setProvenance(supperModel.getShifadi());
        antMapper.addsubmit(deliveryList);
    }


    /**
     * 获取导航条内容
     * @return
     */
    @Override
    public List<Top> getTop() {
        return antMapper.getTop();
    }


    /**
     * 分站城市查询
     * @return
     */
    @Override
    public List<City> findCity() {
        return antMapper.findCity();
    }




    /**
     * 公司查询
     * @param name
     * @return
     */
    @Override
    public Integer findCompany(String name) {

        return antMapper.findCompany(name);
    }


    /**
     * 线路和专线查询，返回公司ID
     * @param
     * @return
     */
    @Override
    public Long findGsId(String provenance,String origin) {
        Long did=antMapper.findGsId(provenance,origin);
        System.out.print("sss");
        return did;
    }


}
