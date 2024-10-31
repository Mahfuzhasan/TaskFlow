package com;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String status; // Added status attribute

    // Constructor for creating a new user with status
    public User(UUID id, String username, String email, String password, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    // Constructor for user registration without setting an ID
    public User(String username, String email, String password) {
        this.id = UUID.randomUUID(); // Generate a new unique ID
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = "Active"; // Default status; modify as needed
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getStatus() { return status; }

    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setStatus(String status) { this.status = status; }
}
