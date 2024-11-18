//package com.workoutapp.entity;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "workout_type")
//public class WorkoutType {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name", nullable = true)
//    private String typeName;
//
//    @OneToMany(mappedBy = "workoutType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Exercises> exercises;
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTypeName() {
//        return typeName;
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
//
//    public List<Exercises> getExercises() {
//        return exercises;
//    }
//
//    public void setExercises(List<Exercises> exercises) {
//        this.exercises = exercises;
//    }
//}
