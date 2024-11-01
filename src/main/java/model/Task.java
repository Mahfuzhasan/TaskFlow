package model;

import java.util.UUID;

public class Task {
	private String Title;
	private Boolean Status;
	private UUID UserId;
	private UUID TaskId;
	private String Description = "";
	
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

	public UUID getUserId() {
		return UserId;
	}

	public void setUserId(UUID userId) {
		UserId = userId;
	}
	
	public UUID getTaskId() {
		return TaskId;
	}

	public void setTaskId(UUID taskId) {
		TaskId = taskId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public Task(String title, Boolean status, UUID id) {
		this.setTitle(title);
		this.setStatus(status);
		this.setUserId(id);
	}
	
	public Task(String title, UUID id) {
		this.setTitle(title);
		this.setStatus(true);
		this.setUserId(id);
		this.TaskId = UUID.randomUUID();
	}
	public Task(String title, UUID userId, UUID taskId) {
		this.setTitle(title);
		this.setUserId(userId);
		this.setTaskId(taskId);
		this.setStatus(true);
	}
	public Task() {
		
	}
	public Task(String title, String des, Boolean status, UUID userId) {
		this.setTitle(title);
		this.setDescription(des);
		this.setStatus(status);
		this.setUserId(userId);
	}
}
