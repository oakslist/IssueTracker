package org.training.model.hib.dao.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.dao.impls.ifaces.ICommonDAO;
import org.training.model.hib.impls.DefaultTableClass;
import org.training.model.impls.DaoException;

@Repository(value="commonDAOImpl")
public class CommonDAOImpl implements ICommonDAO {

	private static final Logger LOG = Logger.getLogger(CommentDAOImpl.class);

	@Autowired
	// (required=false)
	private SessionFactory sessionFactory;

	public boolean setStatus(Status status) {
		boolean isSet = false;
		System.out.println("Set in status");
		LOG.info("Set in status");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(status);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in set status " + e);
		}
		return isSet;
	}

	public boolean setType(Type type) throws DaoException {
		boolean isSet = false;
		System.out.println("Set in type");
		LOG.info("Set in type");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(type);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in set type " + e);
		}
		return isSet;
	}

	public boolean setResolution(Resolution resolution) throws DaoException {
		boolean isSet = false;
		System.out.println("Set in resolution");
		LOG.info("Set in resolution");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(resolution);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in set resolution " + e);
		}
		return isSet;
	}

	public boolean setPriority(Priority priority) throws DaoException {
		boolean isSet = false;
		System.out.println("Set in priority");
		LOG.info("Set in priority");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(priority);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in set priority " + e);
		}
		return isSet;
	}

	public boolean setProject(Project project) throws DaoException {
		boolean isSet = false;
		System.out.println("Set in project");
		LOG.info("Set in project");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(project);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Set in project " + e);
		}
		return isSet;
	}

	public boolean isExist(DefaultTableClass arg) {
		boolean isSet = false;
		System.out.println("Check is exist " + arg);
		LOG.info("Check is exist " + arg);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			isSet = session.contains(arg);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in check is exist " + arg + " " + e);
		}
		return isSet;
	}

	public DefaultTableClass getRowFromTableById(DefaultTableClass arg, int id) {
		System.out.println("Get data by id from " + arg);
		LOG.info("Get data by id from " + arg);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			arg = (DefaultTableClass) session
					.createQuery(
							"from " + arg.getClass().getSimpleName()
									+ " a where a.id = ?").setInteger(0, id)
					.uniqueResult();
			if (arg == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data from " + arg + " " + e);
		}
		return arg;
	}

	/* only for Status, Resolution, Type, Priority (table with *_name) */
	public Status getStatusByName(String name) {
		System.out.println("Get data by name from status");
		LOG.info("Get data by name from status");
		Status status = new Status();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			status = (Status) session
					.createQuery("from Status s where s.statusName = ?")
					.setString(0, name).uniqueResult();
			if (status == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data from status " + e);
		}
		return status;
	}

	public Status getStatusById(int statusId) {
		System.out.println("Get data by id from status");
		LOG.info("Get data by id from status");
		Status status = new Status();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			status = (Status) session
					.createQuery("from Status s where s.id = ?")
					.setInteger(0, statusId).uniqueResult();
			if (status == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data by id from status " + e);
		}
		return status;
	}

	public Type getTypeByName(String name) {
		System.out.println("Get data by name from type");
		LOG.info("Get data by name from type");
		Type type = new Type();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			type = (Type) session
					.createQuery("from Type t where t.typeName = ?")
					.setString(0, name).uniqueResult();
			if (type == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data from type " + e);
		}
		return type;
	}

	public Type getTypeById(int typeId) {
		System.out.println("Get data by id from type");
		LOG.info("Get data by id from type");
		Type type = new Type();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			type = (Type) session.createQuery("from Type t where t.id = ?")
					.setInteger(0, typeId).uniqueResult();
			if (type == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data by id from type " + e);
		}
		return type;
	}

	public Priority getPriorityByName(String name) {
		System.out.println("Get data by name from priority");
		LOG.info("Get data by name from priority");
		Priority priority = new Priority();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			priority = (Priority) session
					.createQuery("from Priority p where p.priorityName = ?")
					.setString(0, name).uniqueResult();
			if (priority == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data from priority " + e);
		}
		return priority;
	}

	public Priority getPriorityById(int priorityId) {
		System.out.println("Get data by id from priority");
		LOG.info("Get data by id from priority");
		Priority priority = new Priority();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			priority = (Priority) session
					.createQuery("from Priority p where p.id = ?")
					.setInteger(0, priorityId).uniqueResult();
			if (priority == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data by id from type " + e);
		}
		return priority;
	}

	public Resolution getResolutionByName(String name) {
		System.out.println("Get data by name from resolution");
		LOG.info("Get data by name from resolution");
		Resolution resolution = new Resolution();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			resolution = (Resolution) session
					.createQuery("from Resolution r where r.resolutionName = ?")
					.setString(0, name).uniqueResult();
			if (resolution == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data from resolution " + e);
		}
		return resolution;
	}

	public Resolution getResolutionById(int resolutionId) {
		System.out.println("Get data by id from resolution");
		LOG.info("Get data by id from resolution");
		Resolution resolution = new Resolution();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			resolution = (Resolution) session
					.createQuery("from Resolution r where r.id = ?")
					.setInteger(0, resolutionId).uniqueResult();
			if (resolution == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data by id from resolution " + e);
		}
		return resolution;
	}

	public Project getProjectByName(String name) {
		System.out.println("Get data by name from project");
		LOG.info("Get data by name from project");
		Project project = new Project();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			project = (Project) session
					.createQuery("from Project p where p.projectName = ?")
					.setString(0, name).uniqueResult();
			if (project == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data from project " + e);
		}
		return project;
	}

	public Project getProjectById(int projectId) {
		System.out.println("Get data by id from project");
		LOG.info("Get data by id from project");
		Project project = new Project();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			project = (Project) session
					.createQuery("from Project r where r.id = ?")
					.setInteger(0, projectId).uniqueResult();
			if (project == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get data by id from project " + e);
		}
		return project;
	}

	public Project getProjectByNameAndBuild(String name, String buildValue) {
		System.out.println("Get data by name and build value from project");
		LOG.info("Get data by name and build value from project");
		Project project = new Project();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "SELECT p FROM Project p WHERE p.projectName = ?";
			project = (Project) session.createQuery(sql).setString(0, name)
					.uniqueResult();
			if (project == null) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out
					.println("eror in Get data by name and build value from project "
							+ e);
		}
		return project;
	}

	@SuppressWarnings("unchecked")
	public List<Status> getStatuses() throws DaoException {
		System.out.println("Get all statuses");
		LOG.info("Get all statuses");
		List<Status> statuses = new ArrayList<Status>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			statuses = (List<Status>) session.createQuery("from Status").list();
			if (statuses.isEmpty()) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get all statuses " + e);
		}
		return statuses;
	}

	@SuppressWarnings("unchecked")
	public List<Type> getTypes() throws DaoException {
		System.out.println("Get all types");
		LOG.info("Get all types");
		List<Type> types = new ArrayList<Type>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			types = (List<Type>) session.createQuery("from Type").list();
			if (types.isEmpty()) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get all types " + e);
		}
		return types;
	}

	@SuppressWarnings("unchecked")
	public List<Resolution> getResolutions() throws DaoException {
		System.out.println("Get all resolutions");
		LOG.info("Get all resolutions");
		List<Resolution> resolutions = new ArrayList<Resolution>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			resolutions = (List<Resolution>) session.createQuery(
					"from Resolution").list();
			if (resolutions.isEmpty()) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get all resolutions " + e);
		}
		return resolutions;
	}

	@SuppressWarnings("unchecked")
	public List<Priority> getPriorities() throws DaoException {
		System.out.println("Get all priorities");
		LOG.info("Get all priorities");
		List<Priority> priorities = new ArrayList<Priority>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			priorities = (List<Priority>) session.createQuery("from Priority")
					.list();
			if (priorities.isEmpty()) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get all priorities " + e);
		}
		return priorities;
	}

	@SuppressWarnings("unchecked")
	public List<Project> getProjects() throws DaoException {
		System.out.println("Get all projects");
		LOG.info("Get all projects");
		List<Project> projects = new ArrayList<Project>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			projects = (List<Project>) session.createQuery("from Project")
					.list();
			if (projects.isEmpty()) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get all projects " + e);
		}
		return projects;
	}

	@SuppressWarnings("unchecked")
	public List<BuildFound> getBuildFounds() throws DaoException {
		System.out.println("Get all buildFounds");
		LOG.info("Get all buildFounds");
		List<BuildFound> buildFounds = new ArrayList<BuildFound>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			buildFounds = (List<BuildFound>) session.createQuery(
					"from BuildFound").list();
			if (buildFounds.isEmpty()) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get all buildFounds " + e);
		}
		return buildFounds;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() throws DaoException {
		System.out.println("Get all users");
		LOG.info("Get all users");
		List<User> users = new ArrayList<User>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			users = (List<User>) session.createQuery("from User").list();
			if (users.isEmpty()) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get all users " + e);
		}
		return users;
	}

	public BuildFound getBuildFound(Project project, String build)
			throws DaoException {
		System.out.println("Get project's buildFound");
		LOG.info("Get project's buildFound");
		BuildFound buildFound = new BuildFound();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			buildFound = (BuildFound) session.createQuery("from BuildFound b")
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get project's buildFound " + e);
		}
		return buildFound;

	}

	public boolean updateStatus(Status status) throws DaoException {
		boolean isUpdate = false;
		System.out.println("Update in status");
		LOG.info("Update in status");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(status);
			session.getTransaction().commit();
			isUpdate = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Update status " + e);
		}
		return isUpdate;
	}

	public boolean updateType(Type type) throws DaoException {
		return update(type);
	}

	public boolean updateResolution(Resolution resolution) throws DaoException {
		return update(resolution);
	}

	public boolean updatePriority(Priority priority) throws DaoException {
		return update(priority);
	}

	public boolean updateProject(Project project) throws DaoException {
		return update(project);
	}

	private boolean update(DefaultTableClass temp) throws DaoException {
		boolean isUpdate = false;
		System.out.println("Update in " + temp.getClass().getSimpleName());
		LOG.info("Update in resolution" + temp.getClass().getSimpleName());
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(temp);
			session.getTransaction().commit();
			isUpdate = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Update "
					+ temp.getClass().getSimpleName() + " " + e);
		}
		return isUpdate;
	}

	@SuppressWarnings("unchecked")
	public List<BuildFound> getBuildsByProjectId(int projectId)
			throws DaoException {
		System.out.println("Get all buildFounds by project");
		LOG.info("Get all buildFounds by project");
		List<BuildFound> buildFounds = new ArrayList<BuildFound>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			String sql = "SELECT p.builds FROM Project p WHERE p.id = ?";
			buildFounds = (List<BuildFound>) session.createQuery(sql)
					.setInteger(0, projectId).list();
			if (buildFounds.isEmpty()) {
				session.getTransaction().commit();
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Get all buildFounds by project " + e);
		}
		return buildFounds;
	}

	public boolean setBuild(Project project, String buildValue)
			throws DaoException {
		System.out.println("Set build " + buildValue + " in "
				+ project.getProjectName());
		LOG.info("Set build " + buildValue + " in " + project.getProjectName());
		BuildFound buildFound = new BuildFound();
		buildFound.setBuildValue(buildValue);
		project.getBuilds().add(buildFound);
		boolean isSet = false;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(project);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in Set build " + buildValue + " in "
					+ project.getProjectName() + " " + e);
		}
		return isSet;
	}

}