package com.workoutapp.service;

import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanService {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    // Create a new workout plan
    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        return workoutPlanRepository.save(workoutPlan);
    }

    // Get a workout plan by its ID
    public WorkoutPlan getWorkoutPlanById(Long id) {
        Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.findById(id);
        return workoutPlan.orElseThrow(() -> new RuntimeException("Workout plan not found"));
    }

    // Get today's workout plan for a specific user
    public WorkoutPlan getWorkoutPlanForDate(String username, LocalDate date) {
        return workoutPlanRepository.findWorkoutPlanByUserUsernameAndDate(username, date)
                .orElseThrow(() -> new RuntimeException("No workout plan found for today"));
    }

    // Get all workout plans for a specific user
    public List<WorkoutPlan> getAllWorkoutPlansForUser(String username) {
        return workoutPlanRepository.findAllByUserUsername(username);
    }

    // Update a workout plan
    public WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan updatedPlan) {
        WorkoutPlan existingPlan = getWorkoutPlanById(id); // Ensure the plan exists
        existingPlan.setName(updatedPlan.getName());
        //existingPlan.setWorkoutType(updatedPlan.getWorkoutType());
        existingPlan.setDate(updatedPlan.getDate());
        return workoutPlanRepository.save(existingPlan);
    }

    // Delete a workout plan
    public void deleteWorkoutPlan(Long id) {
        workoutPlanRepository.deleteById(id);
    }
}
