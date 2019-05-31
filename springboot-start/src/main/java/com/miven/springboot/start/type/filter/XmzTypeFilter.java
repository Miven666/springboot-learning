package com.miven.springboot.start.type.filter;

import com.miven.springboot.start.annotation.XmzComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.util.ClassUtils;

/**
 * 自定义Xmz类型过滤
 *
 * 待实现：
 * 过滤抽象类,接口,注解,枚举,内部类及匿名类
 * @author mingzhi.xie
 * @date 2019/5/5.
 */
public class XmzTypeFilter extends AbstractClassTestingTypeFilter {

    private static final Logger logger = LoggerFactory.getLogger(XmzTypeFilter.class);

    @Override
    public boolean match(ClassMetadata metadata) {
        try {
            Class<?> clazz = ClassUtils.forName(metadata.getClassName(), getClass().getClassLoader());

            // todo 是否标识Spring组件注解
            // todo 过滤抽象类,接口,注解,枚举,内部类及匿名类

            return clazz.isAnnotationPresent(XmzComponent.class);

        } catch (ClassNotFoundException e) {
            logger.error("未扫描到组件类", e);
        }

        return false;
    }
}
