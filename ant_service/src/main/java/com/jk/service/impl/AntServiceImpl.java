package com.jk.service.impl;



import com.jk.mapper.AntMapper;
import com.jk.model.*;
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
}
