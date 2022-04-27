package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.PedigreeMapper;
import com.codecool.robodog2.dao.mapper.SkillMapper;
import com.codecool.robodog2.model.Pedigree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedigreeJdbcDao implements PedigreeDao {
    private static final Logger log = LoggerFactory.getLogger(DogJdbcDao.class);
    private JdbcTemplate jdbcTemplate;
    private PedigreeMapper pedigreeMapper;

    public PedigreeJdbcDao(JdbcTemplate jdbcTemplate, PedigreeMapper pedigreeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.pedigreeMapper = pedigreeMapper;
    }


    @Override
    public void addPedigree(Pedigree pedigree) {
        String sql = "INSERT INTO pedigree (PUPPY_ID, MOM_ID, DAD_ID) VALUES (?,?,?)";
        int insert = jdbcTemplate.update(sql, pedigree.getPuppyId(), pedigree.getMomId(), pedigree.getDadId());
        if (insert == 1) {
            log.info("Dog inserted " + pedigree);
        }
    }

    @Override
    public List<Pedigree> listPedigrees() {
        String sql = "SELECT id, puppy_id, mom_id, dad_id from pedigree";
        return jdbcTemplate.query(sql, pedigreeMapper);
    }

    @Override
    public Pedigree getPedigree(long id) {
        String sql = "SELECT id, puppy_id, mom_id, dad_id from pedigree WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, pedigreeMapper, id);
    }

    @Override
    public void updatePedigree(Pedigree pedigree, long id) {
        String sql = "UPDATE pedigree SET puppy_id =?, mom_id=?, dad_id=? WHERE id = ?";
        jdbcTemplate.update(sql, pedigree.getPuppyId(), pedigree.getMomId(), pedigree.getDadId(), id);
    }

    @Override
    public void deletePedigree(long id) {
        String sql = "DELETE from pedigree WHERE id = ? ";
        jdbcTemplate.update(sql, id);
    }
}
