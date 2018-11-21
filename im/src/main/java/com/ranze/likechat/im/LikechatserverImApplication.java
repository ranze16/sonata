package com.ranze.likechat.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ranze.likechat.im", "com.ranze.likechat.common"})
public class LikechatserverImApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikechatserverImApplication.class, args);
    }
}
