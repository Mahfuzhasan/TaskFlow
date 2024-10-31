package com;

import java.util.UUID;

public class Task {
    private UUID id;          // Unique ID for each task
    private String title;      // Task title
    private String description;// Task description
    private String status;     // Task status (e.g., "Pending", "Completed")
    private UUID userId;       // ID of the user who owns this task

    // Constructor with all attributes, including task ID
    public Task(UUID id, String title, String description, String status, UUID userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    // Constructor for creating a new task without specifying an ID
    public Task(String title, String description, String status, UUID userId) {
        this.id = UUID.randomUUID(); // Generate a new unique ID for each new task
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public UUID getUserId() { return userId; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
}
