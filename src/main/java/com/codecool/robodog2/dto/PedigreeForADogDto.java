package com.codecool.robodog2.dto;

public class PedigreeForADogDto {
    private long momId;
    private long dadId;

    public PedigreeForADogDto(long momId, long dadId) {
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
}
