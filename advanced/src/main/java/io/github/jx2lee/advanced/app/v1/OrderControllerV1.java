package io.github.jx2lee.advanced.app.v1;

import io.github.jx2lee.advanced.trace.TraceStatus;
import io.github.jx2lee.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exceptions(status, e);
            // 로그는 애플리케이션 흐름에 영향을 주면 안된다. 고로 exception 을 반환해야한다. (thorw)
            throw e;
        }
    }
}
