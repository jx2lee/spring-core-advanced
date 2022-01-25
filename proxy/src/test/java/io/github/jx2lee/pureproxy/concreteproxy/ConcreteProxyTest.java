package io.github.jx2lee.pureproxy.concreteproxy;

import io.github.jx2lee.pureproxy.concreteproxy.code.ConcreteProxy;
import io.github.jx2lee.pureproxy.concreteproxy.code.ConcreteProxyClient;
import io.github.jx2lee.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteProxy concreteProxy = new ConcreteProxy();
        ConcreteProxyClient client = new ConcreteProxyClient(concreteProxy);
        client.execute();
    }

    @Test
    void addProxy() {
        ConcreteProxy concreteProxy = new ConcreteProxy();
        TimeProxy timeProxy = new TimeProxy(concreteProxy);
        ConcreteProxyClient client = new ConcreteProxyClient(timeProxy);
        client.execute();
    }
}
