package org.training.model.impls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.training.ifaces.ICommentDAO;
import org.training.model.beans.Comment;

public class H2CommentImpl extends AbstractBaseDB implements ICommentDAO {

	@Override
	public List<Comment> getCommentsByIssueId(int issueId) throws DaoException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Comment> commentsList = new ArrayList<Comment>();
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(ConstantsH2.SELECT_COMMENTS_BY_ISSUE_ID);
			final int ISSUE_ID = 1;
			stmt.setInt(ISSUE_ID, issueId);
			resultSet = stmt.executeQuery();
			while (resultSet != null && resultSet.next()) {
				Comment comment = new Comment();
				comment.setId(resultSet.getInt("commentId"));
				comment.setAddedById(resultSet.getInt("addedById"));
				comment.setAddedBy(resultSet.getString("emailAddress") + " : " 
						+ resultSet.getString("firstName") + " "
						+ resultSet.getString("lastName"));
				comment.setIssueId(resultSet.getInt("issueId"));
				comment.setAddDate(resultSet.getDate("addDate"));
				comment.setComment(resultSet.getString("comment"));
				commentsList.add(comment);
			}
		} catch (SQLException e) {
            System.err.println("Query: " + ConstantsH2.SELECT_COMMENTS_BY_ISSUE_ID + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return commentsList;
	}

	@Override
	public boolean setCommentByIssueId(Comment comment) throws DaoException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		boolean isCreated = false;
		try {
			connection = getConnection();
			// set new comment
			stmt = connection.prepareStatement(ConstantsH2.ADD_NEW_COMMENT);
			final int ADDED_BY_ID = 1, ISSUE_ID = 2, COMMENT = 3;
			stmt.setInt(ADDED_BY_ID, comment.getAddedById());
			stmt.setInt(ISSUE_ID, comment.getIssueId());
			stmt.setString(COMMENT, comment.getComment());
			synchronized (this) {
				stmt.executeUpdate();
				isCreated = true;
			}
		} catch (SQLException e) {
			System.err.println("Query: " + ConstantsH2.ADD_NEW_COMMENT + "\n" + e);
		} finally {
			closeConnection(connection, stmt, resultSet);
		}
		return isCreated;
	}

}
