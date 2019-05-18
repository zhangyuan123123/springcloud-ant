package com.jk.service;

import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.ZhaoBiaoModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

public interface AntService {

    List<CityModel> getCity();

    List<CityModel> getShi(Integer id);

    HashMap<String,Object> getAll(Integer page,Integer limit,Integer id);

    HashMap<String,Object> getXiang(Integer id);

    HashMap<String,Object> getZhaoBiao(Integer page, Integer limit ,String biaoti);

    void addzhaobiao(ZhaoBiaoModel zhaoBiaoModel);
}
