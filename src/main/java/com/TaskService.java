package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskService {
    private static final Logger LOGGER = Logger.getLogger(TaskService.class.getName());

    // Method to create a new task in the database
    public boolean createTask(Task task) {
        String query = "INSERT INTO tasks (id, title, description, status, userId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, task.getId());
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setString(4, task.getStatus());
            stmt.setObject(5, task.getUserId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating task: " + e.getMessage(), e);
            return false;
        }
    }

    // Method to retrieve tasks by a specific user, including task IDs
    public List<Task> getTasksByUser(UUID userId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UUID taskId = UUID.fromString(rs.getString("id"));
                String title = rs.getString("title");
                String description = rs.getString("description");
                String status = rs.getString("status");
                Task task = new Task(taskId, title, description, status, userId);
                tasks.add(task);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving tasks for user: " + e.getMessage(), e);
        }
        return tasks;
    }

    // Method to delete a task based on task ID
    public boolean deleteTask(UUID taskId) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, taskId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting task: " + e.getMessage(), e);
            return false;
        }
    }

    // Method to mark a task as complete
    public boolean markTaskComplete(UUID taskId) {
        String query = "UPDATE tasks SET status = 'Completed' WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, taskId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error marking task as complete: " + e.getMessage(), e);
            return false;
        }
    }

    // Method to filter tasks by user ID and status, including task IDs
    public List<Task> filterTasksByStatus(UUID userId, String status) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE userId = ? AND status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, userId);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UUID taskId = UUID.fromString(rs.getString("id"));
                String title = rs.getString("title");
                String description = rs.getString("description");
                Task task = new Task(taskId, title, description, status, userId);
                tasks.add(task);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error filtering tasks by status: " + e.getMessage(), e);
        }
        return tasks;
    }
}
