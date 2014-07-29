package org.training.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.training.annotations.FieldEquals;

@FieldEquals(field = "password", equalsTo = "confirmPassword")
public class AddUserForm {

	@NotEmpty
	@Pattern(regexp = "([a-zA-Z]{1,50})")
	private String firstName;

	@NotEmpty
	@Pattern(regexp = "([a-zA-Z]{1,50})")
	private String lastName;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String role;

	@NotEmpty
	@Pattern(regexp = "^([a-zA-Z0-9@%$,.;]{5,20})")
	private String password;

	@NotEmpty
	@Pattern(regexp = "^([a-zA-Z0-9@%$,.;]{5,20})")
	private String confirmPassword;

	public AddUserForm() {

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
