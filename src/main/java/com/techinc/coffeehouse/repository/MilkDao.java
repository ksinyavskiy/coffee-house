package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.Milk;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MilkDao extends AbstractDao<Milk, Integer> {
    @Autowired
    public MilkDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
