package org.training.model.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.training.ifaces.ITableDataDAO;
import org.training.model.beans.Project;

public class H2TableDataImpl extends AbstractBaseDB implements ITableDataDAO {

	@Override
	public Map<Integer, String> getStatuses() throws DaoException {
		return getData(ConstantsH2.SELECT_ALL_STATUSES, "statusId", "statusName");
	}

	@Override
	public Map<Integer, String> getTypes() throws DaoException {
		return getData(ConstantsH2.SELECT_ALL_TYPES, "typeId", "typeName");
	}

	@Override
	public Map<Integer, String> getResolutions() throws DaoException {
		return getData(ConstantsH2.SELECT_ALL_RESOLUTIONS, "resolutionId", "resolutionName");
	}

	@Override
	public Map<Integer, String> getPriorities() throws DaoException {
		return getData(ConstantsH2.SELECT_ALL_PRIORITIES, "priorityId", "priorityName");
	}
	
	@Override
	public Map<Integer, Project> getProjects() throws DaoException {
		Map<Integer, Project> projects = new HashMap<Integer, Project>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_ALL_PROJECTS);
			resultSet = stmt.executeQuery();
			while (resultSet != null && resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("projectId"));
				project.setName(resultSet.getString("name"));
				project.setDescription(resultSet.getString("description"));
				project.setBuild(resultSet.getString("buildNumber"));
				project.setManager(resultSet.getString("emailAddress") + " : "
						+ resultSet.getString("firstName") + " "
						+ resultSet.getString("lastName"));
				
				projects.put(resultSet.getInt("projectId"), project);
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_ALL_PROJECTS + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return projects;
	}
	
	@Override
	public Map<Integer, String> getBuildFound() throws DaoException {
		Map<Integer, String> builds = new HashMap<Integer, String>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_ALL_BUILDS);
			resultSet = stmt.executeQuery();
			while (resultSet != null && resultSet.next()) {
				builds.put(resultSet.getInt("buildId"), resultSet.getString("buildNumber"));
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_ALL_BUILDS + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return builds;
	}
	
	@Override
	public Map<Integer, String> getBuildFound(String projectNumber) throws DaoException {
		Map<Integer, String> builds = new HashMap<Integer, String>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_ALL_PROJECT_BUILDS);
			final int BIULD_NUMBER = 1;
			stmt.setString(BIULD_NUMBER, projectNumber);
			resultSet = stmt.executeQuery();
			while (resultSet != null && resultSet.next()) {
				builds.put(resultSet.getInt("buildId"), resultSet.getString("buildNumber"));
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_ALL_PROJECT_BUILDS + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return builds;
	}

	@Override
	public Map<Integer, String> getAssignee() throws DaoException {
		Map<Integer, String> users = new HashMap<Integer, String>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_ALL_USERS);
			resultSet = stmt.executeQuery();
			while (resultSet != null && resultSet.next()) {
				users.put(resultSet.getInt("userId"), resultSet.getString("emailAddress")
						+ " : " + resultSet.getString("firstName") + " "
						+ resultSet.getString("lastName"));
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_ALL_BUILDS + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return users;
	}
	
	private Map<Integer, String> getData(String sql, String idName, String fildName) {
		Map<Integer, String> data = new HashMap<Integer, String>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(sql);
			resultSet = stmt.executeQuery();
			while (resultSet != null && resultSet.next()) {
				data.put(resultSet.getInt(idName), resultSet.getString(fildName));
			}
		} catch (SQLException e) {
            System.err.println("Query: " + sql + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return data;
	}

}
