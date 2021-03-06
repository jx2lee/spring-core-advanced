package io.github.jx2lee.advanced.trace.template;

import io.github.jx2lee.advanced.trace.template.code.AbstractTemplate;
import io.github.jx2lee.advanced.trace.template.code.SubClassLogic1;
import io.github.jx2lee.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
    /*
    * template method pattern 적용 V1
    */
    @Test
    void templateMethodV1() {
        AbstractTemplate subClassLogic1 = new SubClassLogic1();
        subClassLogic1.excute();
        AbstractTemplate subClassLogic2 = new SubClassLogic2();
        subClassLogic2.excute();
    }
    
    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        System.out.println("template1.getClass() = " + template1.getClass());
        template1.excute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        System.out.println("template2.getClass() = " + template2.getClass());
        template2.excute();
    }
}
