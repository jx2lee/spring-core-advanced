package io.github.jx2lee.advanced.app.v5;

import io.github.jx2lee.advanced.trace.callback.TraceCallback;
import io.github.jx2lee.advanced.trace.callback.TraceTemplate;
import io.github.jx2lee.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", (TraceCallback<Object>) () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
