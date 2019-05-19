package com.jk.service;

import com.jk.model.DeliveryList;

import java.util.HashMap;

public interface AntService {

    HashMap<String,Object> getprovinces();

    HashMap<String,Object> getcity(String cid);

    HashMap<String,Object> getcounty(String cid);

    void addsubmit(DeliveryList deliveryList);

    HashMap<String,Object> refer(Integer page, Integer limit);

    HashMap<String,Object> referline(Integer page, Integer limit);

    HashMap<String,Object> refertcom(Integer gid);

    void addinformation(Integer xid, DeliveryList deliveryList);
}
