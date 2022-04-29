package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.DogJdbcDao;
import com.codecool.robodog2.dao.PedigreeJdbcDao;
import com.codecool.robodog2.dto.PedigreeDto;
import com.codecool.robodog2.dto.PedigreeForADogDto;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PedigreeService {

    private PedigreeJdbcDao pedigreeJdbcDao;
    private DogJdbcDao dogJdbcDao;

    @Autowired
    public PedigreeService(PedigreeJdbcDao pedigreeJdbcDao, DogJdbcDao dogJdbcDao) {
        this.pedigreeJdbcDao = pedigreeJdbcDao;
        this.dogJdbcDao = dogJdbcDao;
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

    public List<Integer> getFamily(long puppyId) {
        return pedigreeJdbcDao.getFamily(puppyId);
    }

    public void addPedigreeForADog(long puppyId, PedigreeForADogDto pedigreeForADogDto) {
        Pedigree pedigreeForTheDog = new Pedigree(pedigreeForADogDto);
        pedigreeForTheDog.setPuppyId(puppyId);
        pedigreeJdbcDao.addPedigreeForADog(pedigreeForTheDog);
    }

    public void addPuppy(String name, long mommId, long dadId) {
        Dog littleDog = new Dog();
        littleDog.setName(name);
        littleDog.setAge(0);
        littleDog.setBreed(puppyBreed(mommId, dadId));
        Pedigree pedigree = new Pedigree();
        pedigree.setPuppyId(littleDog.getId());
        pedigree.setMomId(mommId);
        pedigree.setDadId(dadId);
        pedigreeJdbcDao.createPuppyPedigree(pedigree, littleDog);

    }

    private Breed puppyBreed(long momId, long dadId) {
        Random r = new Random();
        int randomBreedNumber = r.nextInt(2);
        if (randomBreedNumber == 0) {
            //TODO, az jó, ha beautowirelem a dogJdbcDao-t?
             return dogJdbcDao.getDog(momId).getBreed();
        } else {
          return  dogJdbcDao.getDog(dadId).getBreed();
        }
    }

    public long getDad(long puppyId) {
       return pedigreeJdbcDao.getDad(puppyId);
    }

    public long getMom(long puppyId) {
        return pedigreeJdbcDao.getMom(puppyId);
    }
}
