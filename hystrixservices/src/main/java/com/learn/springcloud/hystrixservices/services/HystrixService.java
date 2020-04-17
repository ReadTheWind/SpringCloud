package com.learn.springcloud.hystrixservices.services;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.common.domain.User;

/**
 * @author liuhuan
 * @Desc hystrix 功能展示接口
 * @time 2020/4/9 10:18
 */
public interface HystrixService {

    /**
     * 获取所有用户信息
     *
     * @return 用户信息
     */
    Result getAllUser();

    /**
     * 获取所有用户信息
     *
     * @return 用户信息
     */
    Result getAllUserIgnoreSomeException(Integer id);

}
