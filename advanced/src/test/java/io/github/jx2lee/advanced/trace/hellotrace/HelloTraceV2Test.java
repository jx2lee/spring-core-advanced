package io.github.jx2lee.advanced.trace.hellotrace;

import io.github.jx2lee.advanced.trace.TraceId;
import io.github.jx2lee.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class HelloTraceV2Test {

    @Test
    void begin_end_print() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exceptions_print() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exceptions(status2, new IllegalStateException());
        trace.exceptions(status1, new IllegalStateException());
    }

    // 실제 테스트
    @Test
    void begin_end_mock() {
        HelloTraceV2 trace = Mockito.mock(HelloTraceV2.class);
        when(trace.begin("hello")).thenReturn(new TraceStatus(new TraceId(), 1L, "a"));
        TraceStatus status = trace.begin("hello");
        TraceStatus status1 = trace.beginSync(status.getTraceId(), "hello2");
        trace.end(status1);
        trace.end(status);
        verify(trace).end(status);
        verify(trace).end(status1);
    }

    @Test
    void begin_exceptions_mock() {
        HelloTraceV2 trace = Mockito.mock(HelloTraceV2.class);
        when(trace.begin("shitt")).thenReturn(new TraceStatus(new TraceId(), 1L, "a"));
        TraceStatus status = trace.begin("shitt");
        TraceStatus status1 = trace.beginSync(status.getTraceId(), "shitt2");
        IllegalStateException exception = new IllegalStateException();

        doNothing().when(trace).exceptions(status, exception);
        trace.exceptions(status, exception);
        trace.exceptions(status1, exception);
        verify(trace).exceptions(status, exception);
        verify(trace).exceptions(status1, exception);
    }

}
