package org.training.model.hib.impls;

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
	public List<org.training.model.beans.Issue> getAllIssues()
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<org.training.model.beans.Issue> getUserIssues(int userId)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<org.training.model.beans.Issue> getIssues(int firstNumber,
			int number) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.training.model.beans.Issue getIssue(int number)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setIssue(org.training.model.beans.Issue issue)
			throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateIssue(org.training.model.beans.Issue issue)
			throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
