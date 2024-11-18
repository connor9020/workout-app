//package com.workoutapp.controller;
//
//import com.workoutapp.entity.WorkoutType;
//import com.workoutapp.service.WorkoutTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/workout-types")
//@CrossOrigin(origins = "http://localhost:4200")
//public class WorkoutTypeController {
//
//    @Autowired
//    private WorkoutTypeService workoutTypeService;
//
//    // Create a new workout type
//    @PostMapping
//    public ResponseEntity<WorkoutType> createWorkoutType(@RequestBody WorkoutType workoutType) {
//        WorkoutType createdType = workoutTypeService.createWorkoutType(workoutType);
//        return ResponseEntity.ok(createdType);
//    }
//
//    // Get all workout types
//    @GetMapping
//    public ResponseEntity<List<WorkoutType>> getAllWorkoutTypes() {
//        List<WorkoutType> workoutTypes = workoutTypeService.getAllWorkoutTypes();
//        return ResponseEntity.ok(workoutTypes);
//    }
//
//    // Get a specific workout type by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<WorkoutType> getWorkoutTypeById(@PathVariable Long id) {
//        WorkoutType workoutType = workoutTypeService.getWorkoutTypeById(id);
//        return ResponseEntity.ok(workoutType);
//    }
//
//    // Update an existing workout type
//    @PutMapping("/{id}")
//    public ResponseEntity<WorkoutType> updateWorkoutType(@PathVariable Long id, @RequestBody WorkoutType workoutType) {
//        WorkoutType updatedType = workoutTypeService.updateWorkoutType(id, workoutType);
//        return ResponseEntity.ok(updatedType);
//    }
//
//    // Delete a workout type
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteWorkoutType(@PathVariable Long id) {
//        workoutTypeService.deleteWorkoutType(id);
//        return ResponseEntity.noContent().build();
//    }
//}
