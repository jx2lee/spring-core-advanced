package io.github.jx2lee.proxy.config.v1_proxy.concrete_proxy;

import io.github.jx2lee.proxy.app.v2.OrderRepositoryV2;
import io.github.jx2lee.proxy.app.v2.OrderServiceV2;
import io.github.jx2lee.proxy.trace.TraceStatus;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {
    private final OrderServiceV2 target;
    private final LogTrace trace;

    public OrderServiceConcreteProxy(OrderRepositoryV2 orderRepository, OrderServiceV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            target.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
