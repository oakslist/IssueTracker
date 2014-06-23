package org.training.model.hib.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.ifaces.hib.IIssueDAOHib;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.impls.DaoException;
import org.training.persistence.HibernateUtil;

public class IssueService implements IIssueDAOHib {

	
	private static final Logger LOG = Logger.getLogger(IssueService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}
	
	public boolean addIssue(Issue issue) {
		boolean isSet = false;
		System.out.println("Add issue");
		LOG.info("Add issue");
		Session session = openSession();
		try {
			session.beginTransaction();
			session.save(issue);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create new issue " + e);
		}
		closeSession(session);
		return isSet;
	}

	@Override
	public List<Issue> getAllIssues() throws DaoException {
		System.out.println("get All Issues");
		LOG.info("get All Issues");
		List<Issue> issues = new ArrayList<Issue>();
		Session session = openSession();
		try {
			session.beginTransaction();
			issues = (List<Issue>) session.createQuery("from Issue").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in get All Issues " + e);
		}
		closeSession(session);
		return issues;
	}

	@Override
	public List<Issue> getUserIssues(int userId) throws DaoException {
		System.out.println("get user Issues by id");
		LOG.info("get user Issues by id");
		System.out.println("userId = " + userId);
		List<Issue> list = null;
		Session session = openSession();
		try {
			session.beginTransaction();
			list = (List<Issue>) session.createQuery("from Issue i where i.assignee.userId = ?")
					.setInteger(0, userId).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in get user Issues by id " + e);
		}
		closeSession(session);
		return list;
	}

	@Override
	public List<Issue> getIssues(int firstNumber, int number)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issue getIssue(int number) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setIssue(Issue issue) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateIssue(Issue issue) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}



}
