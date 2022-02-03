package io.github.jx2lee.aop.example;

import io.github.jx2lee.aop.example.aop.RetryAspect;
import io.github.jx2lee.aop.example.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {

    @Autowired
    ExamService examService;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("request={}", i);
            examService.request("data" + i);
        }
    }
}
