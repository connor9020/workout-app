package com.workoutapp.repository;

import com.workoutapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Return Optional<User>
    Optional<User> findByEmail(String email);       // Return Optional<User>
}
