package io.github.jx2lee.pureproxy.concreteproxy.code;

public class ConcreteProxyClient {
    private ConcreteProxy concreteProxy;

    public ConcreteProxyClient(ConcreteProxy concreteProxy) {
        this.concreteProxy = concreteProxy;
    }

    public void execute() {
        concreteProxy.operation();
    }
}
