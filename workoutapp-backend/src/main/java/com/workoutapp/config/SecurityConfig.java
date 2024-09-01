package com.workoutapp.config;

import com.workoutapp.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    // Configure authentication using UserDetailsService and PasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Configure authorization rules and integrate JWT filter
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/login", "/api/auth/register").permitAll() // Public endpoints
                .anyRequest().authenticated() // Secure all other endpoints
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Stateless session management

        // Add the JWT filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // Bean for password encoding using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Expose the AuthenticationManager bean for use in other parts of the application
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

//Explanation
//@Configuration and @EnableWebSecurity Annotations:
//
//These annotations tell Spring that this class is a configuration class for Spring Security. @EnableWebSecurity activates the web security support.
//Authentication Configuration (configure(AuthenticationManagerBuilder auth)):
//
//userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()):
//Configures Spring Security to use your custom UserDetailsService to load user details (like username and password) and BCryptPasswordEncoder to hash passwords.
//This ensures that user passwords are stored securely in the database.
//Authorization Configuration (configure(HttpSecurity http)):
//
//http.csrf().disable(): Disables CSRF protection since JWT tokens are stateless, and CSRF is unnecessary for REST APIs.
//authorizeRequests(): Defines which endpoints are public (/api/auth/login, /api/auth/register) and which are secured (everything else).
//sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS): Configures the application to not use sessions. Instead, it relies entirely on JWT tokens for authentication.
//JWT Integration:
//
//addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class): This adds your custom JWT filter to the Spring Security filter chain. The filter intercepts incoming requests and validates the JWT token before the request reaches the controller.
//PasswordEncoder Bean:
//
//passwordEncoder(): Returns a BCryptPasswordEncoder bean, which is used to encode passwords before saving them to the database. This ensures that passwords are hashed and not stored in plain text.
//AuthenticationManager Bean:
//
//authenticationManagerBean(): Exposes the AuthenticationManager as a Spring bean, allowing it to be injected into other components like your authentication service.
//
