package org.training.ifaces;

import java.util.List;

import org.training.model.beans.User;
import org.training.model.impls.DaoException;


public interface IUserDAO {
	public List<User> getExistUsers() throws DaoException;
	public User getExistUser(String emailAddress, String password) throws DaoException;
}
