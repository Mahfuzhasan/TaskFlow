<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    
    <!-- Registration Form -->
    <form action="auth" method="post">
        <!-- Hidden field to specify the action as "register" for the backend to handle -->
        <input type="hidden" name="action" value="register">
        
        <!-- Username input field -->
        <label for="username">Username:</label>
        <input type="text" name="username" required><br>
        
        <!-- Email input field (used for user account activation and communication) -->
        <label for="email">Email:</label>
        <input type="email" name="email" required><br>
        
        <!-- Password input field -->
        <label for="password">Password:</label>
        <input type="password" name="password" required><br>
        
        <!-- Submit button for registering a new account -->
        <button type="submit">Register</button>
    </form>

    <!-- Link to login page if the user already has an account -->
    <p>Already have an account? <a href="login.jsp">Login here</a></p>

    <!-- Error message displayed if registration fails (e.g., username/email already exists) -->
    <p style="color: red;">
        <% if (request.getAttribute("error") != null) { %>
            <%= request.getAttribute("error") %>
        <% } %>
    </p>
</body>
</html>
