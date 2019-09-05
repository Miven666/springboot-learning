package com.miven.spring.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 *  自定义 BeanFactoryAware
 *  见名知意 BeanFactoryAware 为一个"知道 BeanFactory 存在的"接口
 *  区别于常规获取 bean 手段（依赖注入、设置个引用让调用方传递进来）
 *  可以自己在容器里（自己拥有的）查找和筛选出自己想要的 bean
 * @author mingzhi.xie
 * @date 2019/9/4
 * @since 1.0
 */
@Slf4j
public class CustomBeanFactoryAware implements BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("Starting CustomBeanFactoryAware implements BeanFactoryAware setBeanFactory().");
    }
}
