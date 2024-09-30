package com.workoutapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow requests to all endpoints
                        .allowedOrigins("http://localhost:4200") // Allow your frontend's URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these HTTP methods
                        .allowedHeaders("Authorization", "Content-Type", "X-Requested-With") // Allow specific headers
                        .allowCredentials(true); // Allow credentials like cookies and authorization headers
            }
        };
    }
}
