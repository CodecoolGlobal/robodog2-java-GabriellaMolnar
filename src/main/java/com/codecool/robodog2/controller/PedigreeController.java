package com.codecool.robodog2.controller;

import com.codecool.robodog2.dto.PedigreeDto;
import com.codecool.robodog2.dto.PedigreeForADogDto;
import com.codecool.robodog2.dto.PuppyNameAndParentsDto;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import com.codecool.robodog2.service.PedigreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PedigreeController {
    private PedigreeService pedigreeService;

    @Autowired
    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }

    @PostMapping("/pedigree")
    public void addPedigree(@RequestBody PedigreeDto pedigreeDto) {
        pedigreeService.addPedigree(pedigreeDto);
    }

    @GetMapping("/pedigree")
    public List<Pedigree> listPedigrees() {
        return pedigreeService.listPedigrees();
    }

    @GetMapping("/pedigree/{id}")
    public Pedigree getPedigree(@PathVariable long id) {
        return pedigreeService.getPedigree(id);
    }

    @PutMapping("/pedigree/{id}")
    public void updatePedigree(@RequestBody PedigreeDto pedigreeDto, @PathVariable long id) {
        pedigreeService.updatePedigree(pedigreeDto, id);
    }

    @DeleteMapping("/pedigree/{id}")
    public void deletePedigree(@PathVariable long id) {
        pedigreeService.deletePedigree(id);
    }

    @GetMapping("/dog/{dog_id}/pedigree")
    public List<Integer> getFamily(@PathVariable("dog_id") long puppyId){
       return pedigreeService.getFamily(puppyId);
    }
    @PostMapping("/dog/{dog_id}/pedigree")
    public void addPedigreeForADog(@PathVariable("dog_id") long puppyId, @RequestBody PedigreeForADogDto pedigreeForADogDtoDto){
        pedigreeService.addPedigreeForADog(puppyId, pedigreeForADogDtoDto);
    }

    @PostMapping("/dog/puppy")
    public void addPuppy(@RequestBody PuppyNameAndParentsDto puppyNameAndParentsDto) {
        pedigreeService.addPuppy(puppyNameAndParentsDto);
    }

    @GetMapping("/dog/{dog_id}/pedigree/dad")
    public  Dog  getDad(@PathVariable("dog_id") long puppyId){
        return pedigreeService.getDad(puppyId);
    }
    @GetMapping("/dog/{dog_id}/pedigree/mom")
    public Dog getMom(@PathVariable("dog_id") long puppyId){
        return pedigreeService.getMom(puppyId);
    }
}
