package com.jk.service.impl;


import com.jk.bean.City;
import com.jk.bean.DeliveryList;
import com.jk.bean.Top;
import com.jk.mapper.AntMapper;
import com.jk.service.AntService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


@Service
public class AntServiceImpl implements AntService {

    @Resource
    private AntMapper antMapper;


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


    @Override
    public HashMap<String,Object> getprovinces() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<LinkedHashMap<String,Object>> list=antMapper.getprovinces();
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> getcity(String cid) {
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
