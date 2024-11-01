<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    
    <!-- Login Form -->
    <form action="auth" method="post">
        <!-- Hidden field to specify the action as "login" for the backend to handle -->
        <input type="hidden" name="action" value="login">
        
        <!-- Username input field -->
        <label for="username">Username:</label>
        <input type="text" name="username" required><br>
        
        <!-- Password input field -->
        <label for="password">Password:</label>
        <input type="password" name="password" required><br>
        
        <!-- Submit button for logging in -->
        <button type="submit">Login</button>
    </form>

    <!-- Link to registration page if the user doesn't have an account -->
    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
    
    <!-- Error message displayed if login fails (e.g., invalid credentials or unauthenticated user) -->
    <p style="color: red;">
        <% if (request.getAttribute("error") != null) { %>
            Invalid credentials or account not authenticated. Please try again.
        <% } %>
    </p>
</body>
</html>
