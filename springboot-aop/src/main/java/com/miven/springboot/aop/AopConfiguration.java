package com.miven.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  Aop 配置类
 * @author mingzhi.xie
 * @date 2019/9/3
 * @since 1.0
 */
@Slf4j
@Configuration
public class AopConfiguration {

    @Bean
    CommandLineRunner studentCommand(StudentComponent studentComponent) {
        return args -> log.info("Get " + studentComponent.getStudent());
    }
}
