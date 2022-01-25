package io.github.jx2lee.proxy.config.v1_proxy.concrete_proxy;

import io.github.jx2lee.proxy.app.v2.OrderRepositoryV2;
import io.github.jx2lee.proxy.trace.TraceStatus;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {
    private final OrderRepositoryV2 target;
    private final LogTrace trace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            target.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
