package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.DogJdbcDAOIntegrationTest;
import com.codecool.robodog2.dao.DogJdbcDao;
import com.codecool.robodog2.dao.PedigreeJdbcDao;
import com.codecool.robodog2.dto.PuppyNameAndParentsDto;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DogServiceTest {

    @Autowired
    PedigreeService pedigreeService;
    @Autowired
    DogJdbcDao dogJdbcDao;
    @Autowired
    PedigreeJdbcDao pedigreeJdbcDao;

    @Test
    void addPuppyTest() {
        dogJdbcDao.addDog(new Dog(Breed.BULLDOG, "Lujza", 5));
        dogJdbcDao.addDog(new Dog(Breed.LABRADOR, "Tom", 2));
        PuppyNameAndParentsDto puppyNameAndParents = new PuppyNameAndParentsDto("Buksi", 1, 2);
        pedigreeService.addPuppy(puppyNameAndParents);

        List<Pedigree> pedigreeList = pedigreeJdbcDao.listPedigrees();
        List<Pedigree> sameMomAndDad = pedigreeList.stream().filter(e -> e.getDadId() == 2).filter(e -> e.getMomId() == 1).collect(Collectors.toList());
        int newDogId = (int) sameMomAndDad.get(sameMomAndDad.size() - 1).getPuppyId();
        Dog newDog = dogJdbcDao.getDog(newDogId);

        assertEquals("Buksi", dogJdbcDao.getDog(newDogId).getName());
        assertEquals(0, newDog.getAge());
        assertEquals(1, pedigreeService.getMom(newDogId).getId());
        assertEquals(2, pedigreeService.getDad(newDogId).getId());
    }
}
