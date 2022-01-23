package io.github.jx2lee.proxy.trace.logtrace;

import io.github.jx2lee.proxy.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
