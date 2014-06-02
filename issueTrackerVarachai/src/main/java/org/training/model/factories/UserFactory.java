package org.training.model.factories;

import org.training.model.impls.H2UserImpl;

import org.training.ifaces.IUserDAO;


public class UserFactory {
	
	private static H2UserImpl instance;
	
	public static String classesRealPath;
	
	public static IUserDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static H2UserImpl getInstance() {
		if (instance == null) {
			instance = new H2UserImpl();
		}
		return instance;
	}
	
}
