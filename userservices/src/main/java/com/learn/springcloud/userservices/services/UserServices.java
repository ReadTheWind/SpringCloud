package com.learn.springcloud.userservices.services;

import com.learn.springcloud.userservices.domain.User;

import java.util.List;

/**
 * @author: liuhuan
 * @Desc:
 * @time: 2020/4/4 12:01
 */
public interface UserServices {


    List<User> getAllUsers();

}
