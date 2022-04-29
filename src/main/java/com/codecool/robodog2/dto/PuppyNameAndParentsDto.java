package com.codecool.robodog2.dto;

public class PuppyNameAndParentsDto {
    private String name;
    private long momId;
    private long dadId;

    public PuppyNameAndParentsDto(String name, long momId, long dadId) {
        this.name = name;
        this.momId = momId;
        this.dadId = dadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMomId() {
        return momId;
    }

    public void setMomId(long momId) {
        this.momId = momId;
    }

    public long getDadId() {
        return dadId;
    }

    public void setDadId(long dadId) {
        this.dadId = dadId;
    }
}
