package io.github.jx2lee.proxy.config.v1_proxy.concrete_proxy;

import io.github.jx2lee.proxy.app.v2.OrderControllerV2;
import io.github.jx2lee.proxy.app.v2.OrderServiceV2;
import io.github.jx2lee.proxy.trace.TraceStatus;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {
    private final OrderControllerV2 target;
    private final LogTrace trace;

    public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            String result = target.request(itemId);
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
