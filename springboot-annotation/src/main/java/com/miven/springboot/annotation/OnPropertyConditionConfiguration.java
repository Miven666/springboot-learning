package com.miven.springboot.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 *  server.port属性是否指定为8080(默认得8080不行)配置类
 * @author mingzhi.xie
 * @date 2019/8/30
 * @since 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "server", name = "port", havingValue = "8080", matchIfMissing = true)
public class OnPropertyConditionConfiguration {
}
