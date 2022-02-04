package io.github.jx2lee.aop.proxyvs;

import io.github.jx2lee.aop.member.MemberService;
import io.github.jx2lee.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false);

        // proxy -> interface 타입 casting 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();
        log.info("proxy class={}", memberServiceProxy.getClass());

        // proxy -> 구현 클래스로 casting 시 실패
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberServiceImpl = (MemberServiceImpl) factory.getProxy();
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true);

        // proxy -> interface 타입 casting 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();
        log.info("proxy class={}", memberServiceProxy.getClass());

        // proxy -> 구현 클래스로 casting 시 성공
        MemberServiceImpl castingMemberServiceImpl = (MemberServiceImpl) factory.getProxy();
        log.info("proxy class={}", castingMemberServiceImpl.getClass());

    }
}
