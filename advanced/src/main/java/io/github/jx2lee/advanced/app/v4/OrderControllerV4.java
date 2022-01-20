package io.github.jx2lee.advanced.app.v4;

import io.github.jx2lee.advanced.trace.TraceStatus;
import io.github.jx2lee.advanced.trace.logtrace.LogTrace;
import io.github.jx2lee.advanced.trace.logtrace.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            // 로그는 애플리케이션 흐름에 영향을 주면 안된다. 고로 exception 을 반환해야한다. (thorw)
            throw e;
        }
    }
}
