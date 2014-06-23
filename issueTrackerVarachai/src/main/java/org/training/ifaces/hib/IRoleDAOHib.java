package org.training.ifaces.hib;

import java.util.List;

import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
import org.training.model.impls.DaoException;

public interface IRoleDAOHib {
	
	public Role getExistRole(UserRoleEnum role) throws DaoException;
	public List<Role> getExistRoles() throws DaoException;

}
