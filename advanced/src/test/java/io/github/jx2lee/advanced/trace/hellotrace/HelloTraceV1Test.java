package io.github.jx2lee.advanced.trace.hellotrace;

import io.github.jx2lee.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class HelloTraceV1Test {

    // 학습용 테스트
    // Q: void method 의 Test code 는 어떤식으로 작성하는가?
    // A: Mock 객체를 이용하여 Verify 로 함수 호출이 일어난 횟수를 테스트한다.
    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exceptions() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exceptions(status, new IllegalStateException());
    }

    // 실제 테스트
    @Test
    void begin_end_mock() {
        HelloTraceV1 trace = Mockito.mock(HelloTraceV1.class);
        TraceStatus status = trace.begin("hello");
        trace.end(status);
        verify(trace).end(status);
    }

    @Test
    void begin_exceptions_mock() {
        HelloTraceV1 trace = Mockito.mock(HelloTraceV1.class);
        TraceStatus status = trace.begin("shitt");
        IllegalStateException exception = new IllegalStateException();

        doNothing().when(trace).exceptions(status, exception);
        trace.exceptions(status, exception);
        verify(trace).exceptions(status, exception);
    }

}
