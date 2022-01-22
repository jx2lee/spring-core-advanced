package io.github.jx2lee.advanced.app.v4;

import io.github.jx2lee.advanced.trace.TraceStatus;
import io.github.jx2lee.advanced.trace.logtrace.LogTrace;
import io.github.jx2lee.advanced.trace.logtrace.ThreadLocalLogTrace;
import io.github.jx2lee.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;
    public void save(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("에러 발생");
                }

                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
