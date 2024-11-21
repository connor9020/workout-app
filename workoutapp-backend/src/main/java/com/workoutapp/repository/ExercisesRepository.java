package com.workoutapp.repository;

import com.workoutapp.entity.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExercisesRepository extends JpaRepository<Exercises, Long> {
    // Custom query to filter by type
    List<Exercises> findByType(String type);
    
    @Query("SELECT DISTINCT e.type FROM Exercises e")
    List<String> findDistinctTypes();
}


    
 

