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
@Table(name = "attachment")
public class Attachment extends DefaultTableClass implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private User addedBy;
	private Date addedDate;
	private Issue issue;
	private String link;
	private String name;
	
	public Attachment() {
		
	}
	
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "ATTACHMENT_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}
	
	@Column(name = "ADDED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
	public Date getAddedDate() {
		return addedDate;
	}
	
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ISSUE_ID", nullable = false)
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Column(name = "LINK", nullable = false, length = 500)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Column(name = "NAME", nullable = false, length = 500)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", addedBy=" + addedBy + ", addedDate="
				+ addedDate + ", issue=" + issue + ", link=" + link + ", name="
				+ name + "]";
	}
	
}
