package org.training.myhibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERUN")

public class UserUn implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private int roleId;
	private String password;

	
	public UserUn() {
		
	}
	
	public UserUn(String firstName, String lastName, String emailAddress, 
			int role, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.roleId = role;
		this.password = password;
	}

	@Id @GeneratedValue
	@Column(name = "USER_ID", unique = true, nullable = false, precision = 5, scale = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 20)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EMAIL_ADDRESS", nullable = false, length = 50, unique = true)
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Column(name = "ROLE_ID", nullable = false, precision = 5, scale = 0)
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "PASSWORD", nullable = false, length = 40)
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
