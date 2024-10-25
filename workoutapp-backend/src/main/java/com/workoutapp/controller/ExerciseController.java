package com.workoutapp.controller;

import com.workoutapp.entity.Exercises;
import com.workoutapp.service.ExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = "http://localhost:4200") // Adjust the frontend origin if necessary
public class ExerciseController {

    @Autowired
    private ExercisesService ExercisesService;

    // Get Exercises by workout type
    @GetMapping
    public ResponseEntity<List<Exercises>> getExercisesByWorkoutType(@RequestParam String workoutType) {
        List<Exercises> Exercisess = ExercisesService.getExercisesByWorkoutType(workoutType);
        return ResponseEntity.ok(Exercisess);
    }

    // Add a new Exercises
    @PostMapping
    public ResponseEntity<Exercises> addExercises(@RequestBody Exercises Exercises) {
        Exercises savedExercises = ExercisesService.saveExercise(Exercises);
        return ResponseEntity.ok(savedExercises);
    }

    // Additional CRUD operations can be added as needed
}
