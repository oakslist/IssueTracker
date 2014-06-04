package org.training.model.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.training.ifaces.IUserDAO;
import org.training.model.beans.User;
import org.training.model.beans.nums.UserRoleEnum;


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
		PreparedStatement stmtSelect = null;
		PreparedStatement stmtAdd = null;
		ResultSet resultSet = null;
		boolean isCreated = false;
//		try {
//			connection = getConnection();
//			//check exist or not that login
//			stmtSelect = connection.prepareStatement(ConstantsH2.SELECT_USER_BY_LOGIN);
//			stmtAdd = connection.prepareStatement(ConstantsH2.ADD_NEW_USER);
//			final int LOGIN_INDEX = 1, PASSWORD_INDEX = 2;
//			synchronized (this) {
//				stmtSelect.setString(LOGIN_INDEX, "sss");
//				resultSet = stmtSelect.executeQuery();
//				if (!resultSet.next()) {
//					stmtAdd.setString(LOGIN_INDEX, "sss"); //user.getLogin());
//					stmtAdd.setString(PASSWORD_INDEX, user.getPassword());
//					stmtAdd.executeUpdate();
//					isCreated = true;
//				}
//			}
//		} catch (SQLException e) {
//			System.err.println("Query: " + ConstantsH2.ADD_NEW_USER + "\n" + e);
//		} finally {
//			closeConnection(connection, stmtSelect, stmtAdd, resultSet);
//		}
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
	
	
}
