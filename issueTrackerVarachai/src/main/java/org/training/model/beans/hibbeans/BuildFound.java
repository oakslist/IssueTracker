package org.training.model.beans.hibbeans;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "build", uniqueConstraints = @UniqueConstraint(columnNames = "BUILD_VALUE"))
public class BuildFound implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String buildValue;
//	private Project project;
//	private Set<Project> projects = new HashSet<Project>();

	public BuildFound() {

	}

	@Id
	@Column(name = "BUILD_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = AUTO)
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

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "PROJECT_ID", nullable = false)
//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}

	@Override
	public String toString() {
		return "BuildFound [id=" + id + ", buildValue=" + buildValue + "]";
	}


}
