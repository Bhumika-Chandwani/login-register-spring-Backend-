package com.example.backend_cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendCsApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BackendCsApplication.class, args);
        System.out.println("===========================================");
        System.out.println("Backend Server Started Successfully!");
        System.out.println("Running on: http://localhost:8080");
        System.out.println("===========================================");
    }
}

// EXPLANATION:
// This is the main entry point of the Spring Boot application
// @SpringBootApplication enables auto-configuration
// Server starts on PORT 8080 (defined in application.properties)