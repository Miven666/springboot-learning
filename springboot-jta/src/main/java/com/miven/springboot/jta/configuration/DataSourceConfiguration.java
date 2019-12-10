package com.miven.springboot.jta.configuration;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源配置
 * @author mingzhi.xie
 * @date 2019/12/9
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class})
public class DataSourceConfiguration {

    @Configuration
    @MapperScan(
            basePackages = "com.miven.springboot.jta.mapper.db1",
            sqlSessionFactoryRef = "db1SqlSessionFactory")
    protected class DataSource1Configuration {
        /**
         * 数据源1
         * @param dataSourceProperties 数据源1配置
         * @return 数据源1
         * @throws SQLException SQLException
         * @see DataSourceBuilder 也可利用 SpringBoot 2.0 版本新增的构造器类
         */
        @Primary
        @Bean(name = "db1DataSource")
        public DataSource dataSource(DataSourceProperties dataSourceProperties) throws SQLException {
            DataBaseProperties db1 = dataSourceProperties.getDb1();
            return builderDataSourceBean(db1, "db1DataSource");
        }

        @Primary
        @Bean(name = "db1SqlSessionFactory")
        public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource, DataSourceProperties dataSourceProperties) throws Exception {
            MybatisProperties mybatis1 = dataSourceProperties.getMybatis1();
            return builderSqlSessionFactory(dataSource, mybatis1.getLocationPattern());
        }
    }

    @Configuration
    @MapperScan(
            basePackages = "com.miven.springboot.jta.mapper.db2",
            sqlSessionFactoryRef = "db2SqlSessionFactory")
    protected class DataSource2Configuration {

        @Bean(name = "db2DataSource")
        public DataSource dataSource(DataSourceProperties dataSourceProperties) throws SQLException {
            DataBaseProperties db2 = dataSourceProperties.getDb2();
            return builderDataSourceBean(db2, "db2DataSource");
        }

        @Bean(name = "db2SqlSessionFactory")
        public SqlSessionFactory sqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource, DataSourceProperties dataSourceProperties) throws Exception {
            MybatisProperties mybatis2 = dataSourceProperties.getMybatis2();
            return builderSqlSessionFactory(dataSource, mybatis2.getLocationPattern());
        }
    }


    private AtomikosDataSourceBean builderDataSourceBean(DataBaseProperties db, String resourceName) throws SQLException {
        // 设置数据库连接
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(db.getUrl());
        mysqlXaDataSource.setUser(db.getUsername());
        mysqlXaDataSource.setPassword(db.getPassword());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 交给事务管理器进行管理
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXaDataSource);
        atomikosDataSourceBean.setUniqueResourceName(resourceName);
        return atomikosDataSourceBean;
    }

    private SqlSessionFactory builderSqlSessionFactory(DataSource dataSource, String locationPattern) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(locationPattern));

        // 设置 mybatis 配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }
}