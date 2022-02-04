package io.github.jx2lee.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 구조 변경
 * 어떻게? 내부 함수호출을 다른 클래스로 생성
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class CallServiceV3 {

    private final InternalService internalService;

    public void external() {
        log.info("call external");
        internalService.internal();
    }
}
