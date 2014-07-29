package org.training.model.hib.dao.impls.ifaces;

import java.util.List;

import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
import org.training.model.impls.DaoException;

public interface IRoleDAO {
	
	public Role getExistRole(UserRoleEnum role) throws DaoException;

	public List<Role> getExistRoles() throws DaoException;
	
	public boolean addNewRole(Role role) throws DaoException;
	
	public boolean checkRole(Role role) throws DaoException;
	
	public Role getRoleByName(String roleName) throws DaoException;

}
