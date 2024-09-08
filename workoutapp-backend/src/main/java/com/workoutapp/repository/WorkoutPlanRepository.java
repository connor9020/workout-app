package com.workoutapp.repository;

import com.workoutapp.entity.User;
import com.workoutapp.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
    Optional<WorkoutPlan> findWorkoutPlanByUserUsernameAndDate(String username, LocalDate date);
    List<WorkoutPlan> findAllByUserUsername(String username);
    void deleteByUser(User user);
}
