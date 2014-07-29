package org.training.model.hib.dao.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
import org.training.model.hib.dao.impls.ifaces.IRoleDAO;
import org.training.model.hib.impls.RoleService;
import org.training.model.impls.DaoException;

@Repository(value="roleDAOImpl")
public class RoleDAOImpl implements IRoleDAO {
	
	private static final Logger LOG = Logger.getLogger(RoleService.class);

	@Autowired
	// (required=false)
	private SessionFactory sessionFactory;
	
	public Role getExistRole(UserRoleEnum role) throws DaoException {
		Role userRole = null;
		System.out.println("Get exist role");
		LOG.info("Get exist role");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.getTransaction().begin();
			userRole = (Role) session.createQuery(
				    "from Role r where r.roleName = ?")
				   .setString(0, role.toString())
				   .uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("error in get exist Role");
		}
		return userRole;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getExistRoles() throws DaoException {
		List<Role> roles = new ArrayList<Role>();
		System.out.println("Get exist roles");
		LOG.info("Get exist roles");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.getTransaction().begin();
			roles = (List<Role>) session.createQuery("from Role").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("error in get exist roles");
		}
		return roles;
	}
	
	public boolean addNewRole(Role role) throws DaoException {
		System.out.println("Add new role " + role);
		LOG.info("Add new role " + role);
		boolean isAdded = false;
		Session session = sessionFactory.getCurrentSession();	
		try {
			session.beginTransaction();
			session.save(role);
			session.getTransaction().commit();
			isAdded = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in add new role");
		}
		return isAdded;
	}
	
	public boolean checkRole(Role role) throws DaoException {
		System.out.println("check " + role);
		LOG.info("check " + role);
		boolean isExist = false;
		Session session = sessionFactory.getCurrentSession();	
		try {
			session.beginTransaction();
			session.save(role);
			session.getTransaction().commit();
			isExist = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in check");
		}
		return isExist;
	}

	@Override
	public Role getRoleByName(String roleName) throws DaoException {
		Role userRole = null;
		System.out.println("Get exist role by Name");
		LOG.info("Get exist role by Name");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.getTransaction().begin();
			userRole = (Role) session.createQuery(
				    "from Role r where r.roleName = ?")
				   .setString(0, roleName)
				   .uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("error in get exist Role by Name");
		}
		return userRole;
	}

}
