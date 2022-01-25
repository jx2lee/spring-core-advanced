package io.github.jx2lee.pureproxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello hello = new Hello();

        // 공통 로직1 시작
        log.info("start");
        String result1 = hello.callA();
        log.info("result={}", result1);
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        String result2 = hello.callB();
        log.info("result={}", result2);
        // 공통 로직1 종료
    }

    @Test
    void reflection1() throws Exception {
        Hello target = new Hello();
        Class classHello = Class.forName("io.github.jx2lee.pureproxy.jdkdynamic.ReflectionTest$Hello");

        log.info("start");
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result={}", result1);

        log.info("start");
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        Hello target = new Hello();
        Class classHello = Class.forName("io.github.jx2lee.pureproxy.jdkdynamic.ReflectionTest$Hello");

        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception{
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
