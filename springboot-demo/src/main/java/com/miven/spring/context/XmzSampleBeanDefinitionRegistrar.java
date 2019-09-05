package com.miven.spring.context;

import com.miven.spring.beans.BeanDefinitionRegistryComment;
import com.miven.spring.core.AnnotationMetadataComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 样例类
 * ImportBeanDefinitionRegistrar：
 * 目的：对那些想注册成bean的类，添加些额外的定义规则
 * 来源：{@link Import}
 *      {@link Configuration}类中用了{@link Bean}的类
 *      实现了{@link ImportSelector}接口中导入的
 * 生命周期：当实现了XxxAware时，它的setXxx()是早于registerBeanDefinitions()
 * @author mingzhi.xie
 * @date 2019/7/19
 * @since 1.0
 */
@Slf4j
public class XmzSampleBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("Starting XmzSampleBeanDefinitionRegistry setBeanFactory() with beanFactory: {}.", beanFactory.getClass().getName());
    }

    /**
     * 注册bean定义
     * @param importingClassMetadata import注解所在类上的注解元信息
     * @param registry bean定义注册器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        log.info("Starting XmzSampleBeanDefinitionRegistry registerBeanDefinitions().");
        AnnotationMetadataComment.getAnnotationTypesMethod(importingClassMetadata);
        AnnotationMetadataComment.getMetaAnnotationTypesMethod(importingClassMetadata, "org.springframework.context.annotation.Configuration");
        BeanDefinitionRegistryComment.getBeanDefinitionCountMethod(registry);
        BeanDefinitionRegistryComment.getBeanDefinitionNamesMethod(registry);
        BeanDefinition beanDefinition = registry.getBeanDefinition("homeController");
    }
}
