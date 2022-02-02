package io.github.jx2lee.aop.member;

import io.github.jx2lee.aop.member.annotation.ClassAop;
import io.github.jx2lee.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
