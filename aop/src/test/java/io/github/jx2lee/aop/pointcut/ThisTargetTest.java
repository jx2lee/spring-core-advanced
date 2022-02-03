package io.github.jx2lee.aop.pointcut;

import io.github.jx2lee.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
// @SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK dynamic proxy
@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLIB proxy
public class ThisTargetTest {

    /**
     * JDK Dynamic Proxy 이용 시 결과
     2022-02-03 17:00:17.140  INFO 13161 --- [    Test worker] i.g.jx2lee.aop.pointcut.ThisTargetTest   : memberService Proxy=class com.sun.proxy.$Proxy62
     2022-02-03 17:00:17.148  INFO 13161 --- [    Test worker] .g.j.a.p.ThisTargetTest$ThisTargetAspect : [this-interface] String io.github.jx2lee.aop.member.MemberService.hello(String)
     2022-02-03 17:00:17.148  INFO 13161 --- [    Test worker] .g.j.a.p.ThisTargetTest$ThisTargetAspect : [target-interface] String io.github.jx2lee.aop.member.MemberService.hello(String)
     2022-02-03 17:00:17.147  INFO 13161 --- [    Test worker] .g.j.a.p.ThisTargetTest$ThisTargetAspect : [target-impl] String io.github.jx2lee.aop.member.MemberService.hello(String)
     * CGLIB Proxy 이용 시 결과
     2022-02-03 17:01:04.036  INFO 13186 --- [    Test worker] i.g.jx2lee.aop.pointcut.ThisTargetTest   : memberService Proxy=class io.github.jx2lee.aop.member.MemberServiceImpl$$EnhancerBySpringCGLIB$$9201bc2f
     2022-02-03 17:01:04.042  INFO 13186 --- [    Test worker] .g.j.a.p.ThisTargetTest$ThisTargetAspect : [this-interface] String io.github.jx2lee.aop.member.MemberServiceImpl.hello(String)
     2022-02-03 17:01:04.041  INFO 13186 --- [    Test worker] .g.j.a.p.ThisTargetTest$ThisTargetAspect : [target-interface] String io.github.jx2lee.aop.member.MemberServiceImpl.hello(String)
     2022-02-03 17:01:04.042  INFO 13186 --- [    Test worker] .g.j.a.p.ThisTargetTest$ThisTargetAspect : [this-impl] String io.github.jx2lee.aop.member.MemberServiceImpl.hello(String)
     2022-02-03 17:01:04.041  INFO 13186 --- [    Test worker] .g.j.a.p.ThisTargetTest$ThisTargetAspect : [target-impl] String io.github.jx2lee.aop.member.MemberServiceImpl.hello(String)
     **/

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {
        @Around("this(io.github.jx2lee.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(io.github.jx2lee.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("this(io.github.jx2lee.aop.member.MemberServiceImpl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(io.github.jx2lee.aop.member.MemberServiceImpl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
