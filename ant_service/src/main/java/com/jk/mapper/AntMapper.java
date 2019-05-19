package com.jk.mapper;


import com.jk.model.GongSiAndLine;
import com.jk.model.LoginUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}
