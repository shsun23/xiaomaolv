package com.xiaomaolv.education.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//这个注解 mapperscan 扫描的其实是dao层的bean对象，可以放到配置类中，实际上mapper 的xml位置，需要在配置文件中指定
@MapperScan("com.xiaomaolv.education.dao")
public class EduConfig {
}
