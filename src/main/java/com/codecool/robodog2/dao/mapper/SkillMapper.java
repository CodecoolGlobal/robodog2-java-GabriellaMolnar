package com.codecool.robodog2.dao.mapper;

import com.codecool.robodog2.model.Skill;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SkillMapper implements RowMapper<Skill> {
    @Override
    public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setDogId(rs.getLong("dog_id"));
        skill.setTrickId(rs.getLong("trick_id"));
        skill.setLevel(rs.getInt("level"));
        return skill;
    }
}
