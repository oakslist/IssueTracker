package by.epam.ifaces;

import java.util.List;

import by.epam.model.beans.User;
import by.epam.model.impls.DaoException;


public interface IUserDAO {
	public List<User> getExistUsers() throws DaoException;
}
