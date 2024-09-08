package com.workoutapp.controller;

import com.workoutapp.entity.User;
import com.workoutapp.service.AuthService;
import com.workoutapp.config.JwtUtil;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");

            System.out.println("Login request received: " + username + ", " + password);

            // Fetch the user directly as Optional<User>
            Optional<User> optionalUser = userService.findByUsername(username);
            
            // Throw exception if user is not found
            User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
            
            System.out.println("User found: " + user.getUsername() + ", Password: " + user.getPassword());

            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // Generate JWT token
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok().body("Bearer " + token);
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid username or password");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(500).body("Server error");
        }
    }
}
