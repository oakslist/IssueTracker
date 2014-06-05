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
				issue.setAssigneeId(resultSet.getInt("assigneeId"));
				issue.setAssignee(resultSet.getString("emailAddress") + " : " 
						+ resultSet.getString("firstName") + " "
						+ resultSet.getString("lastName"));
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
            System.err.println("Query: " + ConstantsH2.SELECT_ALL_ISSUES2 + "\n" + e);
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
			final int ASSIGNEE_ID = 8, CREATED_BY = 9;
			stmt.setString(SUMMARY, issue.getSummary());
			stmt.setString(DESCRIPTION, issue.getDescription());
			stmt.setString(STATUS, issue.getStatus());
			stmt.setString(TYPE, issue.getType());
			stmt.setString(PRIORITY, issue.getPriority());
			stmt.setString(PROJECT, issue.getProject());
			stmt.setString(BUILD, issue.getBuildFound());
			stmt.setInt(ASSIGNEE_ID, issue.getAssigneeId());
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
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		Issue issue = new Issue();
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_ISSUE);
			final int ISSUE_ID = 1;
			stmt.setInt(ISSUE_ID, number);
			resultSet = stmt.executeQuery();
			if (resultSet != null && resultSet.next()) {
				issue.setId(resultSet.getInt("issueId"));
				issue.setSummary(resultSet.getString("summary"));
				issue.setDescription(resultSet.getString("description"));
				issue.setStatus(resultSet.getString("statusName"));
				issue.setResolution(resultSet.getString("resolutionName"));
				issue.setType(resultSet.getString("typeName"));
				issue.setPriority(resultSet.getString("priorityName"));
				issue.setProject(resultSet.getString("name"));
				issue.setBuildFound(resultSet.getString("buildNumber"));
				issue.setAssigneeId(resultSet.getInt("assigneeId"));
				issue.setCreateDate(resultSet.getDate("createDate"));
				issue.setCreatedBy(resultSet.getString("firstName") + resultSet.getString("lastName"));
				issue.setModifyDate(resultSet.getDate("modifyDate"));
				issue.setModifiedById(resultSet.getInt("modifiedBy"));
			}
		} catch (SQLException e) {
			System.err.println("Query: " + ConstantsH2.SELECT_ISSUE + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return issue;
	}

	@Override
	public boolean updateIssue(Issue issue) throws DaoException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		boolean isUpdated = false;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.UPDATE_ISSUE);
			final int MODIFY_DATE = 1, MODIFIED_BY = 2, SUMMARY = 3;
			final int DESCRIPTION = 4, STATUS = 5, RESOLUTION = 6;
			final int TYPE = 7, PRIORITY = 8, PROJECT = 9, BUILD = 10;
			final int ASSIGNEE_ID = 11, ISSUE_ID = 12;
			stmt.setDate(MODIFY_DATE, issue.getModifyDate());
			stmt.setInt(MODIFIED_BY, issue.getModifiedById());
			stmt.setString(SUMMARY, issue.getSummary());
			stmt.setString(DESCRIPTION, issue.getDescription());
			stmt.setString(STATUS, issue.getStatus());
			stmt.setString(RESOLUTION, issue.getResolution());
			stmt.setString(TYPE, issue.getType());
			stmt.setString(PRIORITY, issue.getPriority());
			stmt.setString(PROJECT, issue.getProject());
			stmt.setString(BUILD, issue.getBuildFound());
			stmt.setInt(ASSIGNEE_ID, issue.getAssigneeId());
			stmt.setInt(ISSUE_ID, issue.getId());
			stmt.executeUpdate();
			isUpdated = true;
		} catch (SQLException e) {
			System.err.println("Query: " + ConstantsH2.UPDATE_ISSUE + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return isUpdated;
	}

}
