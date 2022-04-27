package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.PedigreeJdbcDao;
import com.codecool.robodog2.dto.PedigreeDto;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedigreeService {

    private PedigreeJdbcDao pedigreeJdbcDao;

    @Autowired
    public PedigreeService(PedigreeJdbcDao pedigreeJdbcDao) {
        this.pedigreeJdbcDao = pedigreeJdbcDao;
    }

    public void addPedigree(PedigreeDto pedigreeDto) {
        pedigreeJdbcDao.addPedigree(new Pedigree(pedigreeDto));
    }

    public List<Pedigree> listPedigrees() {
        return pedigreeJdbcDao.listPedigrees();
    }

    public Pedigree getPedigree(long id) {
        return pedigreeJdbcDao.getPedigree(id);
    }

    public void updatePedigree(PedigreeDto pedigreeDto, long id) {
        pedigreeJdbcDao.updatePedigree(new Pedigree(pedigreeDto), id);
    }

    public void deletePedigree(long id) {
        pedigreeJdbcDao.deletePedigree(id);
    }
}
