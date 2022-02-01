package io.github.jx2lee.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    /**
     * pointcut 를 따로 외부에 보관하여 여러 advisor 에 적용한다.
     * 기존 접근제어자 private -> public 으로 변경
     * full package name 을 명시
     */

    @Pointcut("execution(* io.github.jx2lee.aop.order..*(..))")
    public void allOrder() {
    }

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {
    }
}
