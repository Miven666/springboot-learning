package com.miven.spring.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;

import java.util.ArrayList;
import java.util.Set;

/**
 * 自2.5版本引入，继承自{@link ClassMetadata} , {@link AnnotatedTypeMetadata}
 * {@link AnnotationMetadata} 是对某个类上所有注解信息的抽象，即注解类的注解元信息
 *
 * 具有如下方法：
 * 第一类：这个类上的注解
 *  获取这个类上所有注解的类型：
 *      Set<String> getAnnotationTypesMethod()
 *  判断是否存在某个指定注解：
 *      boolean hasAnnotation(String annotationName)
 *  判断这个类是否存在被某个指定注解标注的方法：
 *      boolean hasAnnotatedMethods(String annotationName)
 *  获取这个类上，被某个指定注解标注的，所有方法集合
 *      Set<MethodMetadata> getAnnotatedMethods(String annotationName)
 * 第二类：这个类上注解的注解
 *  获取这个类上所有注解中，某个指定注解类型，它的注解元信息中所有注解类型：
 *      Set<String> getMetaAnnotationTypes(String annotationName)
 *  判断所有注解的注解中是否存在某个指定注解：
 *      boolean hasMetaAnnotation(String metaAnnotationName)
 *
 * @author mingzhi.xie
 * @date 2019/7/19
 * @since 1.0
 */
public class AnnotationMetadataComment {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationMetadataComment.class);

    public static void getAnnotationTypesMethod(AnnotationMetadata am) {
        Set<String> annotationTypes = am.getAnnotationTypes();
        logger.info("AnnotationMetadata of getAnnotationTypes() 是获取 import 注解所在类(配置类、派生注解所在配置类)上的所有注解的类型，即本示例中 {}：{}",am.getClassName(), new ArrayList<>(annotationTypes));
    }

    public static void getMetaAnnotationTypesMethod(AnnotationMetadata am, String annotationName) {
        Set<String> configuration = am.getMetaAnnotationTypes(annotationName);
        logger.info("AnnotationMetadata of getMetaAnnotationTypes(String annotationName) 是获取指定注解的注解的所有类型，即本示例中指定注解 {}：{}", annotationName, new ArrayList<>(configuration));
    }
}
