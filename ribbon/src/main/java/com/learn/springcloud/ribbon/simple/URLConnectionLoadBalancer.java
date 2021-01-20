package com.learn.springcloud.ribbon.simple;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import rx.Observable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Url LoadBalancer Demo
 * @Author: lh
 * @Date: 2021/1/20 14:56
 **/
public class URLConnectionLoadBalancer {


    /**
     * 演示 Ribbon 负载均衡原理
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Server> serverList = new ArrayList<>();
        serverList.add(new Server("localhost", 8080));
        serverList.add(new Server("localhost", 8081));
        BaseLoadBalancer baseLoadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);

        for (int i = 0; i < 5; i++) {
            Object result = LoadBalancerCommand.builder()
                    .withLoadBalancer(baseLoadBalancer)
                    .build()
                    .submit(new ServerOperation<Object>() {
                        @Override
                        public Observable<Object> call(Server server) {
                            String httpUrl = "http://" + server.getHost() + ":" + server.getPort() + "/url";
                            System.out.println("调用地址:" + httpUrl);
                            try {
                                URL url = new URL(httpUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                return Observable.just(conn.getResponseMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                                return Observable.error(e);
                            }
                        }
                    }).toBlocking().first();
            System.out.println("调用结果：" + result);
        }
    }

}
