package com.miven.springboot.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *  注解生效监听器
 * @author mingzhi.xie
 * @date 2019/8/30
 * @since 1.0
 */
@Slf4j
@Component
public class AnnotationEffectsListener {

    /**
     *  检测l@ConditionalOnBean 注解是否生效
     * @param event 上下文刷新事件
     */
    @EventListener(ContextRefreshedEvent.class)
    public void listenerRefreshed(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        OnBeanConditionConfiguration bean = context.getBean(OnBeanConditionConfiguration.class);
        log.info(bean.toString());
    }
}
