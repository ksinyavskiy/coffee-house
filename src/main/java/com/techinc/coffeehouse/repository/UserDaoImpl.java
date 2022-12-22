package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.User;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserById(int userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.find(User.class, userId);
        session.getTransaction().commit();
        session.close();

        return user;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }
}
