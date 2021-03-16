package com.xiaomaolv.controller;

import com.xiaomaolv.service.userService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {

    private userService user_service;

    @RequestMapping("login")
    public String test(){
        System.out.println("进入接口");
        return  "ok";
    }
}
