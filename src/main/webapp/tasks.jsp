<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Task" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="inmemory.Tasks" %>
<%@ page import="java.util.UUID;" %>

<%
    // Retrieve the logged-in user from the session, redirect to login if not authenticated
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    // Retrieve the user's tasks passed from TaskServlet
    Map<UUID, Task> tasks = (Map<UUID, Task>) request.getAttribute("tasks");
%>

<html>
<head>
    <title>Task Management</title>
</head>
<body>
    <!-- Welcome message for the user -->
    <h2>Welcome, <%= user.getUsername() %></h2>
    
    <!-- Task Creation Form -->
    <form action="tasks" method="post">
        <!-- Input field for new task title -->
        <label for="title">Task Title:</label>
        <input type="text" name="title" required><br>
        
        <!-- Submit button to create a new task -->
        <button type="submit">Add Task</button>
    </form>
    
    <h3>Your Tasks</h3>
    <!-- Display list of tasks with options to mark as complete/incomplete and delete -->
    <ul>
        <% for (Map.Entry<UUID, Task> entry : tasks.entrySet()) { %>
            <li>
                <strong><%= entry.getValue().getTitle() %></strong>
                
                <!-- Form for toggling task status (complete/incomplete) -->
                <form action="tasks" method="post" style="display: inline;">
                    <input type="hidden" name="taskId" value="<%= entry.getKey() %>">
                    <!-- Hidden field specifying action as "update" for status toggle -->
                    <input type="hidden" name="action" value="update">
                    <button type="submit">
                        <% if (entry.getValue().isStatus()) { %>
                            Mark as Incomplete
                        <% } else { %>
                            Mark as Complete
                        <% } %>
                    </button>
                </form>
                
                <!-- Form for deleting a task -->
                <form action="tasks" method="post" style="display: inline;">
                    <input type="hidden" name="taskId" value="<%= entry.getKey() %>">
                    <!-- Hidden field specifying action as "delete" for task deletion -->
                    <input type="hidden" name="action" value="delete">
                    <button type="submit">Delete</button>
                </form>
            </li>
        <% } %>
    </ul>
    
    <!-- Logout link to end the session and return to the login page -->
    <a href="logout">Logout</a>
</body>
</html>