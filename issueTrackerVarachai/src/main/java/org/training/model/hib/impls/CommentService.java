package org.training.model.hib.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.model.beans.hibbeans.Comment;
import org.training.model.hib.dao.impls.ifaces.ICommentDAO;
import org.training.model.hib.ifaces.ICommentService;
import org.training.model.impls.DaoException;

@Service(value="commentService")
public class CommentService implements ICommentService {
	
	// (required=false)
	//	private CommentDAOImpl commentDaoImpl;
	@Autowired
	private ICommentDAO commentDAOImpl;

	@Override
	@Transactional
	public List<Comment> getExistCommentsByIssueId(
			int issueId) throws DaoException {
		return commentDAOImpl.getExistCommentsByIssueId(issueId);
	}

	@Override
	@Transactional
	public boolean addComment(Comment comment)
			throws DaoException {
		return commentDAOImpl.addComment(comment);
	}

}
