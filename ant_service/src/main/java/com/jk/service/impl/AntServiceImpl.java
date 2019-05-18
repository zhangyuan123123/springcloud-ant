package com.jk.service.impl;


import com.jk.mapper.AntMapper;
import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.XingQingModel;
import com.jk.model.ZhaoBiaoModel;
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
    public List<CityModel> getCity() {

        return antMapper.getCity();
    }

    @Override
    public List<CityModel> getShi(Integer id) {
        return antMapper.getShi(id);
    }

    @Override
    public HashMap<String,Object> getAll(Integer page,Integer limit,Integer id) {
        //GongSiModel gongSiModel,
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer count = antMapper.getSumSize();
        if(id==null){
            List<GongSiModel> list = antMapper.getAll((page-1)*limit,limit);
            hashMap.put("count",count);
            hashMap.put("data",list);
            hashMap.put("code",0);
            hashMap.put("msg","");
        }
        if(id!=null){
            List<GongSiModel> list = antMapper.getAll2(id);
            hashMap.put("count",count);
            hashMap.put("data",list);
            hashMap.put("code",0);

        }

        return hashMap;
    }

    @Override
    public HashMap<String, Object> getXiang(Integer id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer count = antMapper.getSumSize();
        if(id!=null){
            List<XingQingModel> list = antMapper.getXiang(id);
            hashMap.put("count",count);
            hashMap.put("data",list);
            hashMap.put("code",0);

        }
        return hashMap;
    }

    @Override
    public HashMap<String, Object> getZhaoBiao(Integer page, Integer limit,String biaoti) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer count = antMapper.getSumSizeZhaoBiao();
        List<ZhaoBiaoModel> list = antMapper.getZhaoBiao((page-1)*limit,limit,biaoti);
        hashMap.put("count",count);
        hashMap.put("data",list);
        hashMap.put("code",0);
        hashMap.put("msg","");
        return hashMap;
    }

    @Override
    public void addzhaobiao(ZhaoBiaoModel zhaoBiaoModel) {
        antMapper.addzhaobiao(zhaoBiaoModel);
    }
}
