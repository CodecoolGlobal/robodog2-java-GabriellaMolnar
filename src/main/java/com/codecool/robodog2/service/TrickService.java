package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.TrickJdbcDao;
import com.codecool.robodog2.model.Trick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrickService {
    private TrickJdbcDao trickJdbcDao;

    @Autowired
    public TrickService(TrickJdbcDao trickJdbcDao) {
        this.trickJdbcDao = trickJdbcDao;
    }

    public void addTrick(String name) {
        trickJdbcDao.addTrick(new Trick(name));
    }

    public List<Trick> listTricks() {
        return trickJdbcDao.listTricks();
    }

    public Trick getTrick(long id) {
        return trickJdbcDao.getTrick(id);
    }

    public void updateTrick(String name, long id) {
        trickJdbcDao.updateTrick(new Trick(name), id);
    }

    public void deleteTrick(long id) {
        trickJdbcDao.deleteTrick(id);
    }
}
