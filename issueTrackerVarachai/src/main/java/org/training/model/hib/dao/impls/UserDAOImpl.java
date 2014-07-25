package org.training.model.hib.dao.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.dao.impls.ifaces.IUserDAO;
import org.training.model.hib.impls.UserService;
import org.training.model.impls.DaoException;

@Repository(value="userDAOImpl")
public class UserDAOImpl implements IUserDAO {
	
private static final Logger LOG = Logger.getLogger(UserService.class);
	
	@Autowired
	// (required=false)
	private SessionFactory sessionFactory;

	public User getUserByEmail(String email) {
		User user = new User();
		System.out.println("get user by email " + email);
		LOG.info("get user by email " + email);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			user = (User) session.createQuery(
				    "from User u where u.emailAddress = ?")
				   .setString(0, email)
				   .uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in getting user by email");
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getExistUsers() throws DaoException {
		System.out.println("get all exist users");
		LOG.info("get all exist users");
		List<User> users = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			users = (List<User>) session.createQuery("from User").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in get all exist users");
		}
		return users;
	}

	public User getExistUser(String emailAddress, String password)
			throws DaoException {
		User user = new User();
		System.out.println("get user by email and password " + emailAddress 
				+ "; " + password);
		LOG.info("get user by email and password " + emailAddress 
				+ "; " + password);
		Session session = sessionFactory.getCurrentSession();
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
			session.getTransaction().rollback();
			System.out.println("eror in getting user by email and password");
		}
		return user;
	}

	public User getUserById(int id) throws DaoException {
		User user = new User();
		System.out.println("get user by id " + id);
		LOG.info("get user by id " + id);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			user = (User) session.createQuery(
				    "from User u where u.userId = ?")
				   .setInteger(0, id).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in getting user by id");
		}
		return user;
	}

	public boolean addNewUser(User user) throws DaoException {
		System.out.println("Add new user " + user);
		LOG.info("Add new user " + user);
		boolean isAdded = false;
		Session session = sessionFactory.getCurrentSession();	
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			isAdded = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in add new user");
		}
		return isAdded;
	}

	public boolean updateUser(User user) throws DaoException {
		System.out.println("Update user data " + user);
		LOG.info("Update user data " + user);
		return update(user);
	}

	public boolean updateUserPassword(User user) throws DaoException {
		System.out.println("Update user password " + user);
		LOG.info("Update user password " + user);
		return update(user);
	}

	public boolean update(User user) throws DaoException {
		boolean isUpdate = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
			isUpdate = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in update user data");
		}
		return isUpdate;
	}

}
