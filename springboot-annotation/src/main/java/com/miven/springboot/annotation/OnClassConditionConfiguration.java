package com.miven.springboot.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 *  Class存在条件配置类
 * @author mingzhi.xie
 * @date 2019/8/30
 * @since 1.0
 */
@Configuration
@ConditionalOnClass(ConditionComponent.class)
public class OnClassConditionConfiguration {
}
