package io.github.jx2lee.advanced.trace.logtrace;

import io.github.jx2lee.advanced.trace.TraceId;
import io.github.jx2lee.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ThreadLocalLogTraceTest {
    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();
    @Test
    void begin_end_level2() {
        TraceStatus status = trace.begin("level1");
        TraceStatus status1 = trace.begin("level2");
        trace.end(status1);
        trace.end(status);
    }

    @Test
    void begin_exception_level2() {
        TraceStatus status = trace.begin("level1");
        TraceStatus status1 = trace.begin("level2");
        trace.exception(status1, new IllegalStateException());
        trace.exception(status, new IllegalStateException());
    }

    @Test
    void begin_end_level2_mock() {
        ThreadLocalLogTrace trace = mock(ThreadLocalLogTrace.class);
        when(trace.begin("hello")).thenReturn(new TraceStatus(new TraceId(), 1L, "hello"));
        TraceStatus status = trace.begin("hello");
        trace.end(status);

        verify(trace, times(1)).begin("hello");
        verify(trace, times(1)).end(status);
    }

    @Test
    void begin_exception_level2_mock() {
        // given
        ThreadLocalLogTrace trace = mock(ThreadLocalLogTrace.class);
        when(trace.begin("hello")).thenReturn(new TraceStatus(new TraceId(), 1L, "hello"));
        when(trace.begin("hello1")).thenReturn(new TraceStatus(new TraceId(), 1L, "hello1"));

        // when
        TraceStatus status = trace.begin("hello");
        TraceStatus status1 = trace.begin("hello1");
        IllegalStateException exception = new IllegalStateException();

        trace.exception(status, exception);
        trace.exception(status1, exception);

        // then
        verify(trace, times(1)).exception(status,exception);
        verify(trace, times(1)).exception(status1, exception);
    }
}
