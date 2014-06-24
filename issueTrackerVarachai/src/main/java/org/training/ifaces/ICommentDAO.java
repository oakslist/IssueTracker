package org.training.ifaces;

import java.util.List;

import org.training.model.beans.Comment;
import org.training.model.impls.DaoException;

public interface ICommentDAO {
	
	public List<Comment> getCommentsByIssueId(int issueId) throws DaoException;
	public boolean setComment(Comment comment) throws DaoException;
	
}
