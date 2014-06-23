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

import org.training.model.hib.impls.DefaultTableClass;

@Entity
@Table(name = "resolution", uniqueConstraints = @UniqueConstraint(columnNames = "RESOLUTION_NAME"))
public class Resolution extends DefaultTableClass implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String resolutionName;
	private Set<Issue> issues = new HashSet<Issue>();
	
	public Resolution() {
		
	}

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "RESOLUTION_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "RESOLUTION_NAME", nullable = false, length = 20)
	public String getResolutionName() {
		return resolutionName;
	}

	public void setResolutionName(String resolutionName) {
		this.resolutionName = resolutionName;
	}
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resolution")
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "resolution")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resolution")
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		return "Resolution [id=" + id + ", resolutionName=" + resolutionName
				+ "]";
	}

	
}
