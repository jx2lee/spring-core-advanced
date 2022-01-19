package io.github.jx2lee.advanced.trace.threadlocal;

import io.github.jx2lee.advanced.trace.threadlocal.code.ThreadLocalFieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalFieldServiceTest {

    private ThreadLocalFieldService fieldService = new ThreadLocalFieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("Thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("Thread-B");

        threadA.start();
        threadB.start();

        sleep(3000); // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
