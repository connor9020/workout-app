package com.workoutapp.controller;

import com.workoutapp.dto.UserDTO;
import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.service.UserService;
import com.workoutapp.service.WorkoutPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final WorkoutPlanService workoutPlanService;
    private final UserService userService;  // Inject ProfileService

    public UserController(WorkoutPlanService workoutPlanService, UserService userService) {
        this.workoutPlanService = workoutPlanService;
        this.userService = userService;
    }
    
    // GET request to fetch all users
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    // Endpoint to get today's workout for a given username
    @GetMapping("/todays-workout")
    public WorkoutPlan getTodaysWorkout(@RequestParam String username) {
        LocalDate today = LocalDate.now();
        return workoutPlanService.getWorkoutPlanForDate(username, today);
    }
    
    // PUT request to update profile
    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UserDTO userDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();  // Get logged-in user's username
        UserDTO updatedProfile = userService.updateProfile(username, userDTO);  // Call service to update profile
        return ResponseEntity.ok(updatedProfile);  // Return updated profile
    }
    
    // DELETE request to delete profile/user
//    @DeleteMapping("/profile/delete")
//    public ResponseEntity<?> deleteUser(@RequestBody Map<String, String> body) {
//        String username = body.get("username");
//        profileService.deleteUser(username);
//        return ResponseEntity.ok("User successfully deleted");
//    }
}
