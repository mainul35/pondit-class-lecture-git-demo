package com.spring5.practice.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Properties;

public class HibernateConfig {

    private SessionFactory sessionFactory = null;

    private Session session;

    public Session getSession() {
        this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        return session != null ? this.session : createAndGetLocalSessionFactoryBean().openSession();
    }

    public SessionFactory createAndGetLocalSessionFactoryBean() {
        if (this.sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
             /*  //----------------- configure with hibernate.cfg.xml-----------------
                sessionFactory = new Configuration().configure().buildSessionFactory();
                // or do the above line in step wise like below
                Configuration cfg = new Configuration();
                cfg.configure("hibernate.cfg.xml");
                sessionFactory = cfg.buildSessionFactory();
                System.out.println(sessionFactory);
                System.out.println(sessionFactory.isClosed());
                //----------------- end configure with hibernate.cfg.xml-----------------  */

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

    public CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        return session.getCriteriaBuilder();
    }

    public <T> TypedQuery<T> query(CriteriaQuery<T> query) {
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        var result = session.getEntityManagerFactory().createEntityManager().createQuery(query);
        tx.commit();
        return result;
    }
}
