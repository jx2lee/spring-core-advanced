package io.github.jx2lee.advanced.app.v2;

import io.github.jx2lee.advanced.trace.TraceId;
import io.github.jx2lee.advanced.trace.TraceStatus;
import io.github.jx2lee.advanced.trace.hellotrace.HelloTraceV1;
import io.github.jx2lee.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;
    public void save(TraceId traceId, String itemId) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderRepository.save()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("에러 발생");
            }

            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exceptions(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
