package com.learn.springcloud.userservices;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.learn.springcloud")
@ComponentScan(basePackages = "com.learn.springcloud")
@MapperScan("com.learn.springcloud.common.mapper")
public class UserservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserservicesApplication.class, args);
    }

}
