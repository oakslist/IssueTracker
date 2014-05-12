package by.epam.ifaces;

import by.epam.model.beans.UserRolePrivileges;
import by.epam.model.impls.DaoException;

public interface IPrivilegesDAO {
	
	public UserRolePrivileges getExistPrivileges(String role) throws DaoException;
	
}
