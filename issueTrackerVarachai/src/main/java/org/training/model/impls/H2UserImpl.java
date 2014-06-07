package org.training.model.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.training.ifaces.IUserDAO;
import org.training.model.beans.User;
import org.training.model.beans.enums.UserRoleEnum;


public class H2UserImpl extends AbstractBaseDB implements IUserDAO {
	
	public User getExistUser(String email, String password) throws DaoException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_USER);
			final int EMAIL_ADDRESS_INDEX = 1, PASSWORD_INDEX = 2;
			stmt.setString(EMAIL_ADDRESS_INDEX, email);
			stmt.setString(PASSWORD_INDEX, password);
			resultSet = stmt.executeQuery();
			if (resultSet != null && resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("userId"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmailAddress(resultSet.getString("emailAddress"));
				user.setRole(UserRoleEnum.valueOf(resultSet.getString("roleName")));
				user.setPassword(resultSet.getString("password"));
				if (user.getEmailAddress().equals(email) && user.getPassword().equals(password)) {
					return user;
				}
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_USER + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return null;
	}

	public synchronized boolean addNewUser(User user) throws DaoException {
		Connection connection = null;
		PreparedStatement stmtAdd = null;
		PreparedStatement stmtRole = null;
		ResultSet resultSet = null;
		boolean isCreated = false;
		try {
			connection = getConnection();
			//set new user
			stmtRole = connection.prepareStatement(ConstantsH2.SELECT_ROLE_ID);
			final int ROLE_NAME = 1;
			stmtRole.setString(ROLE_NAME, user.getRole().toString());
			resultSet = stmtRole.executeQuery();
			int roleId = 0;
			if (resultSet != null && resultSet.next()) {
				roleId = resultSet.getInt("roleId");
				stmtAdd = connection.prepareStatement(ConstantsH2.ADD_NEW_USER);
				final int FIRST_NAME = 1, LAST_NAME = 2, EMAIL = 3;
				final int ROLE = 4, PASSWORD = 5;
				stmtAdd.setString(FIRST_NAME, user.getFirstName());
				stmtAdd.setString(LAST_NAME, user.getLastName());
				stmtAdd.setString(EMAIL, user.getEmailAddress());
				stmtAdd.setInt(ROLE, roleId);
				stmtAdd.setString(PASSWORD, user.getPassword());
				synchronized (this) {
					stmtAdd.executeUpdate();
					isCreated = true;
				}
			}
		} catch (SQLException e) {
			System.err.println("Query: " + ConstantsH2.ADD_NEW_USER + "\n" + e);
		} finally {
			closeConnection(connection, stmtAdd, stmtRole, resultSet);
		}
		return isCreated;
	}

	@Override
	public List<User> getExistUsers() throws DaoException {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_ALL_USERS);
			resultSet = stmt.executeQuery();
			while (resultSet != null && resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("userId"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmailAddress(resultSet.getString("emailAddress"));
				user.setRole(UserRoleEnum.valueOf(resultSet.getString("roleName")));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_ALL_USERS + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return users;
	}

	@Override
	public User getUserById(int id) throws DaoException {
		User user = new User();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_USER_BY_ID);
			final int ID_INDEX = 1;
			stmt.setInt(ID_INDEX, id);
			resultSet = stmt.executeQuery();
			if (resultSet != null && resultSet.next()) {
				user.setId(resultSet.getInt("userId"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmailAddress(resultSet.getString("emailAddress"));
				user.setRole(UserRoleEnum.valueOf(resultSet.getString("roleName")));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_USER_BY_ID + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return user;
	}

	@Override
	public boolean updateUser(User user) throws DaoException {
		Connection connection = null;
		PreparedStatement stmtUpd = null;
		PreparedStatement stmtRole = null;
		ResultSet resultSet = null;
		boolean isUpdated = false;
		try {
			connection = getConnection();
			//update user
			stmtRole = connection.prepareStatement(ConstantsH2.SELECT_ROLE_ID);
			final int ROLE_NAME = 1;
			stmtRole.setString(ROLE_NAME, user.getRole().toString());
			resultSet = stmtRole.executeQuery();
			int roleId = 0;
			if (resultSet != null && resultSet.next()) {
				roleId = resultSet.getInt("roleId");
				stmtUpd = connection.prepareStatement(ConstantsH2.UPDATE_USER);
				final int FIRST_NAME = 1, LAST_NAME = 2, EMAIL = 3;
				final int ROLE = 4, USER_ID = 5;
				stmtUpd.setString(FIRST_NAME, user.getFirstName());
				stmtUpd.setString(LAST_NAME, user.getLastName());
				stmtUpd.setString(EMAIL, user.getEmailAddress());
				stmtUpd.setInt(ROLE, roleId);
				stmtUpd.setInt(USER_ID, user.getId());
				synchronized (this) {
					stmtUpd.executeUpdate();
					isUpdated = true;
				}
			}
		} catch (SQLException e) {
			System.err.println("Query: " + ConstantsH2.UPDATE_USER + "\n" + e);
		} finally {
			closeConnection(connection, stmtUpd, stmtRole, resultSet);
		}
		return isUpdated;
	}
	
	
}
