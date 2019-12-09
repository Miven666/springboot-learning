package com.miven.springboot.jta.configuration;

import lombok.Data;

import javax.sql.DataSource;

/**
 * 数据库配置
 * @author mingzhi.xie
 * @date 2019/12/9
 * @since 1.0
 */
@Data
public class DataBaseProperties {
    /**
     * JDBC驱动的完全限定名
     */
    private String driverClassName;
    /**
     * 要使用的连接池实现的全限定名
     */
    private Class<? extends DataSource> type;
    /**
     * 数据库的JDBC url
     */
    private String url;
    /**
     * 数据库的登录用户。
     */
    private String username;
    /**
     * 数据库登录密码。
     */
    private String password;
}