package com.workoutapp.controller;

import com.workoutapp.dto.ProfileDTO;
import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.service.ProfileService;
import com.workoutapp.service.WorkoutPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ProfileController {

    private final WorkoutPlanService workoutPlanService;
    private final ProfileService profileService;  // Inject ProfileService

    public ProfileController(WorkoutPlanService workoutPlanService, ProfileService profileService) {
        this.workoutPlanService = workoutPlanService;
        this.profileService = profileService;
    }

    // Endpoint to get today's workout for a given username
    @GetMapping("/profile/todays-workout")
    public WorkoutPlan getTodaysWorkout(@RequestParam String username) {
        LocalDate today = LocalDate.now();
        return workoutPlanService.getWorkoutPlanForDate(username, today);
    }
    
    // PUT request to update profile
    @PutMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileDTO profileDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();  // Get logged-in user's username
        ProfileDTO updatedProfile = profileService.updateProfile(username, profileDTO);  // Call service to update profile
        return ResponseEntity.ok(updatedProfile);  // Return updated profile
    }
}
