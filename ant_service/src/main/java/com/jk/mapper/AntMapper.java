package com.jk.mapper;

import com.jk.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

public interface AntMapper {

    List<LinkedHashMap<String,Object>> getprovinces();

    List<LinkedHashMap<String,Object>> getcity(String cid);

    List<LinkedHashMap<String,Object>> getcounty(String cid);

    void addsubmit(DeliveryList deliveryList);

    long count();

    List<GongSiModel> refer(@Param("page") Integer page, @Param("limit") Integer limit);

    List<LineModel> referline(@Param("page") Integer page, @Param("limit") Integer limit);

    long countline();

    List<DataModel> refertcom(@Param("gid") Integer gid);

    long countcom();

    SupperModel referall(Integer xid);
}
