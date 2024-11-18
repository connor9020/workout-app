package com.workoutapp.repository;

import com.workoutapp.entity.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExercisesRepository extends JpaRepository<Exercises, Long> {
    // Custom query to filter by type
    List<Exercises> findByType(String type);
}


    
 

