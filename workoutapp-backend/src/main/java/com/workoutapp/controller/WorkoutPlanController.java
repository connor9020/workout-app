package com.workoutapp.controller;

import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {

    @Autowired
    private WorkoutPlanService workoutPlanService;

    // Create a new workout plan
    @PostMapping
    public ResponseEntity<WorkoutPlan> createWorkoutPlan(@RequestBody WorkoutPlan workoutPlan) {
        WorkoutPlan createdPlan = workoutPlanService.createWorkoutPlan(workoutPlan);
        return ResponseEntity.ok(createdPlan);
    }

    // Get a specific workout plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlan> getWorkoutPlanById(@PathVariable Long id) {
        WorkoutPlan workoutPlan = workoutPlanService.getWorkoutPlanById(id);
        return ResponseEntity.ok(workoutPlan);
    }

    // Get today's workout plan for a user
    @GetMapping("/today")
    public ResponseEntity<WorkoutPlan> getTodaysWorkout(@RequestParam("username") String username) {
        LocalDate today = LocalDate.now();
        WorkoutPlan todaysPlan = workoutPlanService.getWorkoutPlanForDate(username, today);
        return ResponseEntity.ok(todaysPlan);
    }

    // Get all workout plans for a specific user
    @GetMapping("/user/{username}")
    public ResponseEntity<List<WorkoutPlan>> getAllWorkoutPlansForUser(@PathVariable String username) {
        List<WorkoutPlan> workoutPlans = workoutPlanService.getAllWorkoutPlansForUser(username);
        return ResponseEntity.ok(workoutPlans);
    }

    // Update an existing workout plan
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutPlan> updateWorkoutPlan(@PathVariable Long id, @RequestBody WorkoutPlan workoutPlan) {
        WorkoutPlan updatedPlan = workoutPlanService.updateWorkoutPlan(id, workoutPlan);
        return ResponseEntity.ok(updatedPlan);
    }

    // Delete a workout plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
        workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }
}
