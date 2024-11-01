package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Task;
import model.User;

public class DBService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public User authenticate(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UUID userId = UUID.fromString(rs.getString("id"));
                String username = rs.getString("username");
                String dbEmail = rs.getString("email");
                
                // Retrieve status as String and check if it is "Active"
                String statusString = rs.getString("status");
                Boolean status = "Active".equalsIgnoreCase(statusString); // true if "Active", false otherwise
                
                User user = new User(userId, username, dbEmail, password, status);
                return user;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error authenticating user: " + e.getMessage(), e);
        }
        return null; // Return null if authentication fails
    }

    public boolean register(User user) {
        String query = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getId().toString()); // Convert UUID to String
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error registering user: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean updateUser(User user) {
        String query = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setObject(4, user.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean deleteUser(UUID userId) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting user: " + e.getMessage(), e);
            return false;
        }
    }
    
    public boolean createTask(Task task) {
        String query = "INSERT INTO tasks (id, title, description, status, userId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, task.getTaskId());
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setBoolean(4, task.isStatus());
            stmt.setObject(5, task.getUserId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating task: " + e.getMessage(), e);
            return false;
        }
    }

    public List<Task> getTasksByUser(UUID userId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getBoolean("status"),
                    userId
                );
                //task.setStatus(rs.getString("status"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving tasks for user: " + e.getMessage(), e);
        }
        return tasks;
    }

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

    public List<Task> filterTasksByStatus(UUID userId, String status) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE userId = ? AND status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, userId);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getBoolean("status"),
                    userId
                );
                //task.setStatus(rs.getString("status"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error filtering tasks by status: " + e.getMessage(), e);
        }
        return tasks;
    }
}
