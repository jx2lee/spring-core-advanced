package io.github.jx2lee.pureproxy.proxy;

import io.github.jx2lee.pureproxy.proxy.code.ProxyPatternClient;
import io.github.jx2lee.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }
}
