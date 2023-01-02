package com.techinc.coffeehouse.service;

import com.techinc.coffeehouse.entity.CoffeeBean;
import com.techinc.coffeehouse.exception.CoffeeBeanNotFoundException;
import com.techinc.coffeehouse.repository.CoffeeBeanDao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeBeanService {

    private final CoffeeBeanDao coffeeBeanDao;

    @Autowired
    public CoffeeBeanService(CoffeeBeanDao coffeeBeanDao) {
        this.coffeeBeanDao = coffeeBeanDao;
    }

    public CoffeeBean getCoffeeBean(int coffeeBeanId) {
        Optional<CoffeeBean> optCoffeeBean = coffeeBeanDao.getEntityById(coffeeBeanId);
        return optCoffeeBean.orElseThrow(CoffeeBeanNotFoundException::new);
    }

    public void addCoffeeBean(CoffeeBean coffeeBean) {
        coffeeBeanDao.addEntity(coffeeBean);
    }

    public void removeCoffeeBean(CoffeeBean coffeeBean) {
        coffeeBeanDao.removeEntity(coffeeBean);
    }

    public List<CoffeeBean> getAllCoffeeBeans() {
        return coffeeBeanDao.getAllEntities();
    }
}
