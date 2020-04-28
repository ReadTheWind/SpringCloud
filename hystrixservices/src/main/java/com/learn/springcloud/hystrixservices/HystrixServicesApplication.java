package com.learn.springcloud.hystrixservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * EnableCircuitBreaker 开启断路器功能
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixServicesApplication.class, args);
    }

}
