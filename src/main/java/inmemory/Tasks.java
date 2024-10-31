package inmemory;

import java.util.LinkedHashMap;
import java.util.Map;

import model.Task;

public class Tasks {
	
	private Map<Integer,Task> tasks;
	
	public Tasks() {
		this.tasks = new LinkedHashMap<Integer,Task>();
	}
	
	public void updateTask(int id) {
		Task task = tasks.get(id);
		if (task.isStatus()) {
			task.setStatus(false);
		} else {
			task.setStatus(true);
		}
	}
	
	public void deleteTask(int id) {
		tasks.remove(id);
	}
	
	public void createTask(Task task) {
		this.tasks.put(task.getTaskId(), task);
	}
	
	public Map<Integer, Task> getTasks(){
		return this.tasks;
	}
	
	public void setTasks(LinkedHashMap<Integer, Task> tasks) {
		this.tasks = tasks;
	}

	public Task getTask(int id) {
		return tasks.get(id);
	}

	public int Count() {
		return tasks.size();
	}
	
}
