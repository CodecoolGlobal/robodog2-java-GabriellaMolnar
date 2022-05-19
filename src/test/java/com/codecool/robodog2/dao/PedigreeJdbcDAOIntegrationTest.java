package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.dao.mapper.PedigreeMapper;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Use the schema built in the .sql file put in resources
@Sql(scripts = {"classpath:schema.sql"})
@ComponentScan
@SpringBootTest
//As the context becomes dirty - database modifications happen, etc. - this method restores it to the original state
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PedigreeJdbcDAOIntegrationTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PedigreeJdbcDao pedigreeJdbcDao;
    @Autowired
    PedigreeMapper pedigreeMapper;
    @Autowired
    DogMapper dogMapper;
    @Autowired
    DogJdbcDao dogJdbcDao;

    @BeforeEach
    public void initEach() {
        dogJdbcDao.addDog(new Dog(Breed.BULLDOG, "Lujza", 5));
        dogJdbcDao.addDog(new Dog(Breed.LABRADOR, "Tom", 2));
        dogJdbcDao.addDog(new Dog(Breed.SPANIEL, "Bodza", 1));
        pedigreeJdbcDao.addPedigree(new Pedigree(3, 1, 2));
    }

    //TODO
    @Test
    void listPedigreesTest() {
        assertEquals(1,pedigreeJdbcDao.listPedigrees().size());
    }

    @Test
    void  getMomTest() {
       Long momId = pedigreeJdbcDao.getMom(3).get();
       assertEquals(1, momId);
       Optional<Long> notFoundId = pedigreeJdbcDao.getMom(4);
       assertEquals(Optional.empty(), notFoundId);
    }

    @Test
    void  getDadTest() {
        Long dadId = pedigreeJdbcDao.getDad(3).get();
        assertEquals(2, dadId);
    }
}
