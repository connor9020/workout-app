package com.workoutapp.dto;

import com.workoutapp.entity.User;
import com.workoutapp.entity.WorkoutPlan;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private Set<String> roles;
    private WorkoutPlan currentWorkout;

    // Constructor to map fields from User entity
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
    
    public UserDTO(User user, WorkoutPlan currentWorkout) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.currentWorkout = currentWorkout;
    }

    // Getters and setters
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

	public WorkoutPlan getCurrentWorkout() {
		return currentWorkout;
	}

	public void setCurrentWorkout(WorkoutPlan currentWorkout) {
		this.currentWorkout = currentWorkout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
    
    
}
