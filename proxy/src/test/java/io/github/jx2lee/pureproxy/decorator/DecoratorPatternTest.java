package io.github.jx2lee.pureproxy.decorator;

import io.github.jx2lee.pureproxy.decorator.code.Component;
import io.github.jx2lee.pureproxy.decorator.code.DecoratorPatternClient;
import io.github.jx2lee.pureproxy.decorator.code.RealComponent;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void noDecoratorTest() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }
}
