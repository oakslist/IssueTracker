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
		System.out.println("Add user");
		LOG.info("Add user");
		Session session = openSession();		
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create new user");
		}
		closeSession(session);
		return user;
	}
	
	public User getUserByEmail(String email) {
		User user = new User();
		System.out.println("get user by email");
		LOG.info("get user by email");
		Session session = openSession();
		try {
			session.beginTransaction();
			user = (User) session.createQuery(
				    "from User u where u.emailAddress = ?")
				   .setString(0, email)
				   .uniqueResult();
			//this comment fix transaction error !!!!
			System.out.println(user);			
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in getting user by email");
		}
		closeSession(session);
		return user;
	}

}
