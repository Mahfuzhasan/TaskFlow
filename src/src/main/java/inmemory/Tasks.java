package inmemory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import model.Task;
// tasks collection bean
public class Tasks {
	
	private Map<UUID, Task> tasks;
	
	public Tasks() {
		this.tasks = new LinkedHashMap<UUID,Task>();
	}
	// comment
	public void updateTask(UUID id) {
		Task task = tasks.get(id);
		if (task.isStatus()) {
			task.setStatus(false);
		} else {
			task.setStatus(true);
		}
	}
	
	public void deleteTask(UUID id) {
		tasks.remove(id);
	}
	
	public void createTask(Task task) {
		this.tasks.put(task.getTaskId(), task);
	}
	
	public Map<UUID, Task> getTasks(){
		return this.tasks;
	}
	
	public void setTasks(LinkedHashMap<UUID, Task> tasks) {
		this.tasks = tasks;
	}

	public Task getTask(UUID id) {
		return tasks.get(id);
	}

	public int Count() {
		return tasks.size();
	}
	
}
