package org.training.model.hib.impls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import org.training.model.hib.dao.impls.ifaces.ICommentDAO;
import org.training.model.hib.dao.impls.ifaces.ICommonDAO;
import org.training.model.hib.dao.impls.ifaces.IIssueDAO;
import org.training.model.hib.dao.impls.ifaces.IRoleDAO;
import org.training.model.hib.dao.impls.ifaces.IUserDAO;
import org.training.model.hib.ifaces.IDefaultDataTables;
import org.training.model.impls.DaoException;

@Service(value="defaultDataTables")
public class DefaultDataTables implements IDefaultDataTables {

	private Map<Integer, String> issueTypes = new HashMap<Integer, String>();
	private Map<Integer, String> issueStatuses = new HashMap<Integer, String>();
	private Map<Integer, String> issueResolutions = new HashMap<Integer, String>();
	private Map<Integer, String> issuePriorities = new HashMap<Integer, String>();
	private List<String> rolesList = new ArrayList<String>();

	private static final Logger LOG = Logger.getLogger(DefaultDataTables.class);

	@Autowired
	// (required=false)
	private IUserDAO userDAOImpl;

	@Autowired
	// (required=false)
	private ICommentDAO commentDAOImpl;

	@Autowired
	// (required=false)
	private ICommonDAO commonDAOImpl;

	@Autowired
	// (required=false)
	private IIssueDAO issueDAOImpl;

	@Autowired
	// (required=false)
	private IRoleDAO roleDAOImpl;

	public DefaultDataTables() {
	}

	public void init() {
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

	@Transactional
	private void createDataIssue() {
		// Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println("=== Create default data in the Issue ===");
			LOG.info("=== Create default data in the Issue ===");

			// issue for user
			Issue issue = new Issue();
			issue.setSummary("It's the summary about issue");
			issue.setDescription("It's the issue description");
			User assignee = userDAOImpl.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			issue.setAssignee(assignee);
			User createdUser = userDAOImpl.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			issue.setCreatedBy(createdUser);
			Calendar calendar = Calendar.getInstance();
			issue.setCreateDate(calendar.getTime());
			
			// find and set data from exist data
//			CommonService commonService = new CommonService();
			Status status = commonDAOImpl.getStatusByName("Assigned");
			issue.setStatus(status);
			Type type = commonDAOImpl.getTypeByName("Bug");
			issue.setType(type);

			Priority priority = commonDAOImpl.getPriorityByName("Important");
			issue.setPriority(priority);

			Resolution resolution = commonDAOImpl
					.getResolutionByName("Wontfix");
			issue.setResolution(resolution);

			Project project = commonDAOImpl.getProjectByName("MyFirstProject");
			issue.setProject(project);

			status.getIssues().add(issue);
			type.getIssues().add(issue);
			priority.getIssues().add(issue);
			resolution.getIssues().add(issue);
			project.getIssues().add(issue);
//			IssueService issueService = new IssueService();
			issueDAOImpl.setIssue(issue);

			// issue for admin
			issue = new Issue();
			issue.setSummary("It's the issue for admin");
			issue.setDescription("It's the issue for admin description");
			assignee = userDAOImpl.getUserByEmail(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			issue.setAssignee(assignee);
			createdUser = userDAOImpl.getUserByEmail(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			issue.setCreatedBy(createdUser);
			issue.setCreateDate(calendar.getTime());

			// find and set data from exist data
			status = commonDAOImpl.getStatusByName("Assigned");
			issue.setStatus(status);

			type = commonDAOImpl.getTypeByName("Bug");
			issue.setType(type);

			priority = commonDAOImpl.getPriorityByName("Important");
			issue.setPriority(priority);

			resolution = commonDAOImpl.getResolutionByName("Wontfix");
			issue.setResolution(resolution);

			project = commonDAOImpl.getProjectByName("MyFirstProject");
			issue.setProject(project);

			status.getIssues().add(issue);
			type.getIssues().add(issue);
			priority.getIssues().add(issue);
			resolution.getIssues().add(issue);
			project.getIssues().add(issue);

			issueDAOImpl.setIssue(issue);

			System.out.println(issue);

			System.out.println("Issue default was saved into the Issue table");
			LOG.info("Issue default was saved into the Issue table");

		} catch (Exception ex) {
			// session.getTransaction().rollback();
			System.out.println("eror in create data Project === " + ex);
			System.out.println("eror in create data Project here");
		}
	}

	@Transactional
	private void createDataRole() {
		System.out.println("=== Create default data in the tables ===");
		LOG.info("=== Create default data in the tables ===");
		rolesList.clear();
		for (UserRoleEnum role : UserRoleEnum.values()) {
			rolesList.add(role.getUserRole());
		}
		try {
			for (String curRole : rolesList) {
				Role role = new Role();
				role.setRoleName(curRole);
				roleDAOImpl.addNewRole(role);
			}
			System.out.println("Role was saved into the ROLE table");
			LOG.info("Role was saved into the ROLE table");
		} catch (DaoException e) {
			e.printStackTrace();
			System.out.println("eror in role create data");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("eror in role create data");
		}
	}

	@Transactional
	private void createDataProject() {
		// Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println("=== Create default data in the Project ===");
			LOG.info("=== Create default data in the Project ===");

			// add project1
			BuildFound buildFound = new BuildFound();
			buildFound.setBuildValue("1.0.1");

			Project project = new Project();
			project.setProjectName("MyFirstProject");
			project.setDescription("It's my first project description");

//			UserService userService = new UserService();
			User manager = userDAOImpl
					.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);

			project.setManager(manager);
			project.getBuilds().add(buildFound);

//			CommonService commonService = new CommonService();
			commonDAOImpl.setProject(project);

			// add project2
			buildFound = new BuildFound();
			buildFound.setBuildValue("1.0.2");

			project = new Project();
			project.setProjectName("ProjectForAdmin");
			project.setDescription("It's project for administrator");

			manager = userDAOImpl
					.getUserByEmail(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);

			project.setManager(manager);
			project.getBuilds().add(buildFound);

			commonDAOImpl.setProject(project);

			System.out
					.println("Project default was saved into the Project table");
			LOG.info("Project default was saved into the Project table");

		} catch (Exception ex) {
			// session.getTransaction().rollback();
			System.out.println("eror in create data Project === " + ex);
		}
	}

