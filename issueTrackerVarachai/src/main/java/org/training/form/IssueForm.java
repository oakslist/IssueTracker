package org.training.form;


import org.hibernate.validator.constraints.NotEmpty;

public class IssueForm {
	
	@NotEmpty
	public String summary;
	
	@NotEmpty
	public String description;
	
	@NotEmpty
	public String status;
	
	@NotEmpty
	public String type;
	
	@NotEmpty
	public String priority;
	
	@NotEmpty
	public String project;
	
	@NotEmpty
	public String buildFound;
	
	public String assignee;
	
	public String resolution;
	
	public IssueForm() {
		
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getBuildFound() {
		return buildFound;
	}

	public void setBuildFound(String buildFound) {
		this.buildFound = buildFound;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}


	
}
