package com.learn.springcloud.configclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lh
 * @description 获取配置中心配置
 * @date 2020/6/20 13:46
 */
@RestController
public class ConfigClientController {

    @Value("${config.info}")
    String configInfo;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        String format = String.format("获取配置中心配置信息：{%s}", configInfo);
        System.out.println(format);
        return configInfo;
    }

}
