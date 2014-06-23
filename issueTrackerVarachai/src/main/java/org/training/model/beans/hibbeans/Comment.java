package org.training.model.beans.hibbeans;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.training.model.hib.impls.DefaultTableClass;

@Entity
@Table(name = "comment")
public class Comment extends DefaultTableClass implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private User addedBy;
	private Date addedDate;
	private Issue issue;
	private String comment;
	
	public Comment() {
		
	}
	
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "COMMENT_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}
	
	@Column(name = "ADDED_DATE", nullable = false, columnDefinition = "date default sysdate")
    @Temporal(TemporalType.TIMESTAMP)
	public Date getAddedDate() {
		return addedDate;
	}
	
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ISSUE_ID", nullable = false)
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Column(name = "COMMENT", nullable = false, length = 1000)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", addedBy=" + addedBy + ", addedDate="
				+ addedDate + issue + ", comment=" + comment + "]";
	}

	
	
}
