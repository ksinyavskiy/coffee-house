package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.Milk;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MilkDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public MilkDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Milk milk) {
        sessionFactory.getCurrentSession()
                      .persist(milk);
    }

    public void remove(Milk milk) {
        sessionFactory.getCurrentSession()
                      .remove(milk);
    }

    public Optional<Milk> getById(int milkId) {
        Milk milk = sessionFactory.getCurrentSession()
                                  .get(Milk.class, milkId);
        return Optional.ofNullable(milk);
    }

    public List<Milk> getAll() {
        return sessionFactory.getCurrentSession()
                             .createQuery("from Milk")
                             .list();
    }
}
