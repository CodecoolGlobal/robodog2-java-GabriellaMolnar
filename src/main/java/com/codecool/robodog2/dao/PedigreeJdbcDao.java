package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.PedigreeMapper;
import com.codecool.robodog2.model.Pedigree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("pedigreeJdbcDao")
public class PedigreeJdbcDao implements PedigreeDao {
    private static final Logger log = LoggerFactory.getLogger(DogJdbcDao.class);
    private JdbcTemplate jdbcTemplate;
    private PedigreeMapper pedigreeMapper;
    private DogJdbcDao dogJdbcDao;

    public PedigreeJdbcDao(JdbcTemplate jdbcTemplate, PedigreeMapper pedigreeMapper, DogJdbcDao dogJdbcDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.pedigreeMapper = pedigreeMapper;
        this.dogJdbcDao = dogJdbcDao;
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

    public Optional<List<Long>> getChildren(long  dogId) {
        Optional<List<Long>> result;
        try {
            String sql = "SELECT id, puppy_id, mom_id, dad_id from pedigree WHERE mom_id = ? OR dad_id = ?";
            List<Pedigree>  pedigrees = jdbcTemplate.query(sql, pedigreeMapper, dogId, dogId);
            List<Long> resultList = pedigrees.stream().map(Pedigree::getPuppyId).collect(Collectors.toList());
            result = Optional.ofNullable(resultList);
        } catch (EmptyResultDataAccessException e) {
            result = Optional.empty();
        }
        return result;
    }

    public void addPedigreeForADog(Pedigree pedigree) {
        String sql = "INSERT INTO pedigree (PUPPY_ID, MOM_ID, DAD_ID) VALUES (?,?,?)";
        int insert = jdbcTemplate.update(sql, pedigree.getPuppyId(), pedigree.getMomId(), pedigree.getDadId());
        if (insert == 1) {
            log.info("Pedigree inserted " + pedigree + " to dog " + pedigree.getId());
        }
    }

    @Override
    public void createPuppyPedigree(Pedigree pedigree) {
        addPedigree(pedigree);
    }

    @Override
    public Optional<Long> getDad(long puppyId) {
        Optional<Pedigree> pedigree;
        Optional<Long> result;
        try {
            String sql = "SELECT id, puppy_id, mom_id, dad_id from pedigree WHERE puppy_id = ?";
            pedigree = Optional.ofNullable(jdbcTemplate.queryForObject(sql, pedigreeMapper, puppyId));
            result = Optional.of(pedigree.get().getDadId());
        } catch (EmptyResultDataAccessException e) {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Optional<Long> getMom(long puppyId) {
        Optional<Pedigree> pedigree;
        Optional<Long> result;
        try {
            String sql = "SELECT id, puppy_id, mom_id, dad_id from pedigree WHERE puppy_id = ?";
            pedigree = Optional.ofNullable(jdbcTemplate.queryForObject(sql, pedigreeMapper, puppyId));
            result = Optional.of(pedigree.get().getMomId());
        } catch (EmptyResultDataAccessException e) {
            result = Optional.empty();
        }
        return result;
    }

}
