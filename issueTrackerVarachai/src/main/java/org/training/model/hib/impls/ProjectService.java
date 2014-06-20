package org.training.model.hib.impls;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.model.beans.hibbeans.Project;
import org.training.persistence.HibernateUtil;

public class ProjectService {

	private static final Logger LOG = Logger.getLogger(ProjectService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}

	public Project add(Project project) {
		System.out.println("Add project");
		LOG.info("Add project");
		Session session = openSession();
		try {
			session.beginTransaction();
			System.out.println("1111 " + session.getTransaction());
			
			//problem here
			session.save(project);

			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create new project " + e);
		}
		closeSession(session);
		return project;
	}
	
	
}
