package io.github.jx2lee.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    /**
     * template callback pattern: 익명 내부 클래스
     * strategy.ContextV2 와 같은 전략패턴을 스프링 내에서 많이 사용하기 때문에 스프링 내에서는 template callback pattern 이라고 부른다.
     * 즉, 전략패턴에서 파라미터로 전달받는 방식 == (spring) template callback pattern
     * Context == Template, Strategy == Callback
     */

    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();
        callback.call(); // 위임
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);

    }

}
