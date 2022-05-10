package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.DogMapper;
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
        //TODO
        assertEquals(4, 4);

    }

    @Test
    void listDogTest() {
        //TODO
    }

    @Test
    void getAtDogTest() {
        //TODO
    }

    @Test
    void updateDogTest() {
        //TODO
    }

    @Test
    void deleteDogTest() {
        //TODO
    }
}
