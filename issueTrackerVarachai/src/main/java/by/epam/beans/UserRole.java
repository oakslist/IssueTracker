package by.epam.beans;

import by.epam.impls.UserRolePrivileges;

public enum UserRole {

	ADMINISTRATOR("ADMINISTRATOR"), 
	USER("USER"), 
	GUEST("GUEST"); 

	private final String userRole;
	// set all privileges for userRole
	private final UserRolePrivileges userRolePrivileges;

	UserRole(String userRole) {
		this.userRole = userRole;
		this.userRolePrivileges = new UserRolePrivileges(userRole);
	}

	public String getUserRole() {
		return userRole;
	}
	
	public UserRolePrivileges getUserRolePrivileges() {
		return userRolePrivileges;
	}

}
