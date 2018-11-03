package com.ranze.likechat.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ranze.likechat.web", "com.ranze.likechat.common"})
@MapperScan(basePackages = "com.ranze.likechat.web.mapper")
public class LikechatserverWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikechatserverWebApplication.class, args);
    }
}
