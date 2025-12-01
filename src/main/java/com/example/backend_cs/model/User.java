package com.example.backend_cs.model;

public class User {
    
    private Long id;
    private String username;
    private String password;
    private String email;
    
    // Constructors
    public User() {
    }
    
    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}

// EXPLANATION:
// This is a simple Java class (POJO) representing a User
// It has 4 fields: id, username, password, email
// Contains constructors, getters, and setters
// This will be used to transfer data between layers