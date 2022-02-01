package io.github.jx2lee.proxy;

import io.github.jx2lee.proxy.config.AppV1Config;
import io.github.jx2lee.proxy.config.AppV2Config;
import io.github.jx2lee.proxy.config.v1_proxy.ConcreteProxyConfig;
import io.github.jx2lee.proxy.config.v1_proxy.InterfaceProxyConfig;
import io.github.jx2lee.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import io.github.jx2lee.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import io.github.jx2lee.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1;
import io.github.jx2lee.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import io.github.jx2lee.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import io.github.jx2lee.proxy.config.v5_autoproxy.AutoProxyConfig;
import io.github.jx2lee.proxy.config.v6_aop.AopConfig;
import io.github.jx2lee.proxy.trace.logtrace.LogTrace;
import io.github.jx2lee.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

// @Import({AppV1Config.class, AppV2Config.class})
// @Import(InterfaceProxyConfig.class)
// @Import(ConcreteProxyConfig.class)
// @Import(DynamicProxyBasicConfig.class)
// @Import(DynamicProxyFilterConfig.class)
// @Import(ProxyFactoryConfigV1.class)
// @Import(ProxyFactoryConfigV2.class)
// @Import(BeanPostProcessorConfig.class)
// @Import(AutoProxyConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "io.github.jx2lee.proxy.app") //주의, config 패키지는 자동 component 스캔 대상이 되지 않도록 하기 위해 Base package 설정
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace trace() {
		return new ThreadLocalLogTrace();
	}

}
