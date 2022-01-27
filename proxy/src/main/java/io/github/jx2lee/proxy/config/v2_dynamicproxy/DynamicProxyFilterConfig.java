package io.github.jx2lee.proxy.config.v2_dynamicproxy;

import io.github.jx2lee.proxy.app.v1.*;
import io.github.jx2lee.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import io.github.jx2lee.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private final String[] PATTERNS = {"order*", "request*", "save*"};


    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(trace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderController, trace, PATTERNS));
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(trace));
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderService, trace, PATTERNS));
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepository, trace, PATTERNS));
        return proxy;
    }
}
