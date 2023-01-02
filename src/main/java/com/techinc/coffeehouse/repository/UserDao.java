package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User, Integer> {
    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
