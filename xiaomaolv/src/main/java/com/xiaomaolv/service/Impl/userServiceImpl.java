package com.xiaomaolv.service.Impl;


import com.xiaomaolv.dao.userDao;
import com.xiaomaolv.pojo.user;
import com.xiaomaolv.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private userDao userdao;

    public int login(String name,String no,String password){

        int r=0;
        user u=userdao.queryByid(no,name);
        if(u!=null){
            System.out.println("sisjf");
            if(u.getPassword().equals(password))
                r=1;
            else
                r=2;
        }
        return r;
    }

    @Override
    public user queryByUno(int uno) {
        return userdao.queryByUno(uno);
    }

    @Override
    public List<user> queryByFlag(String flag) {
        return userdao.queryByFlag(flag);
    }

    @Override
    public void updateUser(user u) {
        userdao.updateUser(u);
    }

    @Override
    public void deleteUser(int uno) {
        userdao.deleteUser(uno);
    }

    @Override
    public int addUser(user u) {
        return userdao.addUser(u);
    }

    @Override
    public user queryByid(String no, String name) {
        return userdao.queryByid(no, name);
    }
}
