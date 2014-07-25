package org.training.model.hib.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
import org.training.model.hib.dao.impls.ifaces.IRoleDAO;
import org.training.model.hib.ifaces.IRoleService;
import org.training.model.impls.DaoException;

@Service(value="roleService")
public class RoleService implements IRoleService {

	@Autowired
	// (required=false)
	private IRoleDAO roleDAOImpl;

	@Override
	@Transactional
	public Role getExistRole(UserRoleEnum role) throws DaoException {
		return roleDAOImpl.getExistRole(role);
	}

	@Override
	@Transactional
	public List<Role> getExistRoles() throws DaoException {
		return roleDAOImpl.getExistRoles();
	}
	
	@Transactional
	public boolean addNewRole(Role role) throws DaoException {
		return roleDAOImpl.addNewRole(role);
	}
	
	@Transactional
	public boolean checkRole(Role role) throws DaoException {
		return roleDAOImpl.checkRole(role);
	}
}
