package com.miven.springboot.dds.jpa.master.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据源配置属性
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Data
@ConfigurationProperties("dds.master.datasource")
public class DataSourceProperties {

    private String url;

    private String password;

    private String username;

    private String driverClassName;

    private long connectionTimeout;

    private int maxPoolSize;

    private long idleTimeout;

    private int minIdle;

    private String poolName;
}