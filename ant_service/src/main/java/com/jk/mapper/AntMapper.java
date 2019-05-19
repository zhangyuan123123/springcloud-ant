package com.jk.mapper;


import com.jk.model.GongSiAndLine;
import com.jk.model.LoginUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jk.model.*;

import java.util.LinkedHashMap;

public interface AntMapper {

    void setBlacklist(String phoneName);

    void setBlacklist2(String emailName);

    LoginUser getUserByNameAndPwd(LoginUser loginUser);

    String findStatus(String phoneName);

    String findStatus2(String emailName);

    Long findUserByAccount(String account);

    void addUser(LoginUser loginUser);

    LoginUser findUser(Integer id);

    void updatePassWord(@Param("id") Integer id,@Param("password") String password);

    List<GongSiAndLine> findline(@Param("limit") Integer limit, @Param("page") Integer page,@Param("name") String name);

    Long getGongsiAndLineSumSize();

    void addLineUserC(@Param("id") Integer id,@Param("lid") Integer lid,@Param("userId") Integer userId);

    List<GongSiAndLine> findcommontable(@Param("limit") Integer limit,@Param("page")  Integer page);

    Long getcommontableSumSize();

    List<LoginUser> findhei(@Param("limit") Integer limit,@Param("page") int page);

    Long getheiSumSize();

    void delheimingdan(@Param("id") Integer id);

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
    List<Top> getTop();

    List<City> findCity();



    Integer findCompany(String name);

    Long findGsId(@Param("provenance")String provenance,@Param("origin")String origin);

}
