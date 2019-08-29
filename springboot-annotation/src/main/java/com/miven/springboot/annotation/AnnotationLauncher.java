package com.miven.springboot.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 *  启动器
 * @author mingzhi.xie
 * @date 2019/8/29
 * @since 1.0
 */
@Slf4j
@SpringBootApplication
public class AnnotationLauncher {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationLauncher.class, args);
    }


    /**
     *  检测l@ConditionalOnBean 注解是否生效
     * @param event 上下文刷新事件
     */
    @EventListener(ContextRefreshedEvent.class)
    public void listenerRefreshed(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        OnBeanConfiguration bean = context.getBean(OnBeanConfiguration.class);
        log.info(bean.toString());
    }
}
