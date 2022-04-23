package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Trick;

import java.util.List;

public interface TrickDAO {

    void addTrick(Trick trick);

    List<Trick> listTricks();

    Trick getTrick(long id);

    void updateTrick(Trick trick, long id);

    void deleteTrick(long id);
}
