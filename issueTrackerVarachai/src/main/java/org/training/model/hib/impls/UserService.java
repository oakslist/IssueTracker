package org.training.model.hib.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.ifaces.hib.IUserDAOHib;
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
	public List<User> getExistUsers() throws DaoException {
		User user = new User();
		System.out.println("get all exist users");
		LOG.info("get all exist users");
		List<User> users = new ArrayList<User>();
		Session session = openSession();
		try {
			session.beginTransaction();
			users = (List<User>) session.createQuery("from User").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in get all exist users");
		}
		closeSession(session);
		return users;
	}

	@Override
	public User getExistUser(String emailAddress, String password)
			throws DaoException {
		User user = new User();
		System.out.println("get user by email and password");
		LOG.info("get user by email and password");
		Session session = openSession();
		try {
			session.beginTransaction();
			user = (User) session.createQuery(
				    "from User u where u.emailAddress = ? and u.password = ?")
				   .setString(0, emailAddress).setString(1, password)
				   .uniqueResult();
			//this comment fix transaction error !!!!
			System.out.println(user);			
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in getting user by email and password");
		}
		closeSession(session);
		return user;
	}

	@Override
	public User getUserById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewUser(User user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserPassword(User user) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	

}
