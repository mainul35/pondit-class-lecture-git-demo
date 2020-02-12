package com.spring5.practice.config.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Properties;

@EnableJpaRepositories("com.spring5.practice.repositories")
@org.springframework.context.annotation.Configuration
public class HibernateConfig {
    private SessionFactory sessionFactory = null;

    private Session session;

    @Bean(value = "session")
    public Session getSession() {
        try{
            session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        } catch(HibernateException e) {
            e.printStackTrace();
            System.out.println("Opening new session...");
            session = createAndGetLocalSessionFactoryBean().openSession();
        }
        return session;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        var tx = session.getTransaction();
        if(!tx.isActive()) {
        	tx = session.beginTransaction();
        }
        return session.getCriteriaBuilder();
    }

    public EntityManager entityManager() {
        return createAndGetLocalSessionFactoryBean().createEntityManager();
    }

    @Bean
    public SessionFactory createAndGetLocalSessionFactoryBean() {
        if (this.sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = getBuiltProperties("hibernate.properties");

                configuration.setProperties(settings);
                configuration.addPackage("com.spring5.practice.model");
                for (Class<?> clazz : (new Reflections("com.spring5.practice.model")).getTypesAnnotatedWith(Entity.class)) {
                    if (!Modifier.isAbstract(clazz.getModifiers())) {
                        configuration.addAnnotatedClass(clazz);
                    }
                }
                StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(settings);
                sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    public EntityManagerFactory entityManagerFactory(Session session) {
        return session.getEntityManagerFactory();
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
