package by.epam.model.beans;

import by.epam.ifaces.IPrivilegesDAO;
import by.epam.model.factories.PrivilegesFactory;
import by.epam.model.impls.DaoException;

public enum UserRole {

	ADMINISTRATOR("ADMINISTRATOR"), 
	USER("USER"), 
	GUEST("GUEST"); 

	private final String userRole;
	// set all privileges for userRole
	private final UserRolePrivileges userRolePrivileges;

	UserRole(String userRole) {
		this.userRole = userRole;
		IPrivilegesDAO privilegesDAO = PrivilegesFactory.getClassFromFactory();
		UserRolePrivileges userRolePrivilegesTmp = null;
		try {
			userRolePrivilegesTmp = privilegesDAO.getExistPrivileges(userRole);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		this.userRolePrivileges = userRolePrivilegesTmp;
	}

	public String getUserRole() {
		return userRole;
	}
	
	public UserRolePrivileges getUserRolePrivileges() {
		return userRolePrivileges;
	}

}
