package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;

public class UserService {
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
                Boolean status = rs.getBoolean("status"); // Assuming a 'status' column exists in the users table
                return new User(userId, username, email, password, status);
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
            stmt.setObject(1, user.getId());
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
}