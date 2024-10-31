package service;

import model.User;

public class UserService {

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public void createUser(User user) {
		System.out.println("user created");
		
	}

	public User getUser(String username, String password) {
		return new User(username);
	}

}
