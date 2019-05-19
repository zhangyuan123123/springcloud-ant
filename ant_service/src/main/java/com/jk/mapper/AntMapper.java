package com.jk.mapper;

import com.jk.model.CityModel;
import com.jk.model.GongSiModel;
import com.jk.model.XingQingModel;
import com.jk.model.ZhaoBiaoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AntMapper {

    @Select("select * from t_area where pid=0 and id<>0 ")
    List<CityModel> getCity();

    @Select("select * from t_area where pid=#{id} and id<>0 ")
    List<CityModel> getShi(@Param("id") Integer id);

    @Select("select count(1) from t_company ")
    Integer getSumSize();
    //@Param("gongSiModel") GongSiModel gongSiModel,
    List<GongSiModel> getAll(@Param("page") Integer page,@Param("limit") Integer limit);


    List<GongSiModel> getAll2(@Param("id") Integer id);

    List<XingQingModel> getXiang(@Param("id") Integer id);

    @Select("select count(1) from t_zhaobiao")
    Integer getSumSizeZhaoBiao();


    List<ZhaoBiaoModel> getZhaoBiao(@Param("page") Integer page,@Param("limit") Integer limit,@Param("biaoti") String biaoti);

    void addzhaobiao(ZhaoBiaoModel zhaoBiaoModel);
}
