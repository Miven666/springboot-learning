package com.miven.spring.context.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

/**
 *  自定义 ImportAware
 *  通过 {@link Import} 我们可以将一个 配置类 导入进某个类
 * @author mingzhi.xie
 * @date 2019/9/4
 * @since 1.0
 */
@Slf4j
@Configuration
public class CustomImportAware implements ImportAware {

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        log.info("CustomImportAware setImportMetadata() is running");
    }
}
