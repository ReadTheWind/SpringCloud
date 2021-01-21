package com.learn.springcloud.hystrixservices.simple;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description: 自定义 command
 * @Author: lh
 * @Date: 2021/1/21 8:57
 **/
public class MyHystrixCommand extends HystrixCommand<String> {


    private final String name;

    public MyHystrixCommand(String name) {
//        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));

        // 信号量配置：信号量隔离也可用于限制并发访问，防止阻塞扩散，与线程隔离最大不同在于执行依赖代码的线程依然是请求线程（该线程需要通过信号申请，
        // 如果客户端是可信的且可以快速返回，可以使用信号隔离替换线程隔离，降低开销。信号量的大小可以动态调整，线程池大小不可以。）
//        super(HystrixCommand.Setter
//                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
//                .andCommandPropertiesDefaults(
//                        HystrixCommandProperties.Setter().withExecutionIsolationStrategy(
//                                HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
//                        )
//                )
//        );

        //线程隔离策略配置（默认策略）,配置线程池参数
        //在请求和服务之间加入了线程池，为每个依赖调用分配一个小的线程池，如果线程池已满调用将被立即拒绝，默认不采用排队，加速失败判定时间。线程数是可以被设定的
        //原理：用户的请求将不再直接访问服务，而是通过线程池中的空闲线程来访问服务，如果线程池已满，则会进行降级处理，用户的请求不会被阻塞，至少可以看到一个执行结果
        // （例如返回友好的提示信息）而不是无休止的等待或者看到系统崩溃
        super(HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionIsolationStrategy(
                                HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
                        )
                ).andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withMaxQueueSize(100)
                        .withMaximumSize(100)
                )
        );

        this.name = name;
    }

    @Override
    protected String run() throws Exception {
//        try {
//            // 默认超时时间是 1000，睡眠会抛出中断异常
//            Thread.sleep(1000 * 2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("get DATA");

        return this.name + ":" + Thread.currentThread().getName();
    }

    /**
     * 回退支持
     *
     * @return 回退内容
     */
    @Override
    protected String getFallback() {
        return "error!!!";
    }

    /**
     * 结果缓存，重写 getCacheKey 使方法同样的参数使用缓存，需要初始化 Hystrix-RequestContext
     * @return
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();

        String test = new MyHystrixCommand("test").execute();
        System.out.println(test);

        Future<String> test1 = new MyHystrixCommand("test").queue();
        System.out.println(test1.get());

        hystrixRequestContext.shutdown();
    }
}
