package org.training.model.factories;

import org.training.ifaces.xmlDAO.IPrivilegesDAO;
import org.training.model.impls.xmls.XmlPrivilegesImpl;

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
