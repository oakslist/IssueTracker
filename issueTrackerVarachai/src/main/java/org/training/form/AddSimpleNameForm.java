package org.training.form;

import org.hibernate.validator.constraints.NotEmpty;

public class AddSimpleNameForm {
	
	@NotEmpty
	private String name;
	
	public AddSimpleNameForm() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
