package com.learn.springcloud.hystrixservices.services.impl;

import com.learn.springcloud.common.domain.Result;
import com.learn.springcloud.common.domain.User;
import com.learn.springcloud.hystrixservices.services.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author liuhuan
 * @Desc
 * @time 2020/4/9 10:19
 */
@Service
public class HystrixServiceImpl implements HystrixService {

    private RestTemplate restTemplate;

    @Autowired
    public HystrixServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${service.url.user-services-client}")
    private String userServiceUrl;


    @HystrixCommand(fallbackMethod = "defaultUser")
    @Override
    public Result getAllUser() {
        Result re = restTemplate.getForObject(userServiceUrl + "/user/getAllUser", Result.class);
        System.out.println(re);
        return re;
    }

    public Result defaultUser() {
        User user = new User();
        user.setUserName("默认用户");
        user.setPassWord("123456");
        return Result.success("hystrix 断路默认返回！", user);
    }


    /**
     * 测试忽略某个异常
     * <p>fallbackMethod 的 参数必须与注解方法参数一致，否则反射找不到对应方法</p>
     *
     * @return 返回参数
     */
    @HystrixCommand(
            fallbackMethod = "defaultUser2",
            ignoreExceptions = {ArrayIndexOutOfBoundsException.class, NullPointerException.class},
            commandKey = "getAllUserCroup",
            threadPoolKey = "getAllUserThreadPool"
    )
    @Override
    public Result getAllUserIgnoreSomeException(Integer id) {
        System.out.println("getAllUserIgnoreSomeException:id:" + id);
        if (id.equals(1)) {
            throw new NullPointerException();
        }
        if (id.equals(2)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (id.equals(3)) {
            throw new IllegalArgumentException();
        }

        return restTemplate.getForObject(userServiceUrl + "/user/getAllUser", Result.class);
    }

    public Result defaultUser2(Integer id) {
        User user = new User();
        user.setUserName("默认用户" + id);
        user.setPassWord("123456");
        return Result.success("hystrix 断路默认返回！", user);
    }

    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(
            fallbackMethod = "defaultUser2",
            commandKey = "getUserByIdWithCache"
    )
    @Override
    public Result getUserByIdWithCache(Integer id) {
        System.out.println("getUserByIdWithCache,id:" + id);

        //这两种方式都支持
//        return restTemplate.getForObject(userServiceUrl + "/user/getUser/{id}", Result.class, id);
        return restTemplate.getForObject(userServiceUrl + "/user/getUser/{1}", Result.class, id);
    }


    private String getCacheKey(Integer id) {
        return id.toString();
    }

    @CacheRemove(commandKey = "getUserByIdWithCache",cacheKeyMethod ="getCacheKey" )
    @Override
    public Result removeCache(Integer id) {
        System.out.println("测试移除缓存，id："+id);
        //TODO
        return null;
    }


    @Override
    public void batchSave(List<User> users) {
        restTemplate.postForEntity(userServiceUrl+"/user/batchSave",users,null);
    }
}
