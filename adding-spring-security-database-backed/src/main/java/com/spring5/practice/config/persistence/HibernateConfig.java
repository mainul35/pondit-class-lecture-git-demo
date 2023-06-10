package com.spring5.practice.config.persistence;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@EnableTransactionManagement
@EnableJpaRepositories("com.spring5.practice.repositories")
@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    public static final String ENTITY_PACKAGES_TO_SCAN = "com.spring5.practice.model";

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(ENTITY_PACKAGES_TO_SCAN);
        Properties hibernateProperties = getBuiltProperties("hibernate.properties");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    protected Properties getBuiltProperties(String propertyFileName) {
        Properties properties = new Properties();
        InputStream input = HibernateConfig.class
                .getClassLoader().getResourceAsStream(propertyFileName);
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    public DriverManagerDataSource dataSource(){
        var properties = getBuiltProperties("hibernate.properties");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getProperty("hibernate.connection.driver_class"));
        dataSource.setUrl(properties.getProperty("jakarta.persistence.jdbc.url"));
        dataSource.setUsername(properties.getProperty("hibernate.connection.username"));
        dataSource.setPassword(properties.getProperty("hibernate.connection.password"));
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(ENTITY_PACKAGES_TO_SCAN);
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }
}
