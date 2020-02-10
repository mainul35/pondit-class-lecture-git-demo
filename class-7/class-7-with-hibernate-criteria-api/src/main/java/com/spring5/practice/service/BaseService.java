package com.spring5.practice.service;

import com.spring5.practice.config.HibernateConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public abstract class BaseService<T> {

    private SessionFactory sessionFactory = null;

    private Session session;

    private T model;

    public BaseService(T model) {
        this.model = model;
    }

    private Session getSession() {
        try {
            session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Opening new session...");
            session = createAndGetLocalSessionFactoryBean().openSession();
        }
        return session;
    }

    private SessionFactory createAndGetLocalSessionFactoryBean() {
        if (this.sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = getBuiltProperties("hibernate.properties");

                configuration.setProperties(settings);
                configuration.addPackage("com.spring5.practice.model");
                configuration.addAnnotatedClass(model.getClass());
                StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(settings);
                sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private Properties getBuiltProperties(String propertyFileName) {
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

    protected Serializable save(T object) {
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive())
            tx = session.beginTransaction();
        var id = session.save(object);
        session.flush();
        tx.commit();
        return id;
    }

    protected void delete(T object) {
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive())
            tx = session.beginTransaction();
        session.delete(object);
        session.flush();
        tx.commit();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        return session.getCriteriaBuilder();
    }

    <T> TypedQuery<T> query(CriteriaQuery<T> query) {
        return getSession().getEntityManagerFactory().createEntityManager().createQuery(query);
    }
}
