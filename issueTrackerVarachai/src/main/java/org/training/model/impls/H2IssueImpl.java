package org.training.model.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.training.ifaces.IIssueDAO;
import org.training.model.beans.Issue;

public class H2IssueImpl extends AbstractBaseDB implements IIssueDAO {

	@Override
	public List<Issue> getAllIssues() throws DaoException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Issue> issuesList = new ArrayList<Issue>();
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_ALL_ISSUES2);
			resultSet = stmt.executeQuery();
			while (resultSet != null && resultSet.next()) {
				Issue issue = new Issue();
				issue.setId(resultSet.getInt("issueId"));
				issue.setSummary(resultSet.getString("summary"));
				issue.setDescription(resultSet.getString("description"));
				issue.setStatus(resultSet.getString("statusName"));
				issue.setResolution(resultSet.getString("resolutionName"));
				issue.setType(resultSet.getString("typeName"));
				issue.setPriority(resultSet.getString("priorityName"));
				issue.setProject(resultSet.getString("name"));
				issue.setBuildFound(resultSet.getString("buildNumber"));
				issue.setAssignee(resultSet.getString("assignee"));
				issue.setCreateDate(resultSet.getDate("createDate"));
				issue.setCreatedBy(resultSet.getString("createdBy"));
				issue.setModifyDate(resultSet.getDate("modifyDate"));
				issue.setModifiedBy(resultSet.getString("modifiedBy"));
				if (issue.getId() != 0 || issue.getSummary() != null 
						|| issue.getDescription() != null || issue.getStatus() != null
						|| issue.getResolution() != null || issue.getType() != null 
						|| issue.getPriority() != null || issue.getProject() != null
						|| issue.getBuildFound() != null || issue.getCreateDate() != null
						|| issue.getCreatedBy() != null || issue.getModifyDate() != null
						|| issue.getModifiedBy() != null) {
					issuesList.add(issue);
				}
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_ALL_ISSUES + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return issuesList;
	}

	@Override
	public List<Issue> getIssues(int firstNumber, int number)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setIssue(Issue issue) throws DaoException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		boolean isCreated = false;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.INSERT_ISSUE);
			final int SUMMARY = 1, DESCRIPTION = 2, STATUS = 3;
			final int TYPE = 4, PRIORITY = 5, PROJECT = 6, BUILD = 7;
			final int ASSIGNEE = 8, CREATED_BY = 9;
			stmt.setString(SUMMARY, issue.getSummary());
			stmt.setString(DESCRIPTION, issue.getDescription());
			stmt.setString(STATUS, issue.getStatus());
			stmt.setString(TYPE, issue.getType());
			stmt.setString(PRIORITY, issue.getPriority());
			stmt.setString(PROJECT, issue.getProject());
			stmt.setString(BUILD, issue.getBuildFound());
			stmt.setString(ASSIGNEE, issue.getAssignee());
			stmt.setInt(CREATED_BY, issue.getCreatedById());
			stmt.executeUpdate();
			isCreated = true;
		} catch (SQLException e) {
			System.err.println("Query: " + ConstantsH2.INSERT_ISSUE + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return isCreated;
	}

	@Override
	public Issue getIssue(int number) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
