package com.workoutapp.service;

import com.workoutapp.dto.ProfileDTO;
import com.workoutapp.entity.User;
import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.repository.UserRepository;
import com.workoutapp.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get the current user's profile
    public ProfileDTO getProfile(String username) {
        // Use Optional to safely handle the user retrieval
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        // Get today's workout plan (assuming such logic exists in WorkoutPlanRepository)
        WorkoutPlan todaysWorkout = workoutPlanRepository.findWorkoutPlanByUserUsernameAndDate(username, LocalDate.now()).orElse(null);

        // Create ProfileDTO with current workout included
        return new ProfileDTO(user.getId(), user.getUsername(), user.getEmail(), user.getName(), null, todaysWorkout);
    }
    
    // Update the user's profile
    public ProfileDTO updateProfile(String username, ProfileDTO profileDTO) {
        // Use Optional to safely retrieve the user
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user object with new profile data
        user.setName(profileDTO.getName());
        user.setEmail(profileDTO.getEmail());
        
        // Encode and set the password only if provided
        if (profileDTO.getPassword() != null && !profileDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(profileDTO.getPassword()));
        }

        // Save the updated user object
        userRepository.save(user);

        // Return the updated user as a ProfileDTO
        return new ProfileDTO(user.getId(), user.getUsername(), user.getEmail(), user.getName(), null, null);
    }
    
    // Delete the user by username
    public void deleteUser(String username) {
        // Use Optional to safely retrieve the user
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

        // Delete all workout plans associated with the user
        workoutPlanRepository.deleteByUser(user);

        // Delete the user
        userRepository.delete(user);
    }
}

