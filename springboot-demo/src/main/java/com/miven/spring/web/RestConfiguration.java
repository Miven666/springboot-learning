package com.miven.spring.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
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

}
