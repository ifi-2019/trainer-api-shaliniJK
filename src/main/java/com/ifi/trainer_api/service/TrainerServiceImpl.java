package com.ifi.trainer_api.service;

import com.ifi.trainer_api.bo.Trainer;
import com.ifi.trainer_api.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Iterable<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainer(String name) {
       Optional<Trainer> trainer = trainerRepository.findById(name);
       if (trainer.isPresent()) {
           return trainer.get();
       } else {
           return null;
       }
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public void deleteTrainer(String name) {
        trainerRepository.deleteById(name);
    }

    @Override
    public Trainer updateTrainer(String name, Trainer trainerDetails) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(name);
        if (trainerOptional.isPresent()) {
            Trainer trainer = trainerOptional.get();
            trainer.setName(trainerDetails.getName());
            trainer.setTeam(trainerDetails.getTeam());

            return trainerRepository.save(trainer);
        } else {
            return null;
        }
    }


}
