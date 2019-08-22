package com.miven.spring.context;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Xmz组件扫描
 * @author mingzhi.xie
 * @date 2019/5/5.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(XmzBeanDefinitionRegistrar.class)
public @interface XmzComponentScan {

    /**
     * 未指定，默认从声明XmzComponentScan所在类的package进行扫描
     */
    String[] value() default {};
}
