package com.miven.springboot.start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author mingzhi.xie
 * @date 2018/9/27
 *
 * 跨域资源共享
 */
@Configuration
public class MyConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 指定授权访问的域
        corsConfiguration.addAllowedOrigin("*");
        // 授权请求的方法
        corsConfiguration.addAllowedMethod("*");
        // 授权请求的头
        corsConfiguration.addAllowedHeader("*");
        // 授权响应的头
        corsConfiguration.addExposedHeader("");
        // 凭证
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
