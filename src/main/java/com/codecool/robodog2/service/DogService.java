package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.DogJdbcDao;
import com.codecool.robodog2.dto.DogDto;
import com.codecool.robodog2.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {
    private DogJdbcDao dogJdbcDao;

    @Autowired
    public DogService(DogJdbcDao dogJdbcDao) {
        this.dogJdbcDao = dogJdbcDao;
    }

    public void addDog(DogDto dogDto) {
        dogJdbcDao.addDog(new Dog(dogDto));
    }

    public List<Dog> listDogs() {
        return dogJdbcDao.listDogs();
    }

    public Dog getDog(long id) {
       return dogJdbcDao.getDog(id);
    }

    public void updateDog(DogDto dogDto, long id) {
        dogJdbcDao.updateDog(new Dog(dogDto), id);
    }

    public void deleteDog(long id) {
        dogJdbcDao.deleteDog(id);
    }

    public long addDogAndReturnId(DogDto dogDto) {
        return dogJdbcDao.addDogAndReturnId(new Dog(dogDto));
    }

    public long createRandomDog(){
       DogDto dogDto = new DogCreator().createRandomDog();
       return addDogAndReturnId(dogDto);
    }
}
