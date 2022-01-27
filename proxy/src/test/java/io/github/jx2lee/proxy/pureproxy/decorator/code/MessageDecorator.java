package io.github.jx2lee.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String execute() {
        log.info("MessageDecorator 실행");
        String result = component.execute();
        String decoResult = "***" + result + "***";
        log.info("변경 전={}, 변경 후={}", result, decoResult);
        return decoResult;
    }
}
