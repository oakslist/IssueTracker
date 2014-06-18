package org.training.model.hib.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.training.constants.ServletConstants;
import org.training.ifaces.xmlDAO.IPrioritiesDAO;
import org.training.ifaces.xmlDAO.IResolutionsDAO;
import org.training.ifaces.xmlDAO.IStatusesDAO;
import org.training.ifaces.xmlDAO.ITypesDAO;
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
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
		if (ServletConstants.IS_SET_DEFAULT_DATA) {
			readXMLData();
			if (ifExistDataRole() == false) {
				createDataRole();
			}

			if (ifExistDataUser() == false) {
				createDataUser();
			}
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

	private void createDataRole() {
		try {
			System.out.println("=== Create default data in the trables ===");
			LOG.info("=== Create default data in the trables ===");

			Session session = HibernateUtil.getSessionFactory().openSession();

			for (UserRoleEnum role : UserRoleEnum.values()) {
				rolesList.add(role.getUserRole());
			}

			try {
				session.beginTransaction();
				for (String curRole : rolesList) {
					Role role = new Role();
					role.setRoleName(curRole);
					session.save(role);
				}
				session.getTransaction().commit();

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
	
	private void createDataUser() {
		try {
			System.out.println("=== Create default data in the user ===");
			LOG.info("=== Create default data in the user ===");

			Session session = HibernateUtil.getSessionFactory().openSession();

			User userDef = new User();
			userDef.setFirstName("admin");
			userDef.setLastName("admin");
			userDef.setEmailAddress("ad@ad.ad");
			userDef.setPassword("ad");
			Role role = null;
			RoleService roleService = new RoleService();
			userDef.setRole(roleService.get(UserRoleEnum.ADMINISTRATOR));
			
			role.getUsers().add(userDef);
	
			UserService userService = new UserService();
			userService.add(userDef);
			
			System.out.println("User default was saved into the USER table");
			LOG.info("User default was saved into the USER table");
			session.close();
		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create data users");
		}
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
						session.contains(role);
						return true;
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

	private boolean ifExistDataUser() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();

			try {

				List list = session.createQuery("from User").list();
				System.out.println(list);

				if (!list.isEmpty()) {
					session.close();
					return true;
				}

			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			session.close();

		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("eror in create data tables");
		}
		return false;
	}
}
