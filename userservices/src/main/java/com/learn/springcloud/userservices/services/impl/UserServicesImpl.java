package com.learn.springcloud.userservices.services.impl;

import com.learn.springcloud.common.domain.User;
import com.learn.springcloud.common.mapper.UserMapper;
import com.learn.springcloud.userservices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liuhuan
 * @Desc: 用户接口实现类
 * @time: 2020/4/4 12:01
 */
@Service
public class UserServicesImpl implements UserServices {

    private final UserMapper userMapper;

    @Autowired
    public UserServicesImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUser = userMapper.getAllUser();
        System.out.println("获取所有redis信息！" + allUser.toString());
        return allUser;
    }
}
