package com.workoutapp.controller;

import com.workoutapp.entity.Exercises;
import com.workoutapp.service.ExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin(origins = "http://localhost:4200")
public class ExerciseController {

    private final ExercisesService exercisesService;

    public ExerciseController(ExercisesService exercisesService) {
        this.exercisesService = exercisesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exercises>> getAllExercises() {
        return ResponseEntity.ok(exercisesService.getAllExercises());
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Exercises>> getExercisesByType(@PathVariable String type) {
        return ResponseEntity.ok(exercisesService.getExercisesByType(type));
    }

    @GetMapping("/types")
    public List<String> getExerciseTypes() {
        return exercisesService.getDistinctTypes();
    }

    @PostMapping
    public ResponseEntity<Exercises> addExercise(@RequestBody Exercises exercise) {
        return ResponseEntity.ok(exercisesService.saveExercise(exercise));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        exercisesService.deleteExercise(id);
        return ResponseEntity.ok("Exercise with ID " + id + " deleted successfully.");
    }

}

