package com.learn.springcloud.userservices.controller;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.common.domain.User;
import com.learn.springcloud.userservices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllUser")
    public Result getAllUser() {
        List<User> allUsers = userServices.getAllUsers();
        return Result.success("获取成功", allUsers);
    }

    @GetMapping("/getUser/{id}")
    public Result getUser(@PathVariable Integer id) {
        User user = userServices.getUserById(id);
        return Result.success("获取成功", user);
    }

    @PostMapping("/batchSave")
    public Result batchSave(@RequestBody List<User> users) {
        int i = userServices.BatchSave(users);
        return Result.success("获取成功", i);
    }

}
