package io.github.jx2lee.aop.example;

import io.github.jx2lee.aop.example.annotation.Retry;
import io.github.jx2lee.aop.example.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {
    private static int seq = 0;

    @Trace
    @Retry
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }

        return "ok";
    }
}
