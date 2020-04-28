package com.learn.springcloud.userservices.controller;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.common.domain.User;
import com.learn.springcloud.userservices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: liuhuan
 * @Desc: 用户对象 controller,用于提供调用接口
 * @time: 2020/4/4 11:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @RequestMapping("/getUser")
    public Result getUser() {
        List<User> allUsers = userServices.getAllUsers();
        return Result.success("获取成功", allUsers);
    }

}
