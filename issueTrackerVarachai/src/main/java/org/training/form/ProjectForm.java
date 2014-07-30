package org.training.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.User;

public class ProjectForm {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Size(max = 100)
	private String description;
	
	@NotEmpty
	private BuildFound build;
	
	@NotEmpty
	private User manager;
	
	public ProjectForm() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BuildFound getBuild() {
		return build;
	}

	public void setBuild(BuildFound build) {
		this.build = build;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
		
}
