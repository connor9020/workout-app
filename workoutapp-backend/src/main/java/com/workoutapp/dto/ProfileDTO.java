package com.workoutapp.dto;

import com.workoutapp.entity.WorkoutPlan;

public class ProfileDTO {

    private Long id;
    private String username;
    private String email;
    private String name;  
    private String password;
    private WorkoutPlan currentWorkout; // Adding currentWorkout field

    // Constructors
    public ProfileDTO() {}

    public ProfileDTO(Long id, String username, String email, String name, String password, WorkoutPlan currentWorkout) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.password = password;
        this.currentWorkout = currentWorkout;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WorkoutPlan getCurrentWorkout() {  // Getter for currentWorkout
        return currentWorkout;
    }

    public void setCurrentWorkout(WorkoutPlan currentWorkout) {  // Setter for currentWorkout
        this.currentWorkout = currentWorkout;
    }
}

