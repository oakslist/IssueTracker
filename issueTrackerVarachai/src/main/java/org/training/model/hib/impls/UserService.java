package org.training.model.hib.impls;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.ifaces.hib.IUserDAOHib;
import org.training.model.beans.Issue;
import org.training.model.beans.hibbeans.User;
import org.training.model.impls.DaoException;
import org.training.persistence.HibernateUtil;

public class UserService implements IUserDAOHib {

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

	@Override
	public List<Issue> getAllIssues() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issue> getUserIssues(int userId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
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
