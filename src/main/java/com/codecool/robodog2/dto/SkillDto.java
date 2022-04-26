package com.codecool.robodog2.dto;

public class SkillDto {

    private long dogId;
    private long trickId;
    private int level;

    public SkillDto(long dogId, long trickId, int level) {
        this.dogId = dogId;
        this.trickId = trickId;
        this.level = level;
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
