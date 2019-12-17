package com.miven.springboot.dds.jpa.configuration;

import com.miven.springboot.dds.jpa.interceptor.TenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login.html");
        super.addInterceptors(registry);
    }
}