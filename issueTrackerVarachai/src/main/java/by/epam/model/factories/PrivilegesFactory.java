package by.epam.model.factories;

import by.epam.ifaces.IPrivilegesDAO;
import by.epam.model.impls.XmlPrivilegesImpl;

public class PrivilegesFactory {
	
	private static XmlPrivilegesImpl instance;
	
	public static String classesRealPath;
	
	public static IPrivilegesDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static XmlPrivilegesImpl getInstance() {
		if (instance == null) {
			instance = new XmlPrivilegesImpl();
		}
		return instance;
	}
}
