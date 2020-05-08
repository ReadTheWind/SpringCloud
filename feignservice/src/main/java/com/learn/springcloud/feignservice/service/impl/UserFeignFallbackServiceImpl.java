package com.learn.springcloud.feignservice.service.impl;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.common.domain.User;
import com.learn.springcloud.feignservice.service.UserFeignService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: feign服务降级实现
 * @author: lh
 * @create: 2020-05-08 10:39
 **/
@Component
public class UserFeignFallbackServiceImpl implements UserFeignService {

    User defaultUser = new User();

    public UserFeignFallbackServiceImpl() {
        defaultUser.setId(-1);
        defaultUser.setCode("default");
        defaultUser.setName("默认用户");
    }

    @Override
    public Result getAllUser() {
        return Result.success("降级返回", defaultUser);
    }

    @Override
    public Result getUserById(Integer id) {
        return Result.success("降级返回", defaultUser);
    }

    @Override
    public Result batchSaveUser(List<User> users) {
        return Result.success("降级返回", defaultUser);
    }
}
