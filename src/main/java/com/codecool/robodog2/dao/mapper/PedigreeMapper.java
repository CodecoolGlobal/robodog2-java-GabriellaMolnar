package com.codecool.robodog2.dao.mapper;

import com.codecool.robodog2.model.Pedigree;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PedigreeMapper  implements RowMapper<Pedigree> {
    @Override
    public Pedigree mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pedigree pedigree = new Pedigree();
        pedigree.setId(rs.getLong("id"));
        pedigree.setMomId(rs.getLong("mom_id"));
        pedigree.setDadId(rs.getLong("dad_id"));
        pedigree.setPuppyId(rs.getLong("puppy_id"));
        return pedigree;
    }
}
