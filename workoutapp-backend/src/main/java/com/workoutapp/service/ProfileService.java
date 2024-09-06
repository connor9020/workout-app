package com.workoutapp.service;

import com.workoutapp.dto.ProfileDTO;
import com.workoutapp.entity.User;
import com.workoutapp.entity.WorkoutPlan;
import com.workoutapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ProfileDTO getProfileByUsername(String username, WorkoutPlan currentWorkout) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return new ProfileDTO(user.getUsername(), user.getEmail(), currentWorkout);
    }
}
