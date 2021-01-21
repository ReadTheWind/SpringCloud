package com.learn.springcloud.hystrixservices.simple;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description: 清除缓存
 * @Author: lh
 * @Date: 2021/1/21 15:10
 **/
public class ClearCacheHystrixCommand extends HystrixCommand<String> {

    private final String name;

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("myKey");

    public ClearCacheHystrixCommand(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("cleatCache")).
                andCommandKey(GETTER_KEY));
        this.name = name;
    }

    /**
     * 清空缓存
     *
     * @param name 缓存key
     */
    public static void flushCache(String name) {
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(name);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }

    @Override
    protected String run() throws Exception {
        System.out.println("get DATA");

        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "error";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();

        String test = new ClearCacheHystrixCommand("test").execute();
        System.out.println(test);

        //清除缓存
        ClearCacheHystrixCommand.flushCache("test");

        Future<String> test1 = new ClearCacheHystrixCommand("test").queue();
        System.out.println(test1.get());

        hystrixRequestContext.shutdown();
    }
}
