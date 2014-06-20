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
@Table(name = "priority", uniqueConstraints = @UniqueConstraint(columnNames = "PRIORITY_NAME"))
public class Priority implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String priorityName;
	private Set<Issue> issues = new HashSet<Issue>();
	
	public Priority() {
		
	}
	
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "PRIORITY_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "PRIORITY_NAME", nullable = false, length = 20)
	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "priority")
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		return "Priority [id=" + id + ", priorityName=" + priorityName + "]";
	}
	
	
	

}
