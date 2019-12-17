package com.ifi.trainer_api.controller;

import com.ifi.trainer_api.bo.Trainer;
import com.ifi.trainer_api.service.TrainerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;


    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    Iterable<Trainer> getAllTrainers(){
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{name}")
    Trainer getTrainer(@PathVariable String name){
        return trainerService.getTrainer(name);
    }

    @PostMapping("/")
    Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }

    @PutMapping("/{name}")
    Trainer updateTrainer(@PathVariable String name, @RequestBody Trainer trainerDetails) {
        return trainerService.updateTrainer(name, trainerDetails);
    }

    @DeleteMapping("/{name}")
    void deleteTrainer(@PathVariable String name) {
        trainerService.deleteTrainer(name);
    }

}
