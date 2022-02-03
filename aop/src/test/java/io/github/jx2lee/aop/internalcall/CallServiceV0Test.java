package io.github.jx2lee.aop.internalcall;

import io.github.jx2lee.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    void external() {
        callServiceV0.external();
        /*
         2022-02-03 23:43:24.271  INFO 20208 --- [    Test worker] i.g.j.a.internalcall.aop.CallLogAspect   : aop=void io.github.jx2lee.aop.internalcall.CallServiceV0.external()
         2022-02-03 23:43:24.283  INFO 20208 --- [    Test worker] i.g.j.aop.internalcall.CallServiceV0     : call external
         2022-02-03 23:43:24.283  INFO 20208 --- [    Test worker] i.g.j.aop.internalcall.CallServiceV0     : call internal
         */
    }

    @Test
    void internal() {
        callServiceV0.internal();
        /*
         2022-02-03 23:43:24.293  INFO 20208 --- [    Test worker] i.g.j.a.internalcall.aop.CallLogAspect   : aop=void io.github.jx2lee.aop.internalcall.CallServiceV0.internal()
         2022-02-03 23:43:24.294  INFO 20208 --- [    Test worker] i.g.j.aop.internalcall.CallServiceV0     : call internal
         */
    }
}
