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

    // Fetch exercises by workout type name (or ID if using IDs)
    public List<Exercises> getExercisesByWorkoutType(String workoutTypeName) {
        // Assuming findExercisesByWorkoutTypeName method exists in ExercisesRepository
        return exercisesRepository.findByWorkoutType_TypeName(workoutTypeName);
    }

    // Save a new exercise
    public Exercises saveExercise(Exercises exercise) {
        return exercisesRepository.save(exercise);
    }
}
