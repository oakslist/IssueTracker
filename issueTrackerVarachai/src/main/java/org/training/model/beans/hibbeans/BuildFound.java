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
@Table(name = "build", uniqueConstraints = @UniqueConstraint(columnNames = "BUILD_VALUE"))
public class BuildFound implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String buildValue;
	private Project project;
	private Set<Project> projects = new HashSet<Project>();
	
	public BuildFound() {
		
	}
	
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "BUILD_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "BUILD_VALUE", nullable = false, length = 20)
	public String getBuildValue() {
		return buildValue;
	}

	public void setBuildValue(String buildValue) {
		this.buildValue = buildValue;
	}
	
	@Column(name = "PROJECT", nullable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "build")
	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}



}
