package com.miven.spring.web;

import com.miven.entity.Fruit;
import com.miven.spring31.web.DefaultClientHttpRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *  Rest 配置类
 * @author mingzhi.xie
 * @date 2019/8/22
 * @since 1.0
 */
@Slf4j
@Configuration
@ConditionalOnClass(RestTemplate.class)
public class RestConfiguration {

    private DefaultClientHttpRequestInterceptor interceptor;

    /**
     *  给RestTemplate添加一个默认的http请求拦截器，并注册
     * @param builder 由{@link RestTemplateAutoConfiguration} 注册
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        this.interceptor = new DefaultClientHttpRequestInterceptor();
        return builder.additionalInterceptors(this.interceptor).build();
    }

    /**
     *  程序启动后执行的操作（发送一个http请求，并打印响应结果）
     * @param restTemplate 注入注册的restTemplate
     * @return 注册的CommandLineRunner
     */
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            Fruit fruit;
            try {
                fruit = restTemplate.getForObject("http://localhost:8081/app/fruit/select?name=apple", Fruit.class);
                Assert.notNull(fruit, "fruit must not be null");
                log.info("httpHeaders has contains key 'Authorization': {}", this.interceptor.containsKey("Authorization"));
                log.info(fruit.toString());
            } catch (RestClientException e) {
                log.error("Remote call failed: {}", e.getMessage());
            }
        };
    }
}
