package by.epam.model.factories;

import by.epam.ifaces.IUserDAO;
import by.epam.model.impls.XmlUserImpl;


public class UserFactory {
	
	private static XmlUserImpl instance;
	
	public static String classesRealPath;
	
	public static IUserDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static XmlUserImpl getInstance() {
		if (instance == null) {
			instance = new XmlUserImpl();
		}
		return instance;
	}
	
}
