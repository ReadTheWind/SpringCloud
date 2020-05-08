package com.learn.springcloud.feignservice.controller;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.feignservice.service.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: Feign Controller
 * @author: lh
 * @create: 2020-05-08 09:58
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {

    private UserFeignService userFeignService;

    @Autowired
    public FeignController(UserFeignService userFeignService) {
        this.userFeignService = userFeignService;
    }

    @GetMapping("/getAllUser")
    public Result getAllUser() {
        return userFeignService.getAllUser();
    }

    @GetMapping("/getUserById/{id}")
    public Result getUserById(@PathVariable Integer id) {
        return userFeignService.getUserById(id);
    }

}
