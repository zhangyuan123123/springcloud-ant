package com.jk.mapper;

import com.jk.bean.City;
import com.jk.bean.DeliveryList;
import com.jk.bean.Top;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

public interface AntMapper {
    List<Top> getTop();

    List<City> findCity();

    List<LinkedHashMap<String,Object>> getprovinces();

    List<LinkedHashMap<String,Object>> getcity(String cid);

    List<LinkedHashMap<String,Object>> getcounty(String cid);

    Integer findCompany(String name);

    Long findGsId(@Param("provenance")String provenance,@Param("origin")String origin);

}
