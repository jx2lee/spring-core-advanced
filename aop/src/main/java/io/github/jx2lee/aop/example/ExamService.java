package io.github.jx2lee.aop.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
