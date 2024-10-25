package com.workoutapp.repository;

import com.workoutapp.entity.Exercises;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisesRepository extends JpaRepository<Exercises, Long> {
	List<Exercises> findByWorkoutType_TypeName(String typeName);

}
