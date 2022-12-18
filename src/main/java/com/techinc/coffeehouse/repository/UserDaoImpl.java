package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public User getUserById(Integer userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.find(User.class, userId);
        session.getTransaction().commit();
        session.close();

        return user;
    }
}
