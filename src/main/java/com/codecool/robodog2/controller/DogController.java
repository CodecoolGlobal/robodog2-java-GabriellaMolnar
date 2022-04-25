package com.codecool.robodog2.controller;

import com.codecool.robodog2.dto.DogDto;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    private DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    public List<Dog> listDogs() {
        return dogService.listDogs();
    }

    @PostMapping
    public void addDog(@RequestBody DogDto dogDto) {
        dogService.addDog(dogDto);
    }

    @GetMapping("/{id}")
    public Dog getDog(@PathVariable long id) {
        return dogService.getDog(id);
    }

    @PutMapping("/{id}")
    public void updateDog(@RequestBody DogDto dogDto, @PathVariable long id) {
        dogService.updateDog(dogDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable long id) {
        dogService.deleteDog(id);
    }

    @PostMapping("/withId")
    public long addDogAndReturnId(@RequestBody DogDto dogDto) {
        return dogService.addDogAndReturnId(dogDto);
    }
    @PostMapping("/random")
    public long addRandomDog(@RequestBody DogDto dogDto) {
        return dogService.createRandomDog();
    }
}
