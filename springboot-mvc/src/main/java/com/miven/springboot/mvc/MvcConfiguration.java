package com.miven.springboot.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS;

/**
 * MVC 配置类
 * @author mingzhi.xie
 * @since 2020/7/27 1.0
 * @see WebMvcAutoConfiguration
 */
@Configuration
public class MvcConfiguration {

    /**
     * 枚举类映射反序列化时，禁止把输入值（如："0"、"1"）当作枚举值的顺序（ordinal），必须输入枚举值的对应字符串
     * @param converters 多个http消息转化器聚合类
     */
    public MvcConfiguration(HttpMessageConverters converters) {
        List<HttpMessageConverter<?>> converterList = converters.getConverters();
        for (HttpMessageConverter<?> converter : converterList) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                objectMapper.enable(FAIL_ON_NUMBERS_FOR_ENUMS);
            }
        }
    }
}
