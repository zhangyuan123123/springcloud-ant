package com.jk.service;

import com.jk.bean.City;
import com.jk.bean.DeliveryList;
import com.jk.bean.Top;

import java.util.HashMap;
import java.util.List;

public interface AntService {

    List<Top> getTop();

    List<City> findCity();

    HashMap<String,Object> getprovinces();

    HashMap<String,Object> getcity(String cid);

    HashMap<String,Object> getcounty(String cid);

    Integer findCompany(String name);

    Long findGsId(String provenance,String origin);
}
