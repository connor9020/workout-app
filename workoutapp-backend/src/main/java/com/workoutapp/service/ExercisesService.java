package com.workoutapp.service;

import com.workoutapp.entity.Exercises;
import com.workoutapp.repository.ExercisesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercisesService {

    private final ExercisesRepository exercisesRepository;

    public ExercisesService(ExercisesRepository exercisesRepository) {
        this.exercisesRepository = exercisesRepository;
    }

    public List<Exercises> getAllExercises() {
        return exercisesRepository.findAll();
    }

    public List<Exercises> getExercisesByType(String type) {
        return exercisesRepository.findByType(type);
    }

    public Exercises saveExercise(Exercises exercise) {
        return exercisesRepository.save(exercise);
    }
    
    public void deleteExercise(Long id) {
        if (!exercisesRepository.existsById(id)) {
            throw new RuntimeException("Exercise with ID " + id + " not found.");
        }
        exercisesRepository.deleteById(id);
    }

}

