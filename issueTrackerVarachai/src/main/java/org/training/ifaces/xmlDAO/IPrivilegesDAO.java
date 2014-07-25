package org.training.ifaces.xmlDAO;

import org.training.model.beans.UserRolePrivileges;
import org.training.model.impls.DaoException;

public interface IPrivilegesDAO {
	
	public UserRolePrivileges getExistPrivileges(String role) throws DaoException;
	
}
