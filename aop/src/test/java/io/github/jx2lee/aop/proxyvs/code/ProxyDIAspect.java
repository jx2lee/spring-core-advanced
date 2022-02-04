package io.github.jx2lee.aop.proxyvs.code;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class ProxyDIAspect {

    @Before("execution(* io.github.jx2lee.aop..*.*(..))")
    public void doLog(JoinPoint joinPoint) {
        log.info("[proxyDIAdvice] {}", joinPoint.getSignature());
    }
}
