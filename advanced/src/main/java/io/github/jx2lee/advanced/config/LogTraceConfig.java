package io.github.jx2lee.advanced.config;

import io.github.jx2lee.advanced.trace.logtrace.FieldLogTrace;
import io.github.jx2lee.advanced.trace.logtrace.LogTrace;
import io.github.jx2lee.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        // return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}
