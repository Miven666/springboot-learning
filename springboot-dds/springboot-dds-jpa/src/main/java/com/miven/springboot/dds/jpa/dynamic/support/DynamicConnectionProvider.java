package com.miven.springboot.dds.jpa.dynamic.support;

import com.miven.springboot.dds.jpa.master.model.Tenant;
import com.miven.springboot.dds.jpa.master.repository.TenantRepository;
import com.miven.springboot.dds.jpa.support.DataSourceUtils;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基于多租户数据源连接提供程序
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
public class DynamicConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final long serialVersionUID = 2811257234170993371L;

    @Resource
    private TenantRepository tenantRepository;

    private Map<String, DataSource> dataSources = new TreeMap<>();

    @Override
    protected DataSource selectAnyDataSource() {
        if(dataSources.isEmpty()){
            List<Tenant> tenants = tenantRepository.findAll();
            tenants.forEach(masterTenant-> dataSources.put(masterTenant.getIdentifier(), DataSourceUtils.wrapperDataSource(masterTenant)));
        }
        return dataSources.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String identifier) {
        if(!dataSources.containsKey(identifier)){
            List<Tenant> tenants = tenantRepository.findAll();
            tenants.forEach(tenant-> dataSources.put(tenant.getIdentifier(), DataSourceUtils.wrapperDataSource(tenant)));
        }
        return dataSources.get(identifier);
    }
}