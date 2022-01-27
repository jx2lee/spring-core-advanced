package io.github.jx2lee.proxy.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteService {
    public void call() {
        log.info("ConcreteService 호출");
    }
    public void find() { log.info("ConcreteService 호출"); }
}
