package io.github.jx2lee.proxy.config.v5_autoproxy;

import io.github.jx2lee.proxy.config.AppV1Config;
import io.github.jx2lee.proxy.config.AppV2Config;
import io.github.jx2lee.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

    // @Bean
    public Advisor advisor1(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save*", "request*", "order*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    // @Bean
    public Advisor advisor2(LogTrace trace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* io.github.jx2lee.proxy.app..*(..))");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    public Advisor advisor3(LogTrace trace) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* io.github.jx2lee.proxy.app..*(..)) && !execution(* io.github.jx2lee.proxy.app..noLog(..))");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
