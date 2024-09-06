package com.workoutapp.service;

import com.workoutapp.entity.WorkoutType;
import com.workoutapp.repository.WorkoutTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutTypeService {

    @Autowired
    private WorkoutTypeRepository workoutTypeRepository;

    // Create a new workout type
    public WorkoutType createWorkoutType(WorkoutType workoutType) {
        return workoutTypeRepository.save(workoutType);
    }

    // Get all workout types
    public List<WorkoutType> getAllWorkoutTypes() {
        return workoutTypeRepository.findAll();
    }

    // Get a workout type by its ID
    public WorkoutType getWorkoutTypeById(Long id) {
        Optional<WorkoutType> workoutType = workoutTypeRepository.findById(id);
        return workoutType.orElseThrow(() -> new RuntimeException("Workout type not found"));
    }

    // Update a workout type
    public WorkoutType updateWorkoutType(Long id, WorkoutType updatedType) {
        WorkoutType existingType = getWorkoutTypeById(id); // Ensure the type exists
        existingType.setTypeName(updatedType.getTypeName());
        return workoutTypeRepository.save(existingType);
    }

    // Delete a workout type
    public void deleteWorkoutType(Long id) {
        workoutTypeRepository.deleteById(id);
    }
}
