package com.learn.springcloud.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: liuhuan
 * @Desc:
 * @time: 2020/4/7 14:22
 */
@RestController
@RequestMapping("/ribbon")
public class UserRibbonController {

    RestTemplate restTemplate;

    @Autowired
    public UserRibbonController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${service.url.user-services-client}")
    private String userServiceUrl;

    @RequestMapping("/getUser")
    public Object getAllUser(){
        return  restTemplate.getForObject(userServiceUrl+"/user/getUser",Object.class);
    }
}
