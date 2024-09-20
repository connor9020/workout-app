package com.workoutapp.service;

import com.workoutapp.entity.User;
import com.workoutapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user
    public User registerUser(User user) {
        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set roles without the "ROLE_" prefix (e.g., just "USER")
        user.setRoles(Set.of("USER"));  // Assuming roles are being set manually

        return userRepository.save(user);
    }

    // Find a user by username, handling Optional safely
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find a user by email, handling Optional safely
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
