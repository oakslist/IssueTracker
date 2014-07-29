package org.training.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	
	@NotEmpty
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp="^([a-zA-Z0-9@%$,.;]{5,20})")
	private String password;
	
	public LoginForm() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
