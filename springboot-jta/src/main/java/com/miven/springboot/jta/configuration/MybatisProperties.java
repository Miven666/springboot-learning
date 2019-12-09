package com.miven.springboot.jta.configuration;

import lombok.Data;

/**
 * Mybatis 配置
 * @author mingzhi.xie
 * @date 2019/12/9
 * @since 1.0
 */
@Data
public class MybatisProperties {
    /**
     * Mapper 解析的位置
     */
    private String locationPattern;
}