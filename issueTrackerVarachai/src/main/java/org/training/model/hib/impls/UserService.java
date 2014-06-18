package org.training.model.hib.impls;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.model.beans.hibbeans.User;
import org.training.persistence.HibernateUtil;

public class UserService {

	private static final Logger LOG = Logger.getLogger(UserService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}

	public User add(User user) {
		try {
			System.out.println("Add user");
			LOG.info("Add user");
			Session session = openSession();
			session.getTransaction().begin();
			session.save(user);
			session.getTransaction().commit();
			closeSession(session);
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create new user");
		}
		return user;
	}

}
