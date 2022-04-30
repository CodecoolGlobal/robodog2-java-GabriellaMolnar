package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.TrickMapper;
import com.codecool.robodog2.model.Trick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("trickJdbcDao")
public class TrickJdbcDao implements TrickDAO {
    private static final Logger log = LoggerFactory.getLogger(DogJdbcDao.class);
    private JdbcTemplate jdbcTemplate;
    private TrickMapper trickMapper;

    public TrickJdbcDao(JdbcTemplate jdbcTemplate, TrickMapper trickMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.trickMapper = trickMapper;
    }

    @Override
    public void addTrick(Trick trick) {
        String sql = "INSERT INTO trick (name) VALUES (?)";
        int insert = jdbcTemplate.update(sql, trick.getName());
        if (insert == 1) {
            log.info("Trick inserted " + trick.getName());
        }
    }

    @Override
    public List<Trick> listTricks() {
        String sql = "SELECT id, name from trick";
        return jdbcTemplate.query(sql, trickMapper);
    }

    @Override
    public Trick getTrick(long id) {
        String sql = "SELECT id, name from trick WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, trickMapper, id);
    }

    @Override
    public void updateTrick(Trick trick, long id) {
        String sql = "UPDATE trick SET name = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, trick.getName(), id);
        if (update == 1) {
            log.info("Trick updated " + trick.getName());
        }
    }

    @Override
    public void deleteTrick(long id) {
        String sql = "DELETE from trick WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
