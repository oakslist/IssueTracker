package org.training.model.hib.impls;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.model.beans.hibbeans.Issue;
import org.training.persistence.HibernateUtil;

public class IssueService {

	
	private static final Logger LOG = Logger.getLogger(IssueService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}

	public Issue add(Issue issue) {
		System.out.println("Add issue");
		LOG.info("Add issue");
		Session session = openSession();
		try {
			session.beginTransaction();

			session.save(issue);

			session.getTransaction().commit();
			System.out.println("issue was added : " + issue);
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create new issue " + e);
		}
		closeSession(session);
		return issue;
	}
	
}
