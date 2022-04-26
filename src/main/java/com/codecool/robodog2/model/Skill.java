package com.codecool.robodog2.model;

import com.codecool.robodog2.dto.SkillDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Skill {

    @JsonIgnore
    private long id;
    private long dogId;
    private long trickId;
    private int level;

    public Skill() {
    }

    public Skill(SkillDto skillDto) {
        this.dogId = skillDto.getDogId();
        this.trickId = skillDto.getTrickId();
        this.level = skillDto.getLevel();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDogId() {
        return dogId;
    }

    public void setDogId(long dogId) {
        this.dogId = dogId;
    }

    public long getTrickId() {
        return trickId;
    }

    public void setTrickId(long trickId) {
        this.trickId = trickId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
