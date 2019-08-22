package com.miven.spring.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.AliasRegistry;

/**
 * 自1.0引入，继承自{@link AliasRegistry}
 * {@link BeanDefinitionRegistry} bean定义注册器
 *
 * 具有如下方法：
 *  注册bean定义：
 *      registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
 *  删除bean定义：
 *      removeBeanDefinition(String beanName)
 *  根据beanName，获取bean定义：
 *      BeanDefinition getBeanDefinition(String beanName)
 *  获取所有bean的name数组：
 *      String[] getBeanDefinitionNames()
 *  获取被定义的bean的个数：
 *      int getBeanDefinitionCount()
 *  根据beanName，判断是否存在对应的bean定义：
 *      boolean containsBeanDefinition(String beanName)
 *  根据beanName，判断是否已经被使用：
 *      boolean isBeanNameInUse(String beanName)
 * @author mingzhi.xie
 * @date 2019/7/19
 * @since 1.0
 */
public class BeanDefinitionRegistryComment {

    private static final Logger logger = LoggerFactory.getLogger(BeanDefinitionRegistryComment.class);

    public static void getBeanDefinitionNamesMethod(BeanDefinitionRegistry registry) {
        String[] names = registry.getBeanDefinitionNames();
        logger.info("所有bean的name:");
        for (String name : names) {
            logger.info(name);
        }
//        logger.info("所有bean的name:\n" + Arrays.toString(names));
    }

    public static void getBeanDefinitionCountMethod(BeanDefinitionRegistry registry) {
        int count = registry.getBeanDefinitionCount();
        logger.info("所有被定义的bean的个数: " + count);
    }
}
