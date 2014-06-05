package org.training.model.beans;

import java.io.Serializable;
import java.sql.Date;

public class Issue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
    private String summary;
    private String description;
    private String status;
    private String resolution;
    private String type;
    private String priority;
    private String project;
    private String buildFound;
    private String assignee;
    private Date createDate;
    private String createdBy;
    private Date modifyDate;
    private String modifiedBy;
    private int createdById;
    private int modifiedById = 0;
    private int assigneeId = 0;
    
	public Issue(int id, String summary, String description, String status,
    		String resolution, String type, String priority, String project,
    		String buildFound, String assignee, Date createDate, 
    		String createdBy, Date modifyDate, String modifiedBy) {
    	this.id = id;
    	this.summary = summary;
    	this.description = description;
    	this.status = status;
    	this.resolution = resolution;
    	this.type = type;
    	this.priority = priority;
    	this.project = project;
    	this.buildFound = buildFound;
    	this.assignee = assignee;
    	this.createDate = createDate;
    	this.createdBy = createdBy;
    	this.modifyDate = modifyDate;
    	this.modifiedBy = modifiedBy;
    }
	
    public Issue() {
    	this(0, null, null, null, null, null, null, null, 
    			null, null, null, null, null, null);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
		
	public int getCreatedById() {
		return createdById;
	}

	public void setCreatedById(int createdById) {
		this.createdById = createdById;
	}

	public int getModifiedById() {
		return modifiedById;
	}

	public void setModifiedById(int modifiedById) {
		this.modifiedById = modifiedById;
	}
	
    public int getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", summary=" + summary
				+ ", description=" + description + ", status=" + status
				+ ", resolution=" + resolution + ", type=" + type
				+ ", priority=" + priority + ", project=" + project
				+ ", buildFound=" + buildFound + ", assignee=" + assignee
				+ ", createDate=" + createDate + ", createdBy=" + createdBy
				+ ", modifyDate=" + modifyDate + ", modifiedBy=" + modifiedBy
				+ "]";
	}
	
	   
}
