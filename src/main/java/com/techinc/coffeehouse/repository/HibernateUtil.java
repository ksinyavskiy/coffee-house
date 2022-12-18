package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.User;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        throw new UnsupportedOperationException("You cannot create an instance of HibernateUtil using constructor!");
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties h2DbProperties = new Properties();
            h2DbProperties.put(Environment.DRIVER, "org.h2.Driver");
            h2DbProperties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
            h2DbProperties.put(Environment.URL, "jdbc:h2:mem:coffeehouse;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;");
            h2DbProperties.put(Environment.USER, "sa");
            h2DbProperties.put(Environment.PASS, "");
            h2DbProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            h2DbProperties.put(Environment.SHOW_SQL, "true");
            h2DbProperties.put(Environment.HBM2DDL_AUTO, "create");

            Configuration configuration = new Configuration();
            configuration.setProperties(h2DbProperties);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(h2DbProperties)
                                                                                  .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
