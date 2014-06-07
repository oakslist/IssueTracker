package org.training.model.beans.enums;

import org.training.ifaces.xmlDAO.IPrivilegesDAO;
import org.training.model.beans.UserRolePrivileges;
import org.training.model.factories.PrivilegesFactory;
import org.training.model.impls.DaoException;

public enum UserRoleEnum {

	ADMINISTRATOR("ADMINISTRATOR"), 
	USER("USER"), 
	GUEST("GUEST"); 

	private final String userRole;
	// set all privileges for userRole
	private final UserRolePrivileges userRolePrivileges;

	UserRoleEnum(String userRole) {
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
