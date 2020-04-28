package com.learn.springcloud.hystrixservices.controller;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.hystrixservices.services.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuhuan
 * @Desc
 * @time 2020/4/9 10:13
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    private HystrixService hystrixService;

    @Autowired
    public HystrixController(HystrixService hystrixService) {
        this.hystrixService = hystrixService;
    }

    @GetMapping("/getUser")
    public Result getUser() {
        Result allUser = hystrixService.getAllUser();
        return allUser;
    }

    @GetMapping("/getUserIgnoreException")
    public Result getUser(@RequestParam Integer id) {
        Result allUser = hystrixService.getAllUserIgnoreSomeException(id);
        return allUser;
    }
}
