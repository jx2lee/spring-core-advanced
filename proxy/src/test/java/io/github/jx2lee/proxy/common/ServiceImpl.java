package io.github.jx2lee.proxy.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceImpl implements ServiceInterface {

    @Override
    public void call() {
        log.info("call 실행");

    }

    @Override
    public void find() {
        log.info("find 실행");
    }
}
