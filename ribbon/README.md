#Ribbon 客户端负载均衡组件
> 负载均衡可以增加系统的可用性和扩展性，当我们使用RestTemplate 来调用其它服务时，ribbon 可以很方便的实现负载均衡。

## RestTemplate 使用
> http客户端，是我们可以很方便的调用http 接口，支持 GET、POST、PUT、DELETE等方法

- GET请求方法
```
<T> T getForObject(String url, Class<T> responseType, Object... uriVariables);

<T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables);

<T> T getForObject(URI url, Class<T> responseType);

<T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables);

<T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables);

<T> ResponseEntity<T> getForEntity(URI var1, Class<T> responseType);
```
- POST 请求方法
```$xslt
<T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables);

<T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables);

<T> T postForObject(URI url, @Nullable Object request, Class<T> responseType);

<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables);

<T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables);

<T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType);
```
- PUT 请求方法
```$xslt
void put(String url, @Nullable Object request, Object... uriVariables);

void put(String url, @Nullable Object request, Map<String, ?> uriVariables);

void put(URI url, @Nullable Object request);
```
- DELETE 请求方法
```$xslt
void delete(String url, Object... uriVariables);

void delete(String url, Map<String, ?> uriVariables);

void delete(URI url);
```

## @LoadBalanced 是如何实现负载均衡的
1. 使用  org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor 拦截 http 请求
2.在 LoadBalancerAutoConfiguration 维护 @LoadBalanced 的 RestTemplate 列表，配置拦截器为 LoadBalancerInterceptor
3.在拦截器中执行交给 LoadBalancerClient 处理请求

## 负载均衡策略介绍
1. Ribbon 默认负载均衡策略为 轮询
2.提供可选策略
- BestAvailable: 选择最小的并发请求的 Server ，追个考察 Server ，如果 Server 被标记为错误，则跳过，然后选择其中 ActiveRequestCount 最小的 Server。
- AvailabilityFilteringRule: 过滤掉那些一直连接失败的且被标记为 circuit tripped 的后端 Server,
并过滤掉那些高并发的后端 server 或者使用一个 AvailabilityPredicate 来包含过滤Server 的逻辑。
其实就是检查 STATUS 里记录的各个 Server 的状态。
- ZoneAvoidanceRule: 使用 ZoneAvoidancePredicate 和 AvailabilityPredicate 来判断是否选择某个 Server，
前一个判断判断一个 Zone 的运行性能是否可用，剔除不可用的 Zone(的所有 server),AvailabilityPredicate 用于过滤掉
连接数过多的 server.
- RandomRule: 随机选择一个 Server。
- RoundRobinRule: 轮询选择，轮询 index ,选择 index 对应位置的 Server.
- RetryRule: 对选的的赋值均衡策略机上重试机制
- ResponseTimeWeightRule: 作用同 WeightResponseTimeRule,ResponseTimeWeightedRule 后来改名为 WeightResponseTimeRule
- WeightedResponseTimeRule: 根据响应时间分配一个 Weight(权重)，响应时间越长，Weight 越小，被选中可能性越低。

3.自定义赋值策略
- 实现 IRule 接口，实现 choose 方法