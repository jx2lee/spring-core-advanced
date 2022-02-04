package io.github.jx2lee.aop.proxyvs;

import io.github.jx2lee.aop.member.MemberService;
import io.github.jx2lee.aop.member.MemberServiceImpl;
import io.github.jx2lee.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Member;

@Slf4j
@Import({ProxyDIAspect.class})
// @SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK dynamic proxy
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB proxy
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass()); // 실패
    }

}
