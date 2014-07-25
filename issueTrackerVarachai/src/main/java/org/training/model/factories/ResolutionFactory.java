package org.training.model.factories;

import org.training.ifaces.xmlDAO.IResolutionsDAO;
import org.training.model.impls.xmls.XmlResolutionsImpl;

public class ResolutionFactory {

	private static XmlResolutionsImpl instance;
	
	public static String classesRealPath;
	
	public static IResolutionsDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static XmlResolutionsImpl getInstance() {
		if (instance == null) {
			instance = new XmlResolutionsImpl();
		}
		return instance;
	}
	
}
