package org.training.model.hib.impls;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.model.beans.hibbeans.Status;
import org.training.persistence.HibernateUtil;

public class CommonService {

	
	private static final Logger LOG = Logger.getLogger(CommonService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}

	public Status getStatus(Status summary) {
		System.out.println("Add project");
		LOG.info("Add project");
		Session session = openSession();
		try {
			session.beginTransaction();

			session.save(summary);

			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create new project " + e);
		}
		closeSession(session);
		return summary;
	}
	
}
