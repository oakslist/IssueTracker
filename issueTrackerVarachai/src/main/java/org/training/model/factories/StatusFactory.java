package org.training.model.factories;

import org.training.ifaces.xmlDAO.IStatusesDAO;
import org.training.model.impls.xmls.XmlStatusesImpl;

public class StatusFactory {

	private static XmlStatusesImpl instance;
	
	public static String classesRealPath;
	
	public static IStatusesDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static XmlStatusesImpl getInstance() {
		if (instance == null) {
			instance = new XmlStatusesImpl();
		}
		return instance;
	}
	
	
}
