package by.epam.model.beans;

import by.epam.constants.ServletConstants;

public class User {

	private String username;
	private String password;
	private UserRole role;
	
	public User(String username, String password, UserRole role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User() {
		this(ServletConstants.DEFAULT_GUEST, 
				ServletConstants.DEFAULT_PASSWORD,
				UserRole.GUEST);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "username='" + username + "', password='" + password 
				+ "', role='" + role + "'";
	}

}
