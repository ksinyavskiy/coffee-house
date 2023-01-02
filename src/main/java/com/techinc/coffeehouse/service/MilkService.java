package com.techinc.coffeehouse.service;

import com.techinc.coffeehouse.entity.Milk;
import com.techinc.coffeehouse.exception.MilkNotFoundException;
import com.techinc.coffeehouse.repository.MilkDao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MilkService {

    private final MilkDao milkDao;

    @Autowired
    public MilkService(MilkDao milkDao) {
        this.milkDao = milkDao;
    }

    public Milk getMilkById(int milkId) {
        Optional<Milk> optMilk = milkDao.getEntityById(milkId);
        return optMilk.orElseThrow(() -> new MilkNotFoundException("Milk with id " + milkId + " does not exist"));
    }

    public void addMilk(Milk milk) {
        milkDao.addEntity(milk);
    }

    public void removeMilk(Milk milk) {
        milkDao.removeEntity(milk);
    }

    public List<Milk> getMilks() {
        return milkDao.getAllEntities();
    }
}
