package org.training.model.hib.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.dao.impls.ifaces.IUserDAO;
import org.training.model.hib.ifaces.IUserService;
import org.training.model.impls.DaoException;

@Service(value="userService")
public class UserService implements IUserService {

	@Autowired
	// (required=false)
	private IUserDAO userDAOImpl;

	@Transactional
	public User getUserByEmail(String email) {
		return userDAOImpl.getUserByEmail(email);
	}

	@Override
	@Transactional
	public List<User> getExistUsers() throws DaoException {
		return userDAOImpl.getExistUsers();
	}

	@Override
	@Transactional
	public User getExistUser(String emailAddress, String password)
			throws DaoException {
		return userDAOImpl.getExistUser(emailAddress, password);
	}

	@Override
	@Transactional
	public User getUserById(int id) throws DaoException {
		return userDAOImpl.getUserById(id);
	}

	@Override
	@Transactional
	public boolean addNewUser(User user) throws DaoException {
		return userDAOImpl.addNewUser(user);
	}

	@Override
	public boolean updateUser(User user) throws DaoException {
		return update(user);
	}

	@Override
	public boolean updateUserPassword(User user) throws DaoException {
		return update(user);
	}
	
	@Transactional
	private boolean update(User user) throws DaoException {
		return userDAOImpl.update(user);
	}

	

}
