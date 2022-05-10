package com.codecool.robodog2.service;

import com.codecool.robodog2.dto.DogDto;
import com.codecool.robodog2.model.Breed;

import java.util.Random;

public class DogCreator {
    private final int maxDogAge = 20;
    private final int maxNameLength = 10;

    public DogDto createRandomDog() {
        Random random = new Random();
        int age = random.nextInt(maxDogAge + 1);
        int breedNumber = random.nextInt(Breed.values().length);
        Breed breed = Breed.values()[breedNumber];
        String name = generateRandomName(random);
        return new DogDto(breed, name, age);
    }

    public String generateRandomName(Random rnd) {
        int nameLength = rnd.nextInt(maxNameLength + 1);
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(nameLength);
        String firstLetter = String.valueOf(chars.charAt(rnd.nextInt(chars.length()))).toUpperCase();
        sb.append(firstLetter);
        for (int i = 0; i < nameLength - 1; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
