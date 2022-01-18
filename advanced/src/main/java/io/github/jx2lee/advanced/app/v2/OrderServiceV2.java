package io.github.jx2lee.advanced.app.v2;

import io.github.jx2lee.advanced.trace.TraceId;
import io.github.jx2lee.advanced.trace.TraceStatus;
import io.github.jx2lee.advanced.trace.hellotrace.HelloTraceV1;
import io.github.jx2lee.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId){

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exceptions(status, e);
            throw e;
        }
    }
}
