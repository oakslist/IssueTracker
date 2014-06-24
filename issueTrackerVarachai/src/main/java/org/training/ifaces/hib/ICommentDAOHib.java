package org.training.ifaces.hib;

import java.util.List;

import org.training.model.beans.hibbeans.Comment;
import org.training.model.impls.DaoException;

public interface ICommentDAOHib {

	public List<Comment> getExistCommentsByIssueId(int issueId) throws DaoException;
	public boolean addComment(Comment comment) throws DaoException;
}
