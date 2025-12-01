package com.example.backend_cs.service;

import com.example.backend_cs.exception.UserAlreadyExistsException;
import com.example.backend_cs.model.User;
import com.example.backend_cs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Register a new user
    public User registerUser(User user) {
        // Check if username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        
        // Save user to database
        userRepository.save(user);
        return user;
    }
    
    // Login user
    public User loginUser(String username, String password) {
        // Find user by username
        User user = userRepository.findByUsername(username);
        
        // Check if user exists
        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }
        
        // Check if password matches
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }
        
        return user;
    }
}

// EXPLANATION:
// @Service - Marks this as business logic component
// @Autowired - Automatically connects UserRepository
// registerUser() - Validates and saves new user
//   - Throws exception if username exists
// loginUser() - Verifies credentials and returns user
//   - Throws exception if username not found or password wrong
// NOTE: Passwords stored as plain text for simplicity
//       In production, use BCrypt encryption