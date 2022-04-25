package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Trick;
import com.codecool.robodog2.service.TrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trick")
public class TrickController {
    private TrickService trickService;

    @Autowired
    public TrickController(TrickService trickService) {
        this.trickService = trickService;
    }

    @PostMapping
    public void addTrick(@RequestBody String name) {
        trickService.addTrick(name);
    }

    @GetMapping
    public List<Trick> listTricks() {
        return trickService.listTricks();
    }

    @GetMapping("/{id}")
    public Trick getTrick(@PathVariable long id) {
        return trickService.getTrick(id);
    }

    @PutMapping("/{id}")
    public void updateTrick(@RequestBody String name, @PathVariable long id) {
        trickService.updateTrick(name, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrick(@PathVariable long id) {
        trickService.deleteTrick(id);
    }
}
