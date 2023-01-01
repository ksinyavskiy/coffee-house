package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.User;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> getById(int userId) {
        User user = sessionFactory.getCurrentSession()
                                  .get(User.class, userId);
        return Optional.ofNullable(user);
    }

    public void add(User user) {
        sessionFactory.getCurrentSession()
                      .persist(user);
    }

    public void remove(User user) {
        sessionFactory.getCurrentSession()
                      .remove(user);
    }

    public List<User> getAll() {
        return sessionFactory.getCurrentSession()
                             .createQuery("from User")
                             .list();
    }
}
