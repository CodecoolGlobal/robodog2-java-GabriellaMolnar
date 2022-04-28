package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DogMemDao implements DogDAO {
    private List<Dog> dogList = new ArrayList<>();
    private AtomicLong id = new AtomicLong();

    @Override
    public void addDog(Dog dog) {
        dog.setId(id.getAndIncrement());
        dogList.add(dog);
    }

    @Override
    public List<Dog> listDogs() {
        return dogList;
    }

    @Override
    public Dog getDog(long id) {
        return dogList.stream().filter(e -> e.getId() == id).findFirst().get();
    }

    @Override
    public void updateDog(Dog dog, long id) {
        Dog dogToModify = getDog(id);
        dogToModify.setAge(dog.getAge());
        dogToModify.setBreed(dog.getBreed());
        dogToModify.setName(dog.getName());
    }

    @Override
    public void deleteDog(long id) {
        dogList.removeIf(e -> e.getId() == id);
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        dog.setId(id.getAndIncrement());
        dogList.add(dog);
        return dog.getId();
    }
}
