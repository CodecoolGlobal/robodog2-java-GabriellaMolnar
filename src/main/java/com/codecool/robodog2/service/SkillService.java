package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.SkillJdbcDao;
import com.codecool.robodog2.dto.SkillDto;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    private SkillJdbcDao skillJdbcDao;

    @Autowired
    public SkillService(SkillJdbcDao skillJdbcDao) {
        this.skillJdbcDao = skillJdbcDao;
    }

    public void addSkill(SkillDto skillDto){
        skillJdbcDao.addSkill(new Skill(skillDto));
    }

    public List<Skill> listSkills(){
        return skillJdbcDao.listSkills();
    }

    public Skill getSkill(long id){
        return skillJdbcDao.getSkill(id);
    }

    public void updateSkill(SkillDto skillDto, long id){
        skillJdbcDao.updateSkill(new Skill(skillDto), id);
    }

    public  void deleteSkill(long id){
        skillJdbcDao.deleteSkill(id);
    }

    public List<Dog> dogsWithTrick(long trickId){
        return skillJdbcDao.dogsWithTrick(trickId);
    }

    public Optional<Skill> getSkillOfDog(long dogId, long trickId){
        return skillJdbcDao.getSkillOfDog(dogId, trickId);
    }


}
