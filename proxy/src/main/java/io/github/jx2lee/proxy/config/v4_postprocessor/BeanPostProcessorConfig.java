package io.github.jx2lee.proxy.config.v4_postprocessor;

import io.github.jx2lee.proxy.config.AppV1Config;
import io.github.jx2lee.proxy.config.AppV2Config;
import io.github.jx2lee.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import io.github.jx2lee.proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace trace) {
        return new PackageLogTracePostProcessor("io.github.jx2lee.proxy.app", getAdvisor(trace));
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save*", "request*", "order*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

}
