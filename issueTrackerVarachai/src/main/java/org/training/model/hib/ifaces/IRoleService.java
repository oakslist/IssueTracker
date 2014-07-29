package org.training.model.hib.ifaces;

import java.util.List;

import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
import org.training.model.impls.DaoException;

public interface IRoleService {
	
	public Role getExistRole(UserRoleEnum role) throws DaoException;
	public Role getRoleByName(String roleName) throws DaoException;
	public List<Role> getExistRoles() throws DaoException;

}
