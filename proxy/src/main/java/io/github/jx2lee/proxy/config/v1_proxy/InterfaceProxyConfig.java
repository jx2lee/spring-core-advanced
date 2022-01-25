package io.github.jx2lee.proxy.config.v1_proxy;

import io.github.jx2lee.proxy.app.v1.*;
import io.github.jx2lee.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import io.github.jx2lee.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import io.github.jx2lee.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(trace));
        return new OrderControllerInterfaceProxy(controllerImpl, trace);
    }

    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(trace));
        return new OrderServiceInterfaceProxy(serviceImpl, trace);
    }

    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, trace);
    }
}
