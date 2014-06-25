package org.training.model.hib.impls;

//http://habrahabr.ru/post/29694/

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.training.constants.ServletConstants;
import org.training.ifaces.xmlDAO.IPrioritiesDAO;
import org.training.ifaces.xmlDAO.IResolutionsDAO;
import org.training.ifaces.xmlDAO.IStatusesDAO;
import org.training.ifaces.xmlDAO.ITypesDAO;
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Role;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.factories.PriorityFactory;
import org.training.model.factories.ResolutionFactory;
import org.training.model.factories.StatusFactory;
import org.training.model.factories.TypeFactory;
import org.training.persistence.HibernateUtil;

public class DefaultDataTables {

	private Map<Integer, String> issueTypes = new HashMap<Integer, String>();
	private Map<Integer, String> issueStatuses = new HashMap<Integer, String>();
	private Map<Integer, String> issueResolutions = new HashMap<Integer, String>();
	private Map<Integer, String> issuePriorities = new HashMap<Integer, String>();
	private List<String> rolesList = new ArrayList<String>();

	private static final Logger LOG = Logger.getLogger(DefaultDataTables.class);

	public DefaultDataTables() {

		try {
			// read all default data from xml
			ITypesDAO typesDAO = TypeFactory.getClassFromFactory();
			issueTypes = typesDAO.getExistTypes();
			IStatusesDAO statusesDAO = StatusFactory.getClassFromFactory();
			issueStatuses = statusesDAO.getExistStatuses();
			IResolutionsDAO resolutionsDAO = ResolutionFactory
					.getClassFromFactory();
			issueResolutions = resolutionsDAO.getExistResolutions();
			IPrioritiesDAO prioritiesDAO = PriorityFactory
					.getClassFromFactory();
			issuePriorities = prioritiesDAO.getExistPriorities();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (ServletConstants.IS_SET_DEFAULT_DATA) {
			readXMLData();
			// set default roles
			if (ifExistDataRole() == false) {
				createDataRole();
			}
			// set default users
			if (ifExistData(User.class.getSimpleName()) == false) {
				createDataUser();
			}
			// set default types
			if (ifExistData(Type.class.getSimpleName()) == false) {
				createData(Type.class.getSimpleName(), issueTypes);
			}
			// set default statuses
			if (ifExistData("Status") == false) {
				createData(Status.class.getSimpleName(), issueStatuses);
			}
			// set default resolution
			if (ifExistData("Resolution") == false) {
				createData(Resolution.class.getSimpleName(), issueResolutions);
			}
			// set default resolution
			if (ifExistData("Priority") == false) {
				createData(Priority.class.getSimpleName(), issuePriorities);
			}
		}

		// set default project build

		// set default project
		if (ifExistData("Project") == false) {
			createDataProject();
		}

		// set default issues
		if (ifExistData("Issue") == false) {
			createDataIssue();
		}

	}

	// read all constant parameters in table
	private void readXMLData() {
		try {
			ITypesDAO typesDAO = TypeFactory.getClassFromFactory();
			issueTypes = typesDAO.getExistTypes();
			IStatusesDAO statusesDAO = StatusFactory.getClassFromFactory();
			issueStatuses = statusesDAO.getExistStatuses();

			// create all tables
			IResolutionsDAO resolutionsDAO = ResolutionFactory
					.getClassFromFactory();
			issueResolutions = resolutionsDAO.getExistResolutions();
			IPrioritiesDAO prioritiesDAO = PriorityFactory
					.getClassFromFactory();
			issuePriorities = prioritiesDAO.getExistPriorities();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			LOG.info(e.getMessage());
		}
	}
	
	private void createDataIssue() {
		try {
			System.out.println("=== Create default data in the Issue ===");
			LOG.info("=== Create default data in the Issue ===");
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			//issue for user
			Issue issue = new Issue();
			issue.setSummary("It's the summary about issue"); 
			issue.setDescription("It's the issue description");
			User assignee = new UserService().getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			issue.setAssignee(assignee);
			User createdUser = new UserService().getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			issue.setCreatedBy(createdUser);
			Calendar calendar = Calendar.getInstance();
			issue.setCreateDate(calendar.getTime());
			
			//find and set data from exist data
			CommonService commonService = new CommonService();
			Status status = commonService.getStatusByName("Assigned");
			issue.setStatus(status);
			
			Type type = commonService.getTypeByName("Bug");
			issue.setType(type);
			
			Priority priority = commonService.getPriorityByName("Important");
			issue.setPriority(priority);
			
			Resolution resolution = commonService.getResolutionByName("Wontfix");
			issue.setResolution(resolution);
			
			Project project = commonService.getProjectByName("MyFirstProject");
			issue.setProject(project);
			
			status.getIssues().add(issue);
			type.getIssues().add(issue);
			priority.getIssues().add(issue);
			resolution.getIssues().add(issue);
			project.getIssues().add(issue);


			IssueService issueService = new IssueService();
			issueService.setIssue(issue);
					
			//issue for admin
			issue = new Issue();
			issue.setSummary("It's the issue for admin"); 
			issue.setDescription("It's the issue for admin description");
			assignee = new UserService().getUserByEmail(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			issue.setAssignee(assignee);
			createdUser = new UserService().getUserByEmail(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			issue.setCreatedBy(createdUser);
			issue.setCreateDate(calendar.getTime());
			
			//find and set data from exist data
			status = commonService.getStatusByName("Assigned");
			issue.setStatus(status);
			
			type = commonService.getTypeByName("Bug");
			issue.setType(type);
			
			priority = commonService.getPriorityByName("Important");
			issue.setPriority(priority);
			
			resolution = commonService.getResolutionByName("Wontfix");
			issue.setResolution(resolution);
			
			project = commonService.getProjectByName("MyFirstProject");
			issue.setProject(project);
			
			status.getIssues().add(issue);
			type.getIssues().add(issue);
			priority.getIssues().add(issue);
			resolution.getIssues().add(issue);
			project.getIssues().add(issue);

			issueService.setIssue(issue);
			
			System.out.println(issue);
			session.close();
			System.out.println("Issue default was saved into the Issue table");
			LOG.info("Issue default was saved into the Issue table");

		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create data Project === " + ex);
		}
	}

	private void createDataRole() {
		try {
			System.out.println("=== Create default data in the trables ===");
			LOG.info("=== Create default data in the trables ===");

			Session session = HibernateUtil.getSessionFactory().openSession();
			
			rolesList.clear();
			for (UserRoleEnum role : UserRoleEnum.values()) {
				rolesList.add(role.getUserRole());
			}

			try {
				for (String curRole : rolesList) {
					session.beginTransaction();
					Role role = new Role();
					role.setRoleName(curRole);
					session.save(role);
					session.getTransaction().commit();
				}

				System.out.println("Role was saved into the ROLE table");
				LOG.info("Role was saved into the ROLE table");
			} catch (HibernateException e) {
				e.printStackTrace();
				System.out.println("eror in role create data");
				session.getTransaction().rollback();
			}
			session.close();
		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create data tables");
		}
	}

	private void createDataProject() {
		try {
			System.out.println("=== Create default data in the Project ===");
			LOG.info("=== Create default data in the Project ===");
			Session session = HibernateUtil.getSessionFactory().openSession();

			//add project1
			BuildFound buildFound = new BuildFound();
			buildFound.setBuildValue("1.0.1");

			Project project = new Project();
			project.setProjectName("MyFirstProject");
			project.setDescription("It's my first project description");

			UserService userService = new UserService();
			User manager = userService
					.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);

			project.setManager(manager);
			project.getBuilds().add(buildFound);

			CommonService commonService = new CommonService();
			commonService.setProject(project);
			
			//add project2
			buildFound = new BuildFound();
			buildFound.setBuildValue("1.0.2");

			project = new Project();
			project.setProjectName("ProjectForAdmin");
			project.setDescription("It's project for administrator");

			manager = userService
					.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);

			project.setManager(manager);
			project.getBuilds().add(buildFound);

			commonService.setProject(project);
			
			session.close();
						
			System.out.println("Project default was saved into the Project table");
			LOG.info("Project default was saved into the Project table");

		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create data Project === " + ex);
		}
	}

	private void createDataUser() {
		try {
			System.out.println("=== Create default data in the user ===");
			LOG.info("=== Create default data in the user ===");

			Session session = HibernateUtil.getSessionFactory().openSession();

			//ad admin
			User userDef = new User();
			userDef.setFirstName(ServletConstants.DEFAULT_ADMIN_FIRST_NAME);
			userDef.setLastName(ServletConstants.DEFAULT_ADMIN_LAST_NAME);
			userDef.setEmailAddress(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			userDef.setPassword(ServletConstants.DEFAULT_ADMIN_PASSWORD);
			Role role = new Role();
			RoleService roleService = new RoleService();
			userDef.setRole(roleService.getExistRole(ServletConstants.DEFAULT_ADMIN_ROLE));

			role.getUsers().add(userDef);

			UserService userService = new UserService();
			userService.addNewUser(userDef);
			
			
			//add user
			userDef = new User();
			userDef.setFirstName(ServletConstants.DEFAULT_USER_FIRST_NAME);
			userDef.setLastName(ServletConstants.DEFAULT_USER_LAST_NAME);
			userDef.setEmailAddress(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			userDef.setPassword(ServletConstants.DEFAULT_USER_PASSWORD);
			role = new Role();
			userDef.setRole(roleService.getExistRole(ServletConstants.DEFAULT_USER_ROLE));

			role.getUsers().add(userDef);

			userService.addNewUser(userDef);

			System.out.println("User default was saved into the USER table");
			LOG.info("User default was saved into the USER table");
			session.close();
		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create data users");
		}
	}

	private void createData(String tableClassName, Map<Integer, String> values) {
		try {
			System.out.println("=== Create default data in the "
					+ tableClassName + " ===");
			LOG.info("=== Create default data in the " + tableClassName
					+ " ===");
			Session session = HibernateUtil.getSessionFactory().openSession();
			if (!values.isEmpty()) {
				session.beginTransaction();
				Collection<String> instances = values.values();
				for (String instance : instances) {
					switch (tableClassName) {
					case "Type":
						Type type = new Type();
						type.setTypeName(instance);
						session.save(type);
						break;
					case "Status":
						Status status = new Status();
						status.setStatusName(instance);
						session.save(status);
						break;
					case "Resolution":
						Resolution resolution = new Resolution();
						resolution.setResolutionName(instance);
						session.save(resolution);
						break;
					case "Priority":
						Priority priority = new Priority();
						priority.setPriorityName(instance);
						session.save(priority);
						break;
					default:
						System.out.println("Class not found");
						break;
					}
				}
				session.getTransaction().commit();
			}
			System.out.println(tableClassName + " default was saved into the "
					+ tableClassName + " table");
			LOG.info(tableClassName + " default was saved into the "
					+ tableClassName + " table");
			session.close();
		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create data " + tableClassName);
		}
	}

	private boolean ifExistData(String tableClassName) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				List list = session.createQuery("from " + tableClassName)
						.list();
				if (!list.isEmpty()) {
					System.out.println(list);
					session.getTransaction().commit();
					session.close();

					return true;
				}
				session.getTransaction().commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			session.close();

		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in checking data data table "
					+ tableClassName);
		}
		return false;
	}

	private boolean ifExistDataRole() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			for (UserRoleEnum role : UserRoleEnum.values()) {
				rolesList.add(role.getUserRole());
			}

			if (rolesList != null) {
				session = HibernateUtil.getSessionFactory().openSession();
				try {
					session.beginTransaction();
					for (String curRole : rolesList) {
						Role role = new Role();
						role.setRoleName(curRole);
						if (session.contains(role)) {
							session.getTransaction().commit();
							session.close();
							return true;
						}
					}
					session.getTransaction().commit();
				} catch (HibernateException e) {
					e.printStackTrace();
					System.out.println("eror in role create data");
					session.getTransaction().rollback();
				}
				session.close();
			}

		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create data tables");
		}
		return false;
	}

//	private List showDataFromTable(String tableClassName) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		List list = session.createQuery("from " + tableClassName).list();
//		System.out.println(list);
//		session.getTransaction().commit();
//		session.close();
//		return list;
//	}

}
