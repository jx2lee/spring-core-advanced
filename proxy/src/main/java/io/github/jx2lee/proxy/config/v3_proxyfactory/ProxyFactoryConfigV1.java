package io.github.jx2lee.proxy.config.v3_proxyfactory;

import io.github.jx2lee.proxy.app.v1.*;
import io.github.jx2lee.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {


    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(trace));
        ProxyFactory factory = new ProxyFactory(orderController);
        factory.addAdvisor(getAdvisor(trace));
        OrderControllerV1 proxy = (OrderControllerV1) factory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", factory.getClass(), orderController.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(trace));
        ProxyFactory factory = new ProxyFactory(orderService);
        factory.addAdvisor(getAdvisor(trace));
        OrderServiceV1 proxy = (OrderServiceV1) factory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", factory.getClass(), orderService.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        ProxyFactory factory = new ProxyFactory(orderRepository);
        factory.addAdvisor(getAdvisor(trace));
        OrderRepositoryV1 proxy = (OrderRepositoryV1) factory.getProxy();

        log.info("ProxyFactory proxy={}, target={}", factory.getClass(), orderRepository.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save*", "request*", "order*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
