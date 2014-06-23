package org.training.model.hib.impls;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.persistence.HibernateUtil;

public class CommonService {

	
	private static final Logger LOG = Logger.getLogger(CommonService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}
	
//	public boolean setNewDataInTable(DefaultTableClass arg) {
//		boolean isSet = false;
//		System.out.println("Add " + arg);
//		LOG.info("Add " + arg);
//		Session session = openSession();
//		try {
//			session.beginTransaction();
//			session.save(arg);
//			session.getTransaction().commit();
//			isSet = true;
//		} catch (Exception e) {
//			HibernateUtil.getSessionFactory().getCurrentSession()
//					.getTransaction().rollback();
//			System.out.println("eror in create new " + arg + " " + e);
//		}
//		closeSession(session);
//		return isSet;
//	}
	
	public boolean setStatus(Status status) {
		boolean isSet = false;
		System.out.println("Set in status");
		LOG.info("Set in status");
		Session session = openSession();
		try {
			session.beginTransaction();
			session.save(status);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in set status " + e);
		}
		closeSession(session);
		return isSet;
	}
	
	public boolean isExist(DefaultTableClass arg) {
		boolean isSet = false;
		System.out.println("Check is exist " + arg);
		LOG.info("Check is exist " + arg);
		Session session = openSession();
		try {
			session.beginTransaction();
			isSet = session.contains(arg);
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in check is exist " + arg + " " + e);
		}
		closeSession(session);
		return isSet;
	}
	
	public DefaultTableClass getRowFromTableById(DefaultTableClass arg, int id) {
		System.out.println("Get data by id from " + arg);
		LOG.info("Get data by id from " + arg);
		Session session = openSession();
		try {
			session.beginTransaction();
			arg = (DefaultTableClass) session.createQuery(
				    "from " + arg.getClass().getSimpleName() + " a where a.id = ?")
				   .setInteger(0, id).uniqueResult();
			if (arg == null) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get data from " + arg + " " + e);
		}
		closeSession(session);
		return arg;
	}
	
	/* only for Status, Resolution, Type, Priority (table with *_name) */
	public Status getStatusByName(String name) {
		System.out.println("Get data by name from status");
		LOG.info("Get data by name from status");
		Status status = new Status();
		Session session = openSession();
		try {
			
//			session.beginTransaction();
//			List list = session.createQuery("from Status").list();
//			System.out.println(list);
//			session.getTransaction().commit();
			
			
			session.beginTransaction();
			status = (Status) session.createQuery(
				    "from Status s where s.statusName = ?")
				   .setString(0, name).uniqueResult();
			if (status == null) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get data from status " + e);
		}
		closeSession(session);
		return status;
	}
	
	public Type getTypeByName(String name) {
		System.out.println("Get data by name from type");
		LOG.info("Get data by name from type");
		Type type = new Type();
		Session session = openSession();
		try {
			session.beginTransaction();
			type = (Type) session.createQuery(
				    "from Type t where t.typeName = ?")
				   .setString(0, name).uniqueResult();
			if (type == null) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get data from type " + e);
		}
		closeSession(session);
		return type;
	}
	
	public Priority getPriorityByName(String name) {
		System.out.println("Get data by name from priority");
		LOG.info("Get data by name from priority");
		Priority priority = new Priority();
		Session session = openSession();
		try {
			session.beginTransaction();
			priority = (Priority) session.createQuery(
				    "from Priority p where p.priorityName = ?")
				   .setString(0, name).uniqueResult();
			if (priority == null) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get data from priority " + e);
		}
		closeSession(session);
		return priority;
	}
	
	public Resolution getResolutionByName(String name) {
		System.out.println("Get data by name from resolution");
		LOG.info("Get data by name from resolution");
		Resolution resolution = new Resolution();
		Session session = openSession();
		try {
			session.beginTransaction();
			resolution = (Resolution) session.createQuery(
				    "from Resolution r where r.resolutionName = ?")
				   .setString(0, name).uniqueResult();
			if (resolution == null) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get data from resolution " + e);
		}
		closeSession(session);
		return resolution;
	}
	
	public Project getProjectByName(String name) {
		System.out.println("Get data by name from project");
		LOG.info("Get data by name from project");
		Project project = new Project();
		Session session = openSession();
		try {
			session.beginTransaction();
			project = (Project) session.createQuery(
				    "from Project p where p.projectName = ?")
				   .setString(0, name).uniqueResult();
			if (project == null) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get data from project " + e);
		}
		closeSession(session);
		return project;
	}
		
}
