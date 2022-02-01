package io.github.jx2lee.proxy.config.v6_aop;

import io.github.jx2lee.proxy.config.AppV1Config;
import io.github.jx2lee.proxy.config.AppV2Config;
import io.github.jx2lee.proxy.config.v6_aop.aspect.LogTraceAspect;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace trace) {
        return new LogTraceAspect(trace);
    }
}
