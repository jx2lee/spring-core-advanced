package io.github.jx2lee.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteProxy {

    public String operation() {
        log.info("ConcreteProxy 실행");
        return "data";
    }
}
