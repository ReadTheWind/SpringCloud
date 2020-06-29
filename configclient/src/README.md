# Spring Cloud Config Client 
> 配置中客户端使用 Demo 

## 配置
- 引入 config client 客户端依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```
- 增加 bootstrap.yml 配置

## 添加 actuator 刷新
- 添加依赖
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
- 在bootstrap.yml中开启refresh端点：
```yml
management:
  endpoints:
    web:
      exposure:
        include: 'refresh'
```
- 在ConfigClientController类添加@RefreshScope注解用于刷新配置
- 调用refresh端点进行配置刷新: http://localhost:8109/actuator/refresh

## 配置中心添加安全配置
- 配置了配置中心的用户名和密码：
```yml
cloud:
    config:
      username: zhangsan
      password: 123456
```