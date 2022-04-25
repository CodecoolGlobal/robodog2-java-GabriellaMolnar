package com.codecool.robodog2.dto;

import com.codecool.robodog2.model.Breed;

public class DogDto {
    private Breed breed;
    private String name;
    private int age;

    public DogDto(Breed breed, String name, int age) {
        this.breed = breed;
        this.name = name;
        this.age = age;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
