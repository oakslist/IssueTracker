package org.training.model.factories;

import org.training.ifaces.xmlDAO.ITypesDAO;
import org.training.model.impls.xmls.XmlTypesImpl;

public class TypeFactory {

	private static XmlTypesImpl instance;
	
	public static String classesRealPath;
	
	public static ITypesDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static XmlTypesImpl getInstance() {
		if (instance == null) {
			instance = new XmlTypesImpl();
		}
		return instance;
	}
	
}
