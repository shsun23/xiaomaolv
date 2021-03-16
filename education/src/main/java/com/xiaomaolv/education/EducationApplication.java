package com.xiaomaolv.education;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 这个注解 mapperscan 扫描的其实是dao层的bean对象，可以放到配置类中
//@MapperScan("com.xiaomaolv.education.dao")
@SpringBootApplication
public class EducationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationApplication.class, args);
    }

}
