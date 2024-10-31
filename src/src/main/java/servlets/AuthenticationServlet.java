package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inmemory.Tasks;
import model.User;
import service.TaskService;
import service.UserService;

@WebServlet("/auth")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public AuthenticationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {	

		// connect to database
		UserService dao = new UserService();
		
		//check action type
		if("register".equals(request.getParameter("action"))) {
			String userName = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			User user = new User(userName,email,password);
			dao.register(user);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else if ("login".equals(request.getParameter("action"))) {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			UUID userid  = dao.authenticate(userName, password);
			// check database for matching user
			if(user==null||!user.isAuthenticated()) {
				//handle error
				request.setAttribute("error", true);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				// create session go to tasks
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				Tasks tasks = dao.getTasks(user.getId());
				Map tasklist = tasks.getTasks();
				//request.setAttribute("tasks",new LinkedHashMap());
				request.setAttribute("tasks",tasklist);
				request.getRequestDispatcher("/tasks.jsp").forward(request, response);
				//response.sendRedirect("tasks");
			}
			
		}
	}
}
