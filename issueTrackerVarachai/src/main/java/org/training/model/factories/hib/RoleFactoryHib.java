package org.training.model.factories.hib;

import org.training.ifaces.hib.IRoleDAOHib;
import org.training.model.hib.impls.RoleService;

public class RoleFactoryHib {

	private static RoleService instance;

	public static String classesRealPath;

	public static IRoleDAOHib getClassFromFactory() {
		return getInstance();
	}

	private static RoleService getInstance() {
		if (instance == null) {
			instance = new RoleService();
		}
		return instance;
	}

}
