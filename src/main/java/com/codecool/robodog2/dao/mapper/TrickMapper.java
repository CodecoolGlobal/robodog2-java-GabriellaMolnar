package com.codecool.robodog2.dao.mapper;

import com.codecool.robodog2.model.Trick;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TrickMapper implements RowMapper<Trick> {
    @Override
    public Trick mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trick trick = new Trick();
        trick.setId(rs.getLong("id"));
        trick.setName(rs.getString("name"));
        return trick;
    }
}

