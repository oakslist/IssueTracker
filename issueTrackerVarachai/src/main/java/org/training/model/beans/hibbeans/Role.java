package org.training.model.beans.hibbeans;

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
import static javax.persistence.GenerationType.AUTO;

/**
 * Role user privileges
 */

@Entity
@Table(name = "role", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ROLE_NAME")})

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int roleId;
	private String roleName;
	private Set<User> users = new HashSet<User>();

	public Role() {	
		
	}
	
	public Role(String roleName) {	
		this.roleName = roleName;
	}
	
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", unique = true, nullable = false, length = 13)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "roleId=" + roleId + ", roleName=" + roleName;
	}
	
	
}
