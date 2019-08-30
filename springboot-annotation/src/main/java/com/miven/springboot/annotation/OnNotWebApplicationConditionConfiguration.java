package com.miven.springboot.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Configuration;

/**
 *  非Web应用条件配置类
 * @author mingzhi.xie
 * @date 2019/8/30
 * @since 1.0
 */
@Configuration
@ConditionalOnNotWebApplication
public class OnNotWebApplicationConditionConfiguration {
}
