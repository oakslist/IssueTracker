package org.training.model.hib.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.ifaces.hib.IRoleDAOHib;
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
import org.training.model.impls.DaoException;
import org.training.persistence.HibernateUtil;

public class RoleService implements IRoleDAOHib {

	private static final Logger LOG = Logger.getLogger(RoleService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}

	@Override
	public Role getExistRole(UserRoleEnum role) throws DaoException {
		Role userRole = null;
		System.out.println("Get exist role");
		LOG.info("Get exist role");
		Session session = openSession();
		try {
			session.getTransaction().begin();
			userRole = (Role) session.createQuery(
				    "from Role r where r.roleName = ?")
				   .setString(0, role.toString())
				   .uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("error in get exist Role");
		}
		closeSession(session);
		return userRole;
	}

	@Override
	public List<Role> getExistRoles() throws DaoException {
		List<Role> roles = new ArrayList<Role>();
		System.out.println("Get exist roles");
		LOG.info("Get exist roles");
		Session session = openSession();
		try {
			session.getTransaction().begin();
			roles = (List<Role>) session.createQuery("from Role").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("error in get exist roles");
		}
		closeSession(session);
		return roles;
	}
}
