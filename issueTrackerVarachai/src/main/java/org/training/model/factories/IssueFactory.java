package org.training.model.factories;

import org.training.ifaces.IIssueDAO;
import org.training.model.impls.H2IssueImpl;


public class IssueFactory {

	private static H2IssueImpl instance;
	
	public static String classesRealPath;
	
	public static IIssueDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static H2IssueImpl getInstance() {
		if (instance == null) {
			instance = new H2IssueImpl();
		}
		return instance;
	}
}
