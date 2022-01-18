package io.github.jx2lee.advanced.trace.logtrace;

import io.github.jx2lee.advanced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
