package com.miven.springboot.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
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
     *  检测l注解是否生效
     *      ConditionalOnBean
     *      ConditionalOnClass
     *      ConditionalOnProperty
     *      ConditionalOnNotWebApplication
     * @param event 上下文刷新事件
     */
    @EventListener(ContextRefreshedEvent.class)
    public void listenerRefreshed(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();

        try {
            OnBeanConditionConfiguration onBean = context.getBean(OnBeanConditionConfiguration.class);
            log.info(onBean.toString());
        } catch (BeansException e) {
            log.error(e.getMessage());
        }

        try {
            OnClassConditionConfiguration onClass = context.getBean(OnClassConditionConfiguration.class);
            log.info(onClass.toString());
        } catch (BeansException e) {
            log.error(e.getMessage());
        }

        try {
            OnNotWebApplicationConditionConfiguration onNotWeb = context.getBean(OnNotWebApplicationConditionConfiguration.class);
            log.info(onNotWeb.toString());
        } catch (BeansException e) {
            log.error(e.getMessage());
        }

        try {
            OnPropertyConditionConfiguration onProperty = context.getBean(OnPropertyConditionConfiguration.class);
            log.info(onProperty.toString());
        } catch (BeansException e) {
            log.error(e.getMessage());
        }
    }
}
