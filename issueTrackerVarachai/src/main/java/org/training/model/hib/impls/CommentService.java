package org.training.model.hib.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.training.ifaces.hib.ICommentDAOHib;
import org.training.model.beans.hibbeans.Comment;
import org.training.model.impls.DaoException;
import org.training.persistence.HibernateUtil;

public class CommentService implements ICommentDAOHib {
	
	private static final Logger LOG = Logger.getLogger(CommonService.class);

	private Session openSession() {
		return HibernateUtil.getHibSessionFactory().openSession();
	}
	
//	private ClassPathXmlApplicationContext getContext() {
//		return HibernateUtil.getContext();
//	}

	private void closeSession(Session session) {
		session.close();
	}

	@Override
	public List<Comment> getExistCommentsByIssueId(
			int issueId) throws DaoException {
		System.out.println("Set in get comment by issue id");
		LOG.info("Set in get comment by issue id");
		List<Comment> comments = new ArrayList<Comment>();
		Session session = openSession();
		try {
			session.beginTransaction();
			comments = (List<Comment>) session.createQuery(
				    "from Comment c where c.issue.id = ?")
				   .setInteger(0, issueId).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getHibSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in get comment by issue id " + e);
		}
		closeSession(session);
		return comments;
	}

	@Override
	public boolean addComment(Comment comment)
			throws DaoException {
		boolean isSet = false;
		System.out.println("Set in comment");
		LOG.info("Set in comment");
		Session session = openSession();
		try {
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			HibernateUtil.getHibSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in set comment " + e);
		}
		closeSession(session);
		return isSet;
	}

}
