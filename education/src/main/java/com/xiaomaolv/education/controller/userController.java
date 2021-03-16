package com.xiaomaolv.education.controller;

import com.xiaomaolv.education.pojo.user;
import com.xiaomaolv.education.service.userService;
import com.xiaomaolv.education.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class userController {

    @Autowired
    private userService user_service;

    // 第一次测试接口，可以用
//    @RequestMapping("test")
//    public String test(){
//        System.out.println("进入接口");
//        user user = user_service.queryByUno(3);
//        System.out.println(user);
//        if(user != null){
//            return  "ok";
//        }
//        return  "err";
//    }

    @RequestMapping("test")
    public R test(){
        System.out.println("进入接口");
        user user = user_service.queryByUno(3);
        System.out.println(user);
        if (user != null){

            return R.ok().data("User", user);
        }
        else {
            return R.error();
        }
    }


    //login
    @PostMapping("login")
    public R login(){
        System.out.println("进入登录接口");
        return R.ok().data("token","admin");
    }

    //userInfo
    @GetMapping("info")
    public R getInfo(){

        return R.ok().data("name", "zhangsan").data("avatar", "http://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //logout
    @PostMapping("logout")
    public R logout(){
        System.out.println("用户退出登录");
        return R.ok();
    }

    // getlist
    @GetMapping("getlist")
    public R getList(){
        List<user> userList = user_service.queryByFlag("1");
        return R.ok().data("rows", userList);
    }

}
