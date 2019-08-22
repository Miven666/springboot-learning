package com.miven.spring.context;

import java.lang.annotation.*;

/**
 * Xmz组件
 * @author mingzhi.xie
 * @date 2019/5/5.
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XmzComponent {

    int value();

    int type() default 0;
}
