package com.codecool.robodog2.model;

import com.codecool.robodog2.dto.PedigreeDto;
import com.codecool.robodog2.dto.PedigreeForADogDto;

public class Pedigree {

    private long id;
    private long puppyId;
    private long momId;
    private long dadId;


    public Pedigree() {
    }

    public Pedigree(PedigreeDto pedigreeDto) {
        this.momId = pedigreeDto.getMomId();
        this.dadId = pedigreeDto.getDadId();
        this.puppyId = pedigreeDto.getPuppyId();
    }

    public Pedigree(PedigreeForADogDto pedigreeForADogDto) {
        this.momId = pedigreeForADogDto.getMomId();
        this.dadId = pedigreeForADogDto.getDadId();
    }

    public Pedigree(long puppyId, long momId, long dadId) {
        this.puppyId = puppyId;
        this.momId = momId;
        this.dadId = dadId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
