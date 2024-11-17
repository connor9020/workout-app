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
        System.out.println("Received workoutType: " + workoutType);
        List<Exercises> exercises = ExercisesService.getExercisesByWorkoutType(workoutType);
        return ResponseEntity.ok(exercises);
    }
    
    // Get all exercises
    @GetMapping("/all")
    public ResponseEntity<List<Exercises>> getAllExercises() {
        List<Exercises> exercises = ExercisesService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }
    
    
    // Add a new Exercises
    @PostMapping
    public ResponseEntity<Exercises> addExercises(@RequestBody Exercises Exercises) {
        Exercises savedExercises = ExercisesService.saveExercise(Exercises);
        return ResponseEntity.ok(savedExercises);
    }

    // Additional CRUD operations can be added as needed
}
