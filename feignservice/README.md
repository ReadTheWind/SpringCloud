# Feign 
#### 集成使用
> 启动类增加 @EnableFeignClients ,如果你的 Feign 接口定义跟你的启动类不在一个包名下，还需要指定扫描的包名，
>参数 basePackages ;  
>接口标识增加 @FeignClient 注解,value 为 服务名,path 为 url 统一前缀

#### 契约配置 
> 原生的 Feign 不支持 SpringMVC 注解，可以通过配置契约来改变这个配置修改 Contract

#### BASIC 认证配置
- 1.配置 BASIC 认证，注入 BasicAuthRequestInterceptor 这个 Bean,构造传入用户名、密码
- 2.实现 RequestInterceptor 自定义的认证方式。

#### 超时时间配置
> 通过 Options 配置连接超时时间和读取超时时间，
> Options 第一个参数是连接超时时间（ms），默认是 10 x 1000，第二个是取超时时间 （ms）,默认值是 60 x 1000; 

#### 客户端组件配置
> Feign 默认使用 HTTPClient 来进行接口调用，可以集成其它组件替换掉，
>参考 org.springframework.cloud.openfeign.FeignAutoConfiguration

#### 编码器、解码器配置
> 在 Feign 的配置类中注册 Decoder 和 Encode 这两个类。