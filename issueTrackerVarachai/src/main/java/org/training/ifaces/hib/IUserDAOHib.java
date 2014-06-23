package org.training.ifaces.hib;

import java.util.List;

import org.training.model.beans.hibbeans.User;
import org.training.model.impls.DaoException;


public interface IUserDAOHib {

	public List<User> getExistUsers() throws DaoException;
	public User getExistUser(String emailAddress, String password) throws DaoException;
	public User getUserById(int id) throws DaoException;
	public boolean addNewUser(User user) throws DaoException;
	public boolean updateUser(User user) throws DaoException;
	public boolean updateUserPassword(User user) throws DaoException;
	
}
