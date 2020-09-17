package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;


public interface UserDAO {
    void save(User user);

    //多参数需要用@Param进行绑定
    User login(@Param("username") String username, @Param("password") String password);
}
