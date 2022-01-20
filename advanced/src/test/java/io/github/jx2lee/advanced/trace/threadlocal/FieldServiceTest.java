package io.github.jx2lee.advanced.trace.threadlocal;

import io.github.jx2lee.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

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
        // sleep(2000); // 동시성 문제 발생 X
        sleep(100); // 동시성 문제 발생 O
        threadB.start();

        sleep(3000); // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    @Test
    void fieldOccurConcurrency() {
        ArrayList<String> results = new ArrayList<>();
        Runnable userA = () -> {
            String value = fieldService.logic("userA");
            results.add(value);
        };
        Runnable userB = () -> {
            String value = fieldService.logic("userB");
            results.add(value);
        };

        Thread threadA = new Thread(userA);
        threadA.setName("Thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("Thread-B");

        threadA.start();
        // sleep(2000); // 동시성 문제 발생 X
        sleep(100); // 동시성 문제 발생 O
        threadB.start();
        sleep(2000);

        Assertions.assertEquals(results.get(0), results.get(1));
    }

    @Test
    void fieldNotOccurConcurrency() {
        ArrayList<String> results = new ArrayList<>();
        Runnable userA = () -> {
            String value = fieldService.logic("userA");
            results.add(value);
        };
        Runnable userB = () -> {
            String value = fieldService.logic("userB");
            results.add(value);
        };

        Thread threadA = new Thread(userA);
        threadA.setName("Thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("Thread-B");

        threadA.start();
        sleep(2000); // 동시성 문제 발생 X
        // sleep(100); // 동시성 문제 발생 O
        threadB.start();
        sleep(2000);

        Assertions.assertNotEquals(results.get(0), results.get(1));
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
