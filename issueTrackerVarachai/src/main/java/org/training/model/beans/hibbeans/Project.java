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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "project", uniqueConstraints = @UniqueConstraint(columnNames = "PROJECT_NAME"))
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String projectName;
	private String description;
	private BuildFound build;
	private User manager;
	private Set<Issue> issues = new HashSet<Issue>();
	
	public Project() {
		
	}
	
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "PROJECT_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "PROJECT_NAME", nullable = false, length = 20)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUILD_ID", nullable = false)
	public BuildFound getBuild() {
		return build;
	}

	public void setBuild(BuildFound build) {
		this.build = build;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName
				+ ", description=" + description + ", build=" + build
				+ ", manager=" + manager + ", issues=" + issues + "]";
	}
	
	

}
