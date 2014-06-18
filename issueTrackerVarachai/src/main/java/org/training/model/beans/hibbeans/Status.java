package org.training.model.beans.hibbeans;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "status", uniqueConstraints = @UniqueConstraint(columnNames = "STATUS_NAME"))
public class Status implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String statusName;
	private Set<Issue> issues = new HashSet<Issue>();
	
	public Status() {
		
	}

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "STATUS_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "STATUS_NAME", nullable = false, length = 20)
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", statusName=" + statusName + "]";
	}

}
