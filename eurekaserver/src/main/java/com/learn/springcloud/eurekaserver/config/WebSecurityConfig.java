package com.learn.springcloud.eurekaserver.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: liuhuan
 * @Desc: spring security 配置
 * @time: 2020/4/3 10:56
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity hs) throws Exception {
        hs.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(hs);
    }

}
