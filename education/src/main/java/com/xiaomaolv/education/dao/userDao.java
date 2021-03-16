package com.xiaomaolv.education.dao;

import com.xiaomaolv.education.pojo.user;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userDao {

    user queryByUno(int uno);
    List<user> queryByFlag(String flag);
    void updateUser(user u);
    void deleteUser(int uno);
    int addUser(user u);
    user queryByid(@Param("no") String no, @Param("name") String name);
}
