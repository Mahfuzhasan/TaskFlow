package service;

import inmemory.Tasks;
import model.Task;
import model.User;

public class TaskService {

	public TaskService() {
	}

	public void update(int taskId, int userId) {
		System.out.println("data updated");
		
	}

	public void delete(int taskId, int userId) {
		System.out.println("data deleted");
		
	}

	public Task Create(String title, int userId) {
		System.out.println("data created");
		return new Task(title, false, userId);
	}

	public Tasks getTasks(int userId) {
		Tasks tasks = new Tasks();
		for (int i = 0; i<10; i++) {
			tasks.createTask(new Task("Stuff "+i,userId,i));
		}
		return tasks;
	}
	
	public void createUser(User user) {
		System.out.println("user created");
		
	}

	public User getUser(String username, String password) {
		return new User(username);
	}
}
