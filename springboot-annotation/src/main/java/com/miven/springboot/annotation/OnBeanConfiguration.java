package com.miven.springboot.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 *  条件配置类
 * @author mingzhi.xie
 * @date 2019/8/29
 * @since 1.0
 */
@Configuration
@ConditionalOnBean(ConditionComponent.class)
public class OnBeanConfiguration {

}
