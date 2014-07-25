package org.training.model.hib.dao.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.hib.dao.impls.ifaces.IIssueDAO;
import org.training.model.impls.DaoException;

@Repository(value="issueDAOImpl")
public class IssueDAOImpl implements IIssueDAO {
	
private static final Logger LOG = Logger.getLogger(CommentDAOImpl.class);
	
	@Autowired
	// (required=false)
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Issue> getAllIssues() throws DaoException {
		System.out.println("get All Issues");
		LOG.info("get All Issues");
		List<Issue> issues = new ArrayList<Issue>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			issues = (List<Issue>) session.createQuery("from Issue").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in get All Issues " + e);
		}
		return issues;
	}

	@SuppressWarnings("unchecked")
	public List<Issue> getUserIssues(int userId) throws DaoException {
		System.out.println("get user Issues by id");
		LOG.info("get user Issues by id");
		List<Issue> list = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			list = (List<Issue>) session.createQuery("from Issue i where i.assignee.userId = ?")
					.setInteger(0, userId).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in get user Issues by id " + e);
		}
		return list;
	}

	public List<Issue> getIssues(int firstNumber, int number)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public Issue getIssueById(int issueId) throws DaoException {
		System.out.println("get issue by id");
		LOG.info("get issue by id");
		Issue issue = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			issue = (Issue) session.createQuery("from Issue i WHERE i.id = ?")
					.setInteger(0, issueId).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in get issue by id " + e);
		}
		return issue;
	}

	public boolean setIssue(Issue issue) throws DaoException {
		boolean isSet = false;
		System.out.println("Add issue");
		LOG.info("Add issue");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(issue);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in create new issue " + e);
		}
		return isSet;
	}

	public boolean updateIssue(Issue issue) throws DaoException {
		boolean isUpdate = false;
		System.out.println("Update issue");
		LOG.info("Update issue");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(issue);
			session.getTransaction().commit();
			isUpdate = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in update issue " + e);
		}
		return isUpdate;
	}

}
