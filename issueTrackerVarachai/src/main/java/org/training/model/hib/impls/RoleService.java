package org.training.model.hib.impls;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
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
		try {
			System.out.println("Get role");
			LOG.info("Get role");
			Session session = openSession();
			session.getTransaction().begin();
			
			Query query = session.createQuery("from Role where roleName = :code");
			query.setParameter("code", role.toString());
			List list = query.list();
			
			if (!list.isEmpty()) {
				userRole = (Role) list.get(0);
			} else {
				System.out.println("Role not found");
			}
			session.getTransaction().commit();
			closeSession(session);
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror to get Role");
		}
		return userRole;
	}
}
