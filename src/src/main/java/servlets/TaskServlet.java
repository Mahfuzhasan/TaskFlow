package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inmemory.Tasks;
import model.Task;
import model.User;
import service.TaskService;


@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;
	private Tasks tasks = new Tasks();
	
	public TaskServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		// display results
		request.getRequestDispatcher("/tasks.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		// get parameters
		String title = request.getParameter("title");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		user = (User)session.getAttribute("user");
		
		// connect to database
		TaskService dao = new TaskService();
		
		if(tasks.Count()==0) {
			List<Task> taskslist = dao.getTasksByUser(user.getId());
			LinkedHashMap<UUID,Task> tasksMap = new LinkedHashMap<UUID,Task>();
			for(Task task:taskslist) {
				tasksMap.put(task.getTaskId(), task);
			}
			tasks.setTasks(tasksMap);
		}
		//check action type
		if(action==null) {
			Task task = new Task(title,user.getId());
			dao.createTask(task);
			tasks.createTask(task);
		} else if (action.equals("update")) {
			UUID taskId = UUID.fromString(request.getParameter("taskId"));
			dao.markTaskComplete(taskId);
			tasks.updateTask(taskId);
		} else if (action.equals("delete")) {
			UUID taskId = UUID.fromString(request.getParameter("taskId"));
			dao.deleteTask(taskId);
			tasks.deleteTask(taskId);
		} 
		Map newtasklist = tasks.getTasks();
		request.setAttribute("tasks", newtasklist);
		request.getRequestDispatcher("/tasks.jsp").forward(request, response);
	}
}
