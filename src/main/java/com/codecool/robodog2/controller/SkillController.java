package com.codecool.robodog2.controller;

import com.codecool.robodog2.dto.SkillDto;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import com.codecool.robodog2.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
public class SkillController {
    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public void addSkill(@RequestBody SkillDto skillDto) {
        skillService.addSkill(skillDto);
    }

    @GetMapping
    public List<Skill> listSkills() {
        return skillService.listSkills();
    }

    @GetMapping("/{skill_id}")
    public Skill getSkill(@PathVariable("skill_id") long id) {
        return skillService.getSkill(id);
    }

    @PutMapping("/{skill_id}")
    public void updateSkill(@RequestBody SkillDto skillDto, @PathVariable("skill_id") long id) {
        skillService.updateSkill(skillDto, id);
    }

    @DeleteMapping("/{skill_id}")
    public void deleteSkill(@PathVariable("skill_id") long id) {
        skillService.deleteSkill(id);
    }

    @GetMapping("/dogs/{trickId}")
    public List<Dog> dogsWithTrick(@PathVariable long trickId) {
        return skillService.dogsWithTrick(trickId);
    }

    @GetMapping("/dog/{dogId}/trick/{trickId}")
    public Optional<Skill> getSkillOfDog(@PathVariable long dogId, @PathVariable long trickId) {
        return skillService.getSkillOfDog(dogId, trickId);
    }

}
