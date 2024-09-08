package com.workoutapp.service;

import com.workoutapp.entity.User;
import com.workoutapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Use Optional to handle user lookup
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Throw exception if the user is not found
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Build UserDetails object using the Spring Security UserBuilder
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.roles(user.getRoles().toArray(new String[0])); // Assuming roles are stored as a Set<String>

        return builder.build();
    }
}
