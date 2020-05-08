package com.learn.springcloud.feignservice.service;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.common.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description: Feign 调用 userServices 服务
 * @author: lh
 * @create: 2020-05-08 09:40
 **/
@FeignClient(value = "user-services-client")
public interface UserFeignService {

    @GetMapping("/user/getAllUser")
    Result getAllUser();

    @GetMapping("/user/getUser/{id}")
    Result getUserById(@PathVariable Integer id);

    @PostMapping("/user/batchSave")
    Result batchSaveUser(@RequestBody List<User> users);

}
