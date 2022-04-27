package com.codecool.robodog2.dto;

public class PedigreeDto {
    private long puppyId;
    private long momId;
    private long dadId;


    public PedigreeDto(long momId, long dadId, long puppyId) {
        this.puppyId = puppyId;
        this.momId = momId;
        this.dadId = dadId;

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

    public long getPuppyId() {
        return puppyId;
    }

    public void setPuppyId(long puppyId) {
        this.puppyId = puppyId;
    }
}
