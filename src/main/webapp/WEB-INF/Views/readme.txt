Summary

Login and Registration: AuthenticationServlet uses the action field to distinguish between login and register requests.

Task Creation, Update, and Deletion: TaskServlet uses the action and taskId fields to identify and process each task action.

Notes
Hidden: Each form uses hidden fields to specify the type of action (login, register, update, or delete). The backend servlets (AuthenticationServlet and TaskServlet) can use to differentiate between different types of requests.

Form submission:

Login Form: Captures user/password, posts to auth servlet with action set to "login".
Registration Form: Captures username, email, and password, posts to auth servlet with action set to "register".
Task Creation Form: Captures a title for the task and posts to tasks servlet to create a new task associated with the logged-in user.
Task Update Form: For each task, allows toggling its completion status by posting the task’s ID and action set to "update" to the tasks servlet.
Task Deletion Form: Allows deleting a task by posting the task’s ID and action set to "delete" to the tasks servlet.
Error Handling: Each JSP page includes inline error message sections. If an error occurs during login or registration, the error message is displayed to the user by checking the presence of an attribute (e.g., request.getAttribute("error")).

Session and Authorization:
tasks.jsp checks for the presence of a user session attribute. If not found (indicating the user is not logged in), it redirects to login.jsp.

Task Display:
The tasks.jsp page dynamically lists all tasks associated with the user, displaying each task’s title along with options to mark it as complete/incomplete or delete it.