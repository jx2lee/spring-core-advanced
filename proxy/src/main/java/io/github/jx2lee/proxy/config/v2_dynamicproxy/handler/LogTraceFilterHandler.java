package io.github.jx2lee.proxy.config.v2_dynamicproxy.handler;

import io.github.jx2lee.proxy.trace.TraceStatus;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceFilterHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace trace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace trace, String[] patterns) {
        this.target = target;
        this.trace = trace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (!PatternMatchUtils.simpleMatch(patterns, method.getName())) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);

            Object result = method.invoke(target, args);
            trace.end(status);

            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
