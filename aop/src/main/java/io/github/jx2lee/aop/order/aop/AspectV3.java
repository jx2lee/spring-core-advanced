package io.github.jx2lee.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    @Pointcut("execution(* io.github.jx2lee.aop.order..*(..))")
    private void allOrder() { // pointcut signature
    }

    // class 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {
    }

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("[log] {}", joinPoint.getSignature()); // point joint signature
        return joinPoint.proceed();
    }

    //
    /**
     * order 패키지 및 하위 패키지 && class 명 패턴이 *Service
        * 트랜잭션 기능은 보통 다음과 같이 동작한다.
            * 핵심 로직 실행 직전에 트랜잭션을 시작
            * 핵심 로직 실행
            * 핵심 로직 실행에 문제가 없으면 커밋
            * 핵심 로직 실행에 예외가 발생하면 롤백
     */
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());

            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
