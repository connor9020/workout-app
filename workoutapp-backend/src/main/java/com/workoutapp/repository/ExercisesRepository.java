package com.workoutapp.repository;

import com.workoutapp.entity.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisesRepository extends JpaRepository<Exercises, Long> {
    // Additional query methods for Exercises if needed
}
