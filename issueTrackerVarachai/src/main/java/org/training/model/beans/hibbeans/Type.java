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
@Table(name = "type", uniqueConstraints = @UniqueConstraint(columnNames = "TYPE_NAME"))
public class Type implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String typeName;
	private Set<Issue> issues = new HashSet<Issue>();
	
	public Type() {
		
	}
	
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "TYPE_ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "TYPE_NAME", nullable = false, length = 20)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", typeName=" + typeName + "]";
	}
	
	

}
