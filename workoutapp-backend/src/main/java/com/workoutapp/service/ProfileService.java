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
        // Fetch the user directly
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Get today's workout plan (assuming such logic exists in WorkoutPlanRepository)
        WorkoutPlan todaysWorkout = workoutPlanRepository.findWorkoutPlanByUserUsernameAndDate(username, LocalDate.now()).orElse(null);

        // Create ProfileDTO with current workout included
        return new ProfileDTO(user.getId(), user.getUsername(), user.getEmail(), user.getName(), null, todaysWorkout);
    }
    
    public ProfileDTO updateProfile(String username, ProfileDTO profileDTO) {
        // Retrieve the user using userRepository directly (not Optional)
        User user = userRepository.findByUsername(username);
        
        // If user is null, throw an exception
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Update the user object with new profile data
        user.setName(profileDTO.getName());
        user.setEmail(profileDTO.getEmail());
        user.setPassword(passwordEncoder.encode(profileDTO.getPassword()));  // Ensure password is encoded

        // Save the updated user object
        userRepository.save(user);

        // Return the updated user as a ProfileDTO
        return new ProfileDTO(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getPassword(), null);
    }




}
