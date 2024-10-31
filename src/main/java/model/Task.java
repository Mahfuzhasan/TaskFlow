package model;

public class Task {
	private String Title;
	private Boolean Status;
	private int UserId;
	private int TaskId;
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Boolean isStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
	
	public int getTaskId() {
		return TaskId;
	}

	public void setTaskId(int taskId) {
		TaskId = taskId;
	}


	public Task(String title, Boolean status, int id) {
		this.setTitle(title);
		this.setStatus(status);
		this.setUserId(id);
	}
	
	public Task(String title, int id) {
		this.setTitle(title);
		this.setStatus(true);
		this.setUserId(id);
	}
	public Task(String title, int userId, int taskId) {
		this.setTitle(title);
		this.setUserId(userId);
		this.setTaskId(taskId);
		this.setStatus(true);
	}
	public Task() {
		
	}
}
