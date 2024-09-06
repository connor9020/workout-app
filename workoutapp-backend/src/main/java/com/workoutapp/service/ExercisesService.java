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

    public List<Exercises> getExercisesByWorkoutType(Long workoutTypeId) {
        // Assuming a method is added to find exercises by workout type
        return exercisesRepository.findAll(); // Replace with actual logic
    }
}
