package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.dao.mapper.SkillMapper;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
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
    private DogMapper dogMapper;

    public SkillJdbcDao(JdbcTemplate jdbcTemplate, SkillMapper skillMapper, DogMapper dogMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.skillMapper = skillMapper;
        this.dogMapper = dogMapper;
    }

    @Override
    public void addSkill(Skill skill) {
        String sql = "INSERT INTO skill (dog_id, trick_id, level) VALUES (?,?,?)";
        int insert = jdbcTemplate.update(sql, skill.getDogId(), skill.getTrickId(), skill.getLevel());
        if (insert == 1) {
            //TODO id-t nem tudja visszaadni, egyelőre 0-t ad, aminek nincs értelme
            log.info("Skill inserted " + skill.getId());
        }
    }

    @Override
    public List<Skill> listSkills() {
        String sql = "SELECT id, dog_id, trick_id, level from skill";
        return jdbcTemplate.query(sql, skillMapper);
    }

    @Override
    public Skill getSkill(long id) {
        String sql = "SELECT id, dog_id, trick_id, level from skill WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, skillMapper, id);
    }

    @Override
    public void updateSkill(Skill skill, long id) {
        String sql = "UPDATE skill SET dog_id = ?, trick_id = ?, level = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, skill.getDogId(), skill.getTrickId(), skill.getLevel(), id);
        if (update == 1) {
            log.info("Skill updated " + id);
        }
    }

    @Override
    public void deleteSkill(long id) {
        String sql = "DELETE from skill WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Dog> dogsWithTrick(long trickId) {
        String sql = "SELECT dog.id, breed, age, name from dog JOIN skill ON dog.id = skill.dog_id WHERE trick_id = ? ";
        return jdbcTemplate.query(sql, dogMapper, trickId);
    }

    @Override
    public Optional<Skill> getSkillOfDog(long dogId, long trickId) {
        String sql = "SELECT id, dog_id, trick_id, level from skill WHERE dog_id = ? AND trick_id = ?";
        Skill skill = null;
        try {
            skill = jdbcTemplate.queryForObject(sql, skillMapper,  dogId, trickId);
        } catch (DataAccessException ex) {
            log.info("Skill not found " + dogId + " " + trickId);
        }
        return Optional.ofNullable(skill);
    }
}
