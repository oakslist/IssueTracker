package org.training.myhibernate;

public class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private int roleId;
	private String password;

	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String emailAddress, 
			int role, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.roleId = role;
		this.password = password;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailAddress=" + emailAddress + ", roleId="
				+ roleId + ", password=" + password + "]";
	}
	
	
	
		
}
