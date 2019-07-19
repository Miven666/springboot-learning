package com.miven.springboot.start.bean;

import com.miven.springboot.start.annotation.XmzComponent;
import com.miven.springboot.start.annotation.XmzComponentScan;
import com.miven.springboot.start.type.filter.XmzTypeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Xmz组件注册器(一个自定义bean定义注册器)
 * ImportBeanDefinitionRegistrar：
 * 目的：对那些想注册成bean的类，添加些额外的定义规则
 * 来源：{@link Import}
 *      {@link Configuration}类中用了{@link Bean}的类
 *      实现了{@link ImportSelector}接口中导入的
 * 生命周期：当实现了XxxAware时，它的setXxx()是早于registerBeanDefinitions()
 * @author mingzhi.xie
 * @date 2019/5/5.
 */
public class XmzBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(XmzBeanDefinitionRegistrar.class);

    private static final String RESOURCE_PATTERN = "/**/*.class";

    private static final Map<Integer, Class<?>> XMZ_COMPONENT_MAPPING = new HashMap<>();


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("XxxAware 的 setXxx() 被执行了");
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        logger.info("registerBeanDefinitions() 被执行了");
        Map<String, Object> metadataAnnotationAttributes = importingClassMetadata.getAnnotationAttributes(XmzComponentScan.class.getName());

        // 获取注册器所有属性
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadataAnnotationAttributes);

        // 获取扫描包
        String[] basePackages = new String[0];
        if (annotationAttributes != null) {
            basePackages = annotationAttributes.getStringArray("value");
        }

        if(ObjectUtils.isEmpty(basePackages)) {
            basePackages = new String[] {ClassUtils.getPackageName(importingClassMetadata.getClassName())};
        }

        // 从扫描包获取候选人
        HashMap<Integer, ? extends Class<?>> candidates = scanPackages(basePackages);

        // 注册候选人们
        registerBeanDefinitions(candidates);
    }

    public static Map<Integer, Class<?>> getXmzComponentMapping() {
        return XMZ_COMPONENT_MAPPING;
    }

    private void registerBeanDefinitions(HashMap<Integer, ? extends Class<?>> candidates) {
        for (Integer key : candidates.keySet()) {
            if (XMZ_COMPONENT_MAPPING.values().contains(candidates.get(key))) {
                logger.error("重复扫描{}类,忽略重复注册", candidates.get(key));
                continue;
            }
            XMZ_COMPONENT_MAPPING.put(key, candidates.get(key));
        }
    }

    /**
     * 根据所有包名扫描出所有候选者们
     * @param basePackages  所有包名
     * @return              所有候选者们
     */
    private HashMap<Integer, ? extends Class<?>> scanPackages(String[] basePackages) {
        HashMap<Integer, Class<?>> candidates = new HashMap<>(16);
        for (String basePackage : basePackages) {
            candidates.putAll(findCandidateClasses(basePackage));
        }

        return candidates;
    }

    /**
     * 根据包名获取候选者们
     * @param basePackage   包名
     * @return              候选者们
     */
    private HashMap<Integer, ? extends Class<?>> findCandidateClasses(String basePackage) {
        HashMap<Integer, Class<?>> candidates = new HashMap<>(16);

        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + StringUtils.replace(basePackage, ".", "/") + RESOURCE_PATTERN;
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource[] resources = new Resource[0];
        try {
            resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(packageSearchPath);
        } catch (IOException e) {
            logger.error("扫描XmzComponent基础类资源异常", e);
        }

        MetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory(resourceLoader);
        try {
            for (Resource resource : resources) {
                MetadataReader metadataReader = readerFactory.getMetadataReader(resource);

                // AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata()
                ClassMetadata classMetadata = metadataReader.getClassMetadata();

                if (isCandidate(classMetadata)) {
                    Class<?> candidateClass = transform(classMetadata.getClassName());
                    XmzComponent xmzComponent = candidateClass.getAnnotation(XmzComponent.class);
                    int key = xmzComponent.value();

                    if (candidates.containsKey(key)) {
                        logger.error("XmzComponent组件type值已存在:{}", candidateClass);
                        continue;
                    }

                    candidates.put(key, candidateClass);
                    logger.info("扫描到符合要求XmzComponent基础类:{}", candidateClass);
                }
            }
        } catch (IOException e) {
            logger.error("获取元数据读取器失败", e);
        } catch (ClassNotFoundException e) {
            logger.error("未扫描到组件类", e);
        }

        return candidates;
    }

    /**
     * 是否为候选者
     * @param classMetadata 元数据
     * @return              true:是, false:不是
     */
    private boolean isCandidate(ClassMetadata classMetadata) {
        XmzTypeFilter typeFilter = new XmzTypeFilter();
        return typeFilter.match(classMetadata);
    }

    private Class<?> transform(String className) throws ClassNotFoundException {
        return ClassUtils.forName(className, getClass().getClassLoader());
    }
}
