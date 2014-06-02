package org.training.model.factories;

import org.training.ifaces.xmlDAO.IPrioritiesDAO;
import org.training.model.impls.xmls.XmlPrioritiesImpl;

public class PriorityFactory {
	
private static XmlPrioritiesImpl instance;
	
	public static String classesRealPath;
	
	public static IPrioritiesDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static XmlPrioritiesImpl getInstance() {
		if (instance == null) {
			instance = new XmlPrioritiesImpl();
		}
		return instance;
	}

}
