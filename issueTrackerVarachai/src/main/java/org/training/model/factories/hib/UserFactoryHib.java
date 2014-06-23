package org.training.model.factories.hib;

import org.training.ifaces.hib.IUserDAOHib;
import org.training.model.hib.impls.UserService;

public class UserFactoryHib {
	
private static UserService instance;
	
	public static String classesRealPath;
	
	public static IUserDAOHib getClassFromFactory() {
		return getInstance();
	}
	
	private static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

}
