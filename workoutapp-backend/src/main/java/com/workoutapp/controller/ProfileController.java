package com.workoutapp.controller;

import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.service.WorkoutPlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ProfileController {

    private final WorkoutPlanService workoutPlanService;

    public ProfileController(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    @GetMapping("/profile/todays-workout")
    public WorkoutPlan getTodaysWorkout(@RequestParam String username) {
        LocalDate today = LocalDate.now();
        return workoutPlanService.getWorkoutPlanForDate(username, today);
    }
}
