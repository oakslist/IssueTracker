package org.training.model.hib.impls;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
import org.training.persistence.HibernateUtil;

public class RoleService {

	private static final Logger LOG = Logger.getLogger(RoleService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}

	public Role get(UserRoleEnum role) {
		Role userRole = null;
		System.out.println("Get role");
		LOG.info("Get role");
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
			System.out.println("eror to get Role");
		}
		closeSession(session);
		return userRole;
	}
}
