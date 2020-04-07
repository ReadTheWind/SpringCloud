package com.learn.springcloud.userservices.services.impl;

import com.learn.springcloud.userservices.domain.User;
import com.learn.springcloud.userservices.services.UserServices;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author: liuhuan
 * @Desc: 用户接口实现类
 * @time: 2020/4/4 12:01
 */
@Service
public class UserServicesImpl implements UserServices {

    @Override
    public List<User> getAllUsers() {
        User user1 = new User();
        user1.setPassWord("123456");
        user1.setUserName("ribbon");
        System.out.println("获取所有redis信息！");
        return Collections.singletonList(user1);
    }
}
