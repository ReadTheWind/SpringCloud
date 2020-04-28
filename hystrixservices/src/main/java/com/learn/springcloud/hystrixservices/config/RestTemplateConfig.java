package com.learn.springcloud.hystrixservices.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuhuan
 * @Desc
 * @time 2020/4/9 16:44
 */
@Configuration
public class RestTemplateConfig {

    /**
     * @LoadBalanced 注解不使用时，服务地址需换成ip地址，而非客户端名称
     * @return
     */
    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