	@Transactional
	private void createDataUser() {
		// Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println("=== Create default data in the user ===");
			LOG.info("=== Create default data in the user ===");

			// ad admin
			User userDef = new User();
			userDef.setFirstName(ServletConstants.DEFAULT_ADMIN_FIRST_NAME);
			userDef.setLastName(ServletConstants.DEFAULT_ADMIN_LAST_NAME);
			userDef.setEmailAddress(ServletConstants.DEFAULT_ADMIN_EMAIL_ADDRESS);
			userDef.setPassword(ServletConstants.DEFAULT_ADMIN_PASSWORD);
			Role role = new Role();
//			RoleService roleService = new RoleService();
			userDef.setRole(roleDAOImpl
					.getExistRole(ServletConstants.DEFAULT_ADMIN_ROLE));

			role.getUsers().add(userDef);

//			UserService userService = new UserService();
			userDAOImpl.addNewUser(userDef);

			// add user
			userDef = new User();
			userDef.setFirstName(ServletConstants.DEFAULT_USER_FIRST_NAME);
			userDef.setLastName(ServletConstants.DEFAULT_USER_LAST_NAME);
			userDef.setEmailAddress(ServletConstants.DEFAULT_USER_EMAIL_ADDRESS);
			userDef.setPassword(ServletConstants.DEFAULT_USER_PASSWORD);
			role = new Role();
			userDef.setRole(roleDAOImpl
					.getExistRole(ServletConstants.DEFAULT_USER_ROLE));

			role.getUsers().add(userDef);

			userDAOImpl.addNewUser(userDef);

			System.out.println("User default was saved into the USER table");
			LOG.info("User default was saved into the USER table");

		} catch (Exception ex) {
			// session.getTransaction().rollback();
			System.out.println("eror in create data users");
		}
	}

	@Transactional
	private void createData(String tableClassName, Map<Integer, String> values) {
		System.out.println("=== Create default data in the " + tableClassName
				+ " ===");
		LOG.info("=== Create default data in the " + tableClassName + " ===");
		if (!values.isEmpty()) {
			Collection<String> instances = values.values();
			try {
				for (String instance : instances) {
					switch (tableClassName) {
					case "Type":
						Type type = new Type();
						type.setTypeName(instance);
						commonDAOImpl.setType(type);
						break;
					case "Status":
						Status status = new Status();
						status.setStatusName(instance);
						commonDAOImpl.setStatus(status);
						break;
					case "Resolution":
						Resolution resolution = new Resolution();
						resolution.setResolutionName(instance);
						commonDAOImpl.setResolution(resolution);
						break;
					case "Priority":
						Priority priority = new Priority();
						priority.setPriorityName(instance);
						commonDAOImpl.setPriority(priority);
						break;
					default:
						System.out.println("Class not found");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println(tableClassName + " default was saved into the "
				+ tableClassName + " table");
		LOG.info(tableClassName + " default was saved into the "
				+ tableClassName + " table");
	}

	@Transactional
	private boolean ifExistData(String tableClassName) {
//		try {
//			session.beginTransaction();
//			List list = session.createQuery("from " + tableClassName).list();
//			if (!list.isEmpty()) {
//				System.out.println(list);
//				session.getTransaction().commit();
//
//				return true;
//			}
//		} catch (Exception ex) {
//			System.out.println("eror in checking data data table "
//					+ tableClassName);
//		}
		return false;
	}

	@Transactional
	private boolean ifExistDataRole() {
//		for (UserRoleEnum role : UserRoleEnum.values()) {
//			rolesList.add(role.getUserRole());
//		}
//		if (rolesList != null) {
//			try {
//				for (String curRole : rolesList) {
//					Role role = new Role();
//					role.setRoleName(curRole);
//					if (roleDAOImpl.checkRole(role)) {
//						return true;
//					}
//				}
//			} catch (DaoException e) {
//				e.printStackTrace();
//				System.out.println("eror in role create data");
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("eror in role create data");
//			}
//		}
		return false;
	}

}
