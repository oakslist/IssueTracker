package org.training.model.beans;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String addedBy;
	private int addedById;
	private int issueId;
	private Date addDate;
	private String comment;
	
	public Comment(int id, String addedBy, int addedById, int issueId,
			Date adDate, String comment) {
		this.id = id;
		this.addedBy = addedBy;
		this.addedById = addedById;
		this.issueId = issueId;
		this.addDate = adDate;
		this.comment = comment;
	}
	
	public Comment() {
		this(0, null, 0, 0, null, null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public int getAddedById() {
		return addedById;
	}

	public void setAddedById(int addedById) {
		this.addedById = addedById;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", addedBy=" + addedBy + ", addedById="
				+ addedById + ", addDate=" + addDate + ", comment=" + comment
				+ "]";
	}
	
	
}
