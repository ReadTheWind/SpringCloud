# Spring Cloud Config 外部集中化配置管理
## 简介
> Spring Cloud Config 分为服务端和客户端。服务端被称为分布式配置中心，它是个独立的应用，可以从配置仓库获取配置信息并提供给客户端使用。
>客户端可以通过配置中心来获取配置信息，在启动时加载配置。Spring Cloud 

## 通过 config-server 获取配置信息
- 获取配置文件信息的访问格式
    - 获取配置信息  /{label}/{application}-{profile} 
    - 获取配置文件信息  /{label}/{application}-{profile}.yml 
- 占位符相关解释
    - application：代表应用名称，默认为配置文件中的spring.application.name，如果配置了spring.cloud.config.name，则为该名称；
    - label：代表分支名称，对应配置文件中的spring.cloud.config.label；
    - profile：代表环境名称，对应配置文件中的spring.cloud.config.profile。
- 获取配置信息演示  
    - 获取master分支上test环境的配置信息: http://localhost:8108/master/config-test || http://localhost:8108/master/config-server-test
    - 获取master分支上dev环境的配置文件信息: http://localhost:8108/master/config-test.yml


## 配置中心添加安全配置
- 添加依赖
```xml 
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
- yml 中配置用户名密码
```yml
spring:
  security: #配置用户名和密码
    user:
      name: zhangsan
      password: 123456
```
