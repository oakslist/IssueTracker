package org.training.model.hib.dao.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.model.beans.hibbeans.Comment;
import org.training.model.hib.dao.impls.ifaces.ICommentDAO;
import org.training.model.impls.DaoException;

@Repository(value="commentDAOImpl")
public class CommentDAOImpl implements ICommentDAO {
	
	private static final Logger LOG = Logger.getLogger(CommentDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Comment> getExistCommentsByIssueId(int issueId) throws DaoException {
		System.out.println("Set in get comment by issue id");
		LOG.info("Set in get comment by issue id");
		List<Comment> comments = new ArrayList<Comment>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			comments = (List<Comment>) session.createQuery(
				    "from Comment c where c.issue.id = ?")
				   .setInteger(0, issueId).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in get comment by issue id " + e);
		}
		return comments;
	}
	
	public boolean addComment(Comment comment)
			throws DaoException {
		boolean isSet = false;
		System.out.println("Set in comment");
		LOG.info("Set in comment");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in set comment " + e);
		}
		return isSet;
	}
	

}
