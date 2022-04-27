package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillDAO {
    void addSkill(Skill skill);

    List<Skill> listSkills();

    Skill getSkill(long id);

    void updateSkill(Skill skill, long id);

    void deleteSkill(long id);

    List<Dog> dogsWithTrick(long trickId);

    Optional<Skill> getSkillOfDog(long dogId, long trickId);

}
