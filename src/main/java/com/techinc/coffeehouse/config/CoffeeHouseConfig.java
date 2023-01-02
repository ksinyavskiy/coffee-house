package com.techinc.coffeehouse.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.techinc.coffeehouse.entity.CoffeeBean;
import com.techinc.coffeehouse.entity.Milk;
import com.techinc.coffeehouse.entity.User;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@EnableTransactionManagement
@ComponentScan("com.techinc.coffeehouse")
public class CoffeeHouseConfig {
    // MODE=LEGACY for allowing H2 to auto generate ID auto increment
    private static final String DATABASE_URL =
            "jdbc:h2:mem:coffeehouse;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=LEGACY;";

    @Bean
    public XsdSchema xsdSchema() {
        // the schema should be presented like a Spring bean, otherwise the following exception occurs:
        // schemaElement must not be null!
        return new SimpleXsdSchema(new ClassPathResource("xsd/users.xsd"));
    }

    @Bean("users")
    public DefaultWsdl11Definition wsdl11Definition(XsdSchema xsdSchema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("userPort");
        defaultWsdl11Definition.setTargetNamespace("http://coffeehouse.com/users");
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("org.h2.Driver");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        dataSource.setJdbcUrl(DATABASE_URL);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxPoolSize(20);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());

        localSessionFactoryBean.setAnnotatedClasses(User.class, Milk.class, CoffeeBean.class);

        Properties h2DbProperties = new Properties();
        h2DbProperties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        // do not use this property while using @Transactional annotation from Spring
        // h2DbProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        h2DbProperties.put(Environment.SHOW_SQL, "true");
        h2DbProperties.put(Environment.HBM2DDL_AUTO, "create-drop");

        localSessionFactoryBean.setHibernateProperties(h2DbProperties);

        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws PropertyVetoException {
        SessionFactory sessionFactory = Objects.requireNonNull(sessionFactory().getObject());
        return new HibernateTransactionManager(sessionFactory);
    }
}
