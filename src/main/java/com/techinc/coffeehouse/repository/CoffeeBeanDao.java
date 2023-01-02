package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.CoffeeBean;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CoffeeBeanDao extends AbstractDao<CoffeeBean, Integer> {

    @Autowired
    public CoffeeBeanDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


}
