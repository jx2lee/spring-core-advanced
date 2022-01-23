package io.github.jx2lee.proxy;

import io.github.jx2lee.proxy.config.AppV1Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AppV1Config.class)
@SpringBootApplication(scanBasePackages = "io.github.jx2lee.proxy.app") //주의, config 패키지는 자동 component 스캔 대상이 되지 않도록 하기 위해 Base package 설정
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
