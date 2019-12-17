package com.miven.springboot.dds.jpa.configuration;

import com.miven.springboot.dds.jpa.dynamic.support.DynamicConnectionProvider;
import com.miven.springboot.dds.jpa.master.properties.DataSourceProperties;
import com.miven.springboot.dds.jpa.support.SimpleCurrentTenantIdentifierResolver;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源配置
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@EnableJpaRepositories(
        basePackages = "com.miven.springboot.dds.jpa.master.repository",
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager")
public class DataSourceConfiguration {

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDatasource(DataSourceProperties dataSourceProperties){
        log.info("Setting up masterDatasource with : {}",dataSourceProperties);
        HikariDataSource datasource = new HikariDataSource();
        datasource.setUsername(dataSourceProperties.getUsername());
        datasource.setPassword(dataSourceProperties.getPassword());
        datasource.setJdbcUrl(dataSourceProperties.getUrl());
        datasource.setDriverClassName(dataSourceProperties.getDriverClassName());
        datasource.setPoolName(dataSourceProperties.getPoolName());
        datasource.setMaximumPoolSize(dataSourceProperties.getMaxPoolSize());
        datasource.setMinimumIdle(dataSourceProperties.getMinIdle());
        datasource.setConnectionTimeout(dataSourceProperties.getConnectionTimeout());
        datasource.setIdleTimeout(dataSourceProperties.getIdleTimeout());
        log.info("Setup of masterDatasource successfully.");
        return datasource;
    }

    @Primary
    @Bean(name = "masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(@Qualifier("masterDataSource") DataSource dataSource){
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("com.miven.springboot.dds.jpa.master.model");
        // Setting a name for the persistence unit as Spring sets it as 'default' if not defined.
        entityManager.setPersistenceUnitName("master-database-persistence-unit");
        // Setting Hibernate as the JPA provider.
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);
        // Setting the hibernate properties
        entityManager.setJpaProperties(hibernateProperties());
        log.info("Setup of masterEntityManagerFactory successfully.");
        return entityManager;
    }

    @Primary
    @Bean(name = "masterTransactionManager")
    public JpaTransactionManager masterTransactionManager(@Qualifier("masterEntityManagerFactory") EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        log.info("Setup of masterTransactionManager successfully.");
        return transactionManager;
    }


    @Bean(name = "datasourceBasedMultiTenantConnectionProvider")
    public MultiTenantConnectionProvider multiTenantConnectionProvider(){
        return new DynamicConnectionProvider();
    }

    @Bean(name = "currentTenantIdentifierResolver")
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver(){
        return new SimpleCurrentTenantIdentifierResolver();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, MySQL57Dialect.class.getName());
        properties.put(Environment.SHOW_SQL,true);
        properties.put(Environment.FORMAT_SQL,true);
        properties.put(Environment.HBM2DDL_AUTO,"update");
        return properties;
    }
}