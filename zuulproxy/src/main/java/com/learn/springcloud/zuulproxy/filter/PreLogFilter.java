package com.learn.springcloud.zuulproxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 日志前置过滤器,请求前打印日志
 * @author: lh
 * @create: 2020-05-08 19:55
 **/
@Component
public class PreLogFilter extends ZuulFilter {

    /**
     * 过滤器类型，pre,post,routing,error
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序，数值越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否进行过滤，返回true 时会执行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 自定义的过滤器逻辑，当 shouldFilter（） 返回 true 时会执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String remoteHost = request.getRemoteHost();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        System.out.println(String.format("*** 日志 ****，Remote host:{%s},method:{%s},uri:{%s}", remoteHost, method, requestURI));
        return null;
    }

}
