package com.workoutapp.dto;

import com.workoutapp.entity.WorkoutPlan;

public class ProfileDTO {
    private String username;
    private String email;
    private WorkoutPlan currentWorkout;

    public ProfileDTO(String username, String email, WorkoutPlan currentWorkout) {
        this.username = username;
        this.email = email;
        this.currentWorkout = currentWorkout;
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

    public WorkoutPlan getCurrentWorkout() {
        return currentWorkout;
    }

    public void setCurrentWorkout(WorkoutPlan currentWorkout) {
        this.currentWorkout = currentWorkout;
    }
}
