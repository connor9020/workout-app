package com.workoutapp.service;

import com.workoutapp.dto.UserDTO;
import com.workoutapp.entity.User;
import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.repository.UserRepository;
import com.workoutapp.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::new) // Map each User entity to a UserDTO
                .collect(Collectors.toList());
    }
    // Get the current user's profile
    public UserDTO getUser(String username) {
        // Retrieve user from repository
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retrieve today's workout plan
        WorkoutPlan todaysWorkout = workoutPlanRepository
                .findWorkoutPlanByUserUsernameAndDate(username, LocalDate.now())
                .orElse(null);

        // Use the overloaded constructor to include the workout
        return new UserDTO(user, todaysWorkout);
    }

    
    // Update the user's profile
    public UserDTO updateProfile(String username, UserDTO userDTO) {
        // Use Optional to safely retrieve the user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user object with new profile data
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        
        // Encode and set the password only if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        // Save the updated user object
        userRepository.save(user);

        // Return the updated user as a UserDTO
        return new UserDTO(user); // Assuming a matching constructor exists
    }
    
    // Delete the user by username
//    public void deleteUser(String username) {
//        // Use Optional to safely retrieve the user
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Delete all workout plans associated with the user
//        workoutPlanRepository.deleteByUser(user);
//
//        // Delete the user
//        userRepository.delete(user);
//    }
}

