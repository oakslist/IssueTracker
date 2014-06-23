package org.training.model.beans.hibbeans;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.training.model.hib.impls.DefaultTableClass;

@Entity
@Table(name = "project", uniqueConstraints = {
		@UniqueConstraint(columnNames = "PROJECT_NAME")
})
public class Project extends DefaultTableClass implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String projectName;
	private String description;
	private User manager;
	private Set<Issue> issues = new HashSet<Issue>();
	private Set<BuildFound> builds = new HashSet<BuildFound>();
	
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

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<BuildFound> getBuilds() {
		return builds;
	}

	public void setBuilds(Set<BuildFound> builds) {
		this.builds = builds;
	}
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "MANAGER", nullable = false)
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER) //(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = true)
	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName
				+ ", description=" + description 
				+ ", builds=" + builds + "]";
	}

}
