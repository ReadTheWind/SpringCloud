package com.learn.springcloud.hystrixservices.controller;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.hystrixservices.services.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return hystrixService.getAllUser();
    }

    @GetMapping("/getUserIgnoreException")
    public Result getUser(@RequestParam Integer id) {
        return hystrixService.getAllUserIgnoreSomeException(id);
    }

    @GetMapping("/getUserByIdWithCache/{id}")
    public Result getUserByIdWithCache(@PathVariable Integer id) {
        return hystrixService.getUserByIdWithCache(id);
    }
}
