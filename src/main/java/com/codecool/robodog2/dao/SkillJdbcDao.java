package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.SkillMapper;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import jdk.jpackage.internal.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SkillJdbcDao implements SkillDAO {
    private static final Logger log = LoggerFactory.getLogger(DogJdbcDao.class);
    private JdbcTemplate jdbcTemplate;
    private SkillMapper skillMapper;

    @Override
    public void addSkill(Skill skill) {
        String sql = "INSERT INTO skill (dogId, trickId, level) VALUES (?,?,?)";
        int insert = jdbcTemplate.update(sql, skill.getDogId(), skill.getTrickId(), skill.getLevel());
        if (insert == 1) {
            log.info("Skill inserted " + skill.getId());
        }
    }

    @Override
    public List<Skill> listSkills() {
        String sql = "SELECT id, dogId, trickID, level from skill";
        return jdbcTemplate.query(sql, skillMapper);
    }

    @Override
    public Skill getSkill(long id) {
        String sql = "SELECT id, dogId, trickID, level from skill WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, skillMapper, id);
    }

    @Override
    public void updateSkill(Skill skill, long id) {
        String sql = "UPDATE skill SET dogId = ?, trickId = ?, level = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, skill.getDogId(), skill.getTrickId(), skill.getLevel(), id);
        if (update == 1) {
            log.info("Skill updated " + skill.getId());
        }
    }

    @Override
    public void deleteSkill(long id) {
        String sql = "DELETE from skill WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Dog> dogsWithTrick(long trickId) {
        //TODO
        return null;
    }

    @Override
    public Optional<Skill> getSkillOfDog(long dogId) {
        String sql = "SELECT id, dogId, trickID, level from skill WHERE dogId = ?";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, new Object[]{dogId}, skillMapper);
        } catch (DataAccessException ex) {
            Log.info("Skill not found" + dogId);
        }
        return Optional.ofNullable(skill);
    }

    @Override
    public Optional<Skill> getSkillOfTrick(long trickId) {
        String sql = "SELECT id, dogId, trickID, level from skill WHERE trickId) = ?";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, new Object[]{trickId}, skillMapper);
        } catch (DataAccessException ex) {
            Log.info("Skill not found" + trickId);
        }
        return Optional.ofNullable(skill);
    }
}
