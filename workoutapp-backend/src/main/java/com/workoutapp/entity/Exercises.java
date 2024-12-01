package com.workoutapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exercise", nullable = false)
    private String exercise;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "image_url")  // New column for image URL
    private String imageUrl;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {  
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {  
        this.imageUrl = imageUrl;
    }
}

