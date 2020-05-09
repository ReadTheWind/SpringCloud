# ZUUL 简介
> API网关为微服务架构中的服务提供了统一的访问入口，客户端通过API网关访问相关服务。
>API网关的定义类似于设计模式中的门面模式，它相当于整个微服务架构中的门面，
>所有客户端的访问都通过它来进行路由及过滤。
>它实现了请求路由、负载均衡、校验过滤、服务容错、服务聚合等功能

## 过滤器
> 路由与过滤是Zuul的两大核心功能，
>路由功能负责将外部请求转发到具体的服务实例上去，
>是实现统一访问入口的基础，过滤功能负责对请求过程进行额外的处理，
>是请求校验过滤及服务聚合的基础。

### 过滤器类型
- pre:在请求被路由到目标服务前执行，比如权限校验、打印日志功能
- routing: 在请求被路由到目标服务时执行，这是使用Apache HttpClient 或 Netflix Ribbon构建和原始发送HTTP 请求的地方
- post: 在请求被路由到目标服务后执行，比如给目标服务的响应添加头信息，手机统计数据等功能
- error: 请求在其他阶段发生错误时执行

### 禁用过滤器，配置格式
```properties
zuul:
  filterClassName:
    filter:
      disable: true 
```

### 核心过滤器
过滤器名称 | 过滤类型 | 优先级 | 过滤器的作用
----:|:----:|:----:|:----
ServletDetectionFilter | pre | -3 | 检测当前请求是通过 DispatcherServlet 处理运行的还是 ZuulServlet运行处理的
Servlet30WrapperFilter | pre | -2 | 对原始的 HttpServletRequest 进行包装
FormBodyWrapperFilter | pre | -1 | 	将Content-Type为application/x-www-form-urlencoded或multipart/form-data的请求包装成FormBodyRequestWrapper对象
DebugFilter | route | 1 | 根据zuul.debug.request 的配置来决定是否打印 debug 日志
PreDecorationFilter | route | 5 | 对当前请求进行预处理以便执行后续操作
RibbonRoutingFilter | route | 10 | 通过 Ribbon 和 Hystrix 来向服务实例发起请求，并将请求结果进行返回
SimpleHostRoutingFilter | route | 100 | 只对请求上下文有 routeHost 参数的进行处理，直接使用 HttpClient 向 routeHost 对应的屋里地址进行转发
SendForwardFilter | route | 500 | 只对请求上下文中有 forward.to 参数的进行处理，进行本地跳转
SendErrorFilter | post | 0 | 当其他过滤器内部发生异常时的会由它来进行处理，产生错误响应
SendResponseFilter | post | 1000 | 利用请求上下文的响应信息来组织请求成功的响应内容

## Ribbon 和 Hystrix 的支持
> 由于Zuul 自动集成了 Ribbon 和 Hystrix,因此 Zuul 天生就具有负载均衡和服务容错的能力，
>我们可以用过Ribbon 和 Hystrix 的配置来配置 Zuul 中相应功能
- 可以使用Hystrix 的配置来设置路由转发时 HystrixCommand 的执行超时时间
```yaml
hystrix:
  command: #用于控制HystrixCommand的行为
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 1000 #配置HystrixCommand执行的超时时间，执行超过该时间会进行服务降级处理
```

- 可以使用Ribbon 的配置来设置路由转发时请求连接及处理的超时时间
```yaml
ribbon: #全局配置
  ConnectTimeout: 1000 #服务请求连接超时时间（毫秒）
  ReadTimeout: 3000 #服务请求处理超时时间（毫秒）
```

