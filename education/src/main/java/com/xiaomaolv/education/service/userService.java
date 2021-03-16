package com.xiaomaolv.education.service;

import com.xiaomaolv.education.pojo.user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userService {

    user queryByUno(int uno);

    List<user> queryByFlag(String flag);
    void updateUser(user u);
    void deleteUser(int uno);
    int addUser(user u);
    user queryByid(@Param("no") String no, @Param("name") String name);
}
