package com.miven.springboot.jta.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author mingzhi.xie
 * @date 2019/12/9
 * @since 1.0
 */
@Data
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties {
    /**
     * 数据库1配置
     */
    @NestedConfigurationProperty
    private DataBaseProperties db1;
    /**
     * 数据库2配置
     */
    @NestedConfigurationProperty
    private DataBaseProperties db2;
    /**
     * Mybatis1 配置
     */
    @NestedConfigurationProperty
    private MybatisProperties mybatis1;
    /**
     * Mybatis2 配置
     */
    @NestedConfigurationProperty
    private MybatisProperties mybatis2;
}