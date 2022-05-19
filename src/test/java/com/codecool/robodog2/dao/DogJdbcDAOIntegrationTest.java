package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;


//Use the schema built in this .sql file put in resources
@Sql(scripts = {"classpath:schema.sql"})
@ComponentScan
@SpringBootTest
//As the context becomes dirty - database modifications happen, etc. - this method restores it to the original state
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DogJdbcDAOIntegrationTest {


    //TODO
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DogMapper dogMapper;
    @Autowired
    DogJdbcDao dogJdbcDao;


    @Test
    void addDogTest() {
        dogJdbcDao.addDog(new Dog(Breed.BULLDOG, "Lujza", 5));
        assertEquals(1, dogJdbcDao.listDogs().size());
        assertEquals("Lujza", dogJdbcDao.listDogs().get(0).getName());
        assertEquals(4, 4);

    }

    @Test
    void listDogTest() {
        dogJdbcDao.addDog(new Dog(Breed.BULLDOG, "Lujza", 5));
        dogJdbcDao.addDog(new Dog(Breed.LABRADOR, "Tom", 2));
        assertEquals(2, dogJdbcDao.listDogs().size());
    }

    @Test
    void getAtDogTest() {
        dogJdbcDao.addDog(new Dog(Breed.BULLDOG, "Lujza", 5));
        assertEquals(5, dogJdbcDao.getDog(1).getAge());
        assertEquals(Breed.BULLDOG, dogJdbcDao.getDog(1).getBreed());
        assertEquals("Lujza", dogJdbcDao.getDog(1).getName());

    }

    @Test
    void updateDogTest() {
        dogJdbcDao.addDog(new Dog(Breed.BULLDOG, "Lujza", 5));
        Dog updateData = new Dog(Breed.LABRADOR, "Tom", 2);
        updateData.setId(1);
        dogJdbcDao.updateDog(updateData, 1);
        assertEquals(2, dogJdbcDao.getDog(1).getAge());
        assertEquals(Breed.LABRADOR, dogJdbcDao.listDogs().get(0).getBreed());
    }

    @Test
    void deleteDogTest() {
        dogJdbcDao.addDog(new Dog(Breed.BULLDOG, "Lujza", 5));
        dogJdbcDao.addDog(new Dog(Breed.LABRADOR, "Tom", 2));
        assertEquals(2, dogJdbcDao.listDogs().size());
        dogJdbcDao.deleteDog(1);
        assertEquals(1, dogJdbcDao.listDogs().size());
    }

    @Test
    void addDogAndReturnIdTest() {
        Long id = dogJdbcDao.addDogAndReturnId(new Dog(Breed.BULLDOG, "Lujza", 5));
        assertEquals(1, id);
    }
}
