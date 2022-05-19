package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.model.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("dogJdbcDao")
public class DogJdbcDao implements DogDAO {

    private static final Logger log = LoggerFactory.getLogger(DogJdbcDao.class);
    private JdbcTemplate jdbcTemplate;
    private DogMapper dogMapper;

    public DogJdbcDao(JdbcTemplate jdbcTemplate, DogMapper dogMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.dogMapper = dogMapper;
    }

    @Override
    public void addDog(Dog dog) {
        String sql = "INSERT INTO dog (breed, name, age) VALUES (?,?,?)";
        int insert = jdbcTemplate.update(sql, dog.getBreed().toString(), dog.getName(), dog.getAge());
        if (insert == 1) {
            log.info("Dog inserted " + dog.getName());
        }
    }

    @Override
    public List<Dog> listDogs() {
        String sql = "SELECT id, breed, name, age from dog";
        return jdbcTemplate.query(sql, dogMapper);
    }

    @Override
    public Dog getDog(long id) {
        String sql = "SELECT id, breed, name, age from dog WHERE id = ?";
        Dog dog = null;
        try {
            dog = jdbcTemplate.queryForObject(sql, dogMapper, id);
        } catch (DataAccessException ex) {
            log.info("Dog not found" + id);
        }
        return dog;
    }

    @Override
    public void updateDog(Dog dog, long id) {
        String sql = "UPDATE dog set breed = ?, name = ?, age = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, dog.getBreed().toString(), dog.getName(), dog.getAge(), id);
        if (update == 1) {
            log.info("Dog updated " + dog.getName());
        }
    }

    @Override
    public void deleteDog(long id) {
        jdbcTemplate.update("DELETE from dog WHERE id = ?", id);
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO dog (breed, name, age) VALUES (?,?,?)";
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, dog.getBreed().toString());
                    ps.setString(2, dog.getName());
                    ps.setInt(3, dog.getAge());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().longValue();
    }

}
