package org.training.model.hib.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.training.ifaces.hib.ITableDataDAOHib;
import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.impls.DaoException;
import org.training.persistence.HibernateUtil;

public class CommonService implements ITableDataDAOHib {

	
	private static final Logger LOG = Logger.getLogger(CommonService.class);

	private Session openSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	private void closeSession(Session session) {
		session.close();
	}
	
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
	
	public Project getProjectByNameAndBuild(String name, String buildValue) {
		System.out.println("Get data by name and build value from project");
		LOG.info("Get data by name and build value from project");
		Project project = new Project();
		Session session = openSession();
		try {
			session.beginTransaction();
			String sql = "SELECT p FROM Project p WHERE p.projectName = ?";
			project = (Project) session.createQuery(sql)
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
			System.out.println("eror in Get data by name and build value from project " + e);
		}
		closeSession(session);
		return project;
	}

	@Override
	public List<Status> getStatuses() throws DaoException {
		System.out.println("Get all statuses");
		LOG.info("Get all statuses");
		List<Status> statuses = new ArrayList<Status>();
		Session session = openSession();
		try {
			session.beginTransaction();
			statuses = (List<Status>) session.createQuery("from Status").list();
			if (statuses.isEmpty()) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get all statuses " + e);
		}
		closeSession(session);
		return statuses;
	}

	@Override
	public List<Type> getTypes() throws DaoException {
		System.out.println("Get all types");
		LOG.info("Get all types");
		List<Type> types = new ArrayList<Type>();
		Session session = openSession();
		try {
			session.beginTransaction();
			types = (List<Type>) session.createQuery("from Type").list();
			if (types.isEmpty()) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get all types " + e);
		}
		closeSession(session);
		return types;
	}

	@Override
	public List<Resolution> getResolutions() throws DaoException {
		System.out.println("Get all resolutions");
		LOG.info("Get all resolutions");
		List<Resolution> resolutions = new ArrayList<Resolution>();
		Session session = openSession();
		try {
			session.beginTransaction();
			resolutions = (List<Resolution>) session.createQuery("from Resolution").list();
			if (resolutions.isEmpty()) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get all resolutions " + e);
		}
		closeSession(session);
		return resolutions;
	}

	@Override
	public List<Priority> getPriorities() throws DaoException {
		System.out.println("Get all priorities");
		LOG.info("Get all priorities");
		List<Priority> priorities = new ArrayList<Priority>();
		Session session = openSession();
		try {
			session.beginTransaction();
			priorities = (List<Priority>) session.createQuery("from Priority").list();
			if (priorities.isEmpty()) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get all priorities " + e);
		}
		closeSession(session);
		return priorities;
	}

	@Override
	public List<Project> getProjects() throws DaoException {
		System.out.println("Get all projects");
		LOG.info("Get all projects");
		List<Project> projects = new ArrayList<Project>();
		Session session = openSession();
		try {
			session.beginTransaction();
			projects = (List<Project>) session.createQuery("from Project").list();
			if (projects.isEmpty()) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get all projects " + e);
		}
		closeSession(session);
		return projects;
	}

	@Override
	public List<BuildFound> getBuildFounds() throws DaoException {
		System.out.println("Get all buildFounds");
		LOG.info("Get all buildFounds");
		List<BuildFound> buildFounds = new ArrayList<BuildFound>();
		Session session = openSession();
		try {
			session.beginTransaction();
			buildFounds = (List<BuildFound>) session.createQuery("from BuildFound").list();
			if (buildFounds.isEmpty()) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get all buildFounds " + e);
		}
		closeSession(session);
		return buildFounds;
	}

	@Override
	public List<User> getAssignee() throws DaoException {
		System.out.println("Get all assignees");
		LOG.info("Get all assignees");
		List<User> assignees = new ArrayList<User>();
		Session session = openSession();
		try {
			session.beginTransaction();
			assignees = (List<User>) session.createQuery("from User").list();
			if (assignees.isEmpty()) {
				session.getTransaction().commit();
				closeSession(session);
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get all assignees " + e);
		}
		closeSession(session);
		return assignees;
	}

	@Override
	public BuildFound getBuildFound(Project project, String build)
			throws DaoException {
		System.out.println("Get project's buildFound");
		LOG.info("Get project's buildFound");
		BuildFound buildFound = new BuildFound();
		Session session = openSession();
		try {
			session.beginTransaction();
			buildFound = (BuildFound) session.createQuery("from BuildFound b").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in Get project's buildFound " + e);
		}
		closeSession(session);
		return buildFound;

	}

	

	
		
}
