package com;

import java.util.UUID;

public class Task {
    private UUID id;
    private String title;
    private String description;
    private String status;
    private UUID userId;

    public Task(String title, String description, String status, UUID userId) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
