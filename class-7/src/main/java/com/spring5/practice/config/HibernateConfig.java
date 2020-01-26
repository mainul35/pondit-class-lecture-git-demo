package com.spring5.practice.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Properties;

public class HibernateConfig {
    private SessionFactory sessionFactory = null;

    private Session session;

    public Session getSession() {
        this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        return session != null
                ? this.session
                : createAndGetLocalSessionFactoryBean().openSession();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        session.beginTransaction();
        return session.getCriteriaBuilder();
    }

    public EntityManager entityManager() {
        return createAndGetLocalSessionFactoryBean().createEntityManager();
    }

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
}
