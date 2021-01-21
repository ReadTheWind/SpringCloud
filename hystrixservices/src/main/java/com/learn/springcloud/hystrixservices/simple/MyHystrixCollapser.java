package com.learn.springcloud.hystrixservices.simple;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description: 合并请求
 * @Author: lh
 * @Date: 2021/1/21 15:29
 **/
public class MyHystrixCollapser extends HystrixCollapser<List<String>, String, String> {

    private final String name;

    public MyHystrixCollapser(String name) {
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
        return name;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, String>> collection) {
        return new BatchCommand(collection);
    }

    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, String>> collection) {
        int count = 0;
        for (CollapsedRequest<String, String> request : collection) {
            request.setResponse(strings.get(count++));
        }
    }

    private static final class BatchCommand extends HystrixCommand<List<String>> {
        private Collection<CollapsedRequest<String, String>> requests;

        public BatchCommand(Collection<CollapsedRequest<String, String>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BatchGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }

        @Override
        protected List<String> run() throws Exception {
            System.out.println("真正执行请求 。。。。");
            ArrayList<String> reponse = new ArrayList<>();
            for (CollapsedRequest<String, String> request : requests) {
                reponse.add("返回结果:" + request.getArgument());
            }
            return reponse;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> testColl1 = new MyHystrixCollapser("testColl").queue();
        Future<String> testColl2 = new MyHystrixCollapser("testColl2").queue();
        System.out.println(testColl1.get());
        System.out.println(testColl2.get());
        context.shutdown();
    }
}
