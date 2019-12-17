package com.miven.springboot.dds.jpa.dynamic.configuration;

import com.miven.springboot.dds.jpa.configuration.DataSourceConfiguration;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Configuration
@ConditionalOnBean(name = {"masterEntityManagerFactory"})
@AutoConfigureAfter(DataSourceConfiguration.class)
@EnableJpaRepositories(
        basePackages = "com.miven.springboot.dds.jpa.dynamic.repository",
        entityManagerFactoryRef = "tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager")
public class DynamicDataSourceConfiguration {

    @Bean(name = "tenantEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("datasourceBasedMultiTenantConnectionProvider") MultiTenantConnectionProvider connectionProvider,
            @Qualifier("currentTenantIdentifierResolver") CurrentTenantIdentifierResolver tenantIdentifierResolver) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setPackagesToScan("com.miven.springboot.dds.jpa.dynamic.model");
        entityManager.setPersistenceUnitName("dynamic-database-persistence-unit");

        Map<String,Object> properties = new HashMap<>(8);
        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantIdentifierResolver);
        properties.put(Environment.DIALECT, MySQL57Dialect.class.getName());
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);
        properties.put(Environment.HBM2DDL_AUTO, "update");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }

    @Bean(name = "tenantTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}