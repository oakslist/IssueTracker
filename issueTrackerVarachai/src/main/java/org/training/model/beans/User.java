package org.training.model.beans;

import java.io.Serializable;

import org.training.model.beans.nums.UserRoleEnum;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private UserRoleEnum role;
	private String password;
	
	public User(String firstName, String lastName, String emailAddress, 
			UserRoleEnum role, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.role = role;
		this.password = password;
	}
	
	public User() {
		this.firstName = null;
		this.lastName = null;
		this.emailAddress = null;
		this.role = UserRoleEnum.GUEST;
		this.password = null;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public UserRoleEnum getRole() {
		return role;
	}

	public void setRole(UserRoleEnum role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", role=" + role
				+ ", password=" + password + "]";
	}

}
