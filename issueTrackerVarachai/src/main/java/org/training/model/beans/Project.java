package org.training.model.beans;

import java.io.Serializable;

public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	private String build;
	private String manager;
	
	public Project(int id, String name, String description,
			String build, String manager) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.build = build;
		this.manager = manager;
	}
	
	public Project() {
		this(0, null, null, null, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description="
				+ description + ", build=" + build + ", manager=" + manager
				+ "]";
	}
	
	

}
