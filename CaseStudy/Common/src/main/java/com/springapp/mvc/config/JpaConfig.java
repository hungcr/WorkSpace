package com.springapp.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by LANGKHACH on 20/05/2014.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.springapp.mvc.repository")
@PropertySource(value = "classpath:persistence.properties")
public class JpaConfig {

    @Resource
    Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("dataSource.driverClassName"));
        dataSource.setUrl(env.getProperty("dataSource.url"));
        dataSource.setUsername(env.getProperty("dataSource.username"));
        dataSource.setPassword(env.getProperty("dataSource.password"));

        return dataSource;

    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        /*
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.springapp.mvc.entity");
        factory.setPersistenceXmlLocation("classpath:/persistence.xml");
        factory.setPersistenceUnitName("PersistenceUnit");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
        */


        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.springapp.mvc.entity");
        factory.setPersistenceXmlLocation("classpath:/persistence.xml");
        factory.setPersistenceUnitName("PersistenceUnit");
        factory.setDataSource(dataSource());
        Properties properties = new Properties();
        properties.setProperty("eclipselink.weaving","false");
        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();
        return factory.getObject();

        /*
        OpenJpaVendorAdapter vendorAdapter = new OpenJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.springapp.mvc.entity");
        factory.setPersistenceXmlLocation("classpath:/persistence.xml");
        factory.setPersistenceUnitName("PersistenceUnit");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
        */

    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
