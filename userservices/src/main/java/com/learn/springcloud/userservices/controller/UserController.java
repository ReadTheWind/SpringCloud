package com.learn.springcloud.userservices.controller;

import com.alibaba.fastjson.JSONObject;
import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.common.domain.User;
import com.learn.springcloud.userservices.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/getAllUser")
    public Result<List<User>> getAllUser() {
        List<User> allUsers = userServices.getAllUsers();
        logger.info("获取所有用户信息成功：{}", JSONObject.toJSONString(allUsers));
        return Result.success("获取成功", allUsers);
    }

    @GetMapping("/getUser/{id}")
    public Result<User> getUser(@PathVariable Integer id) {
        User user = userServices.getUserById(id);
        logger.info("获取用户：{}的信息：{}", id, JSONObject.toJSONString(user));
        return Result.success("获取成功", user);
    }

    @PostMapping("/batchSave")
    public Result<Integer> batchSave(@RequestBody List<User> users) {
        int i = userServices.BatchSave(users);
        logger.info("保存用户信息成功：{}", JSONObject.toJSONString(users));
        return Result.success("保存成功", i);
    }

}
