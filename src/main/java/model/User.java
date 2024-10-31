package model;

import java.util.UUID;

public class User {
	private UUID id;
	private String Username;
	private String Email;
	private String Password;
	private Boolean Authenticated;

	public User(UUID id, String username, String email, String password) {
		this.setId(id);
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setAuthenticated(false);
	}
	
	public User(String username, String email, String password) {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setId(UUID.randomUUID());
	}
	
	public User(UUID id) {
		this.setId(id);
	}
	
	public User(UUID id, String username, String email, String password,Boolean status) {
		this.setId(id);
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setAuthenticated(status);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		this.Username = username;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public Boolean isAuthenticated() {
		return Authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.Authenticated = authenticated;
	}

}
