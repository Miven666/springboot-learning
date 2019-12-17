package com.miven.springboot.dds.jpa.support;

import com.miven.springboot.dds.jpa.master.model.Tenant;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * 数据源工具类
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
public class DataSourceUtils {

    public static DataSource wrapperDataSource(Tenant tenant){
        return DataSourceBuilder.create()
                .driverClassName(tenant.getDriverClassName())
                .url(tenant.getUrl())
                .username(tenant.getUsername())
                .password(tenant.getPassword())
                .build();
    }
}