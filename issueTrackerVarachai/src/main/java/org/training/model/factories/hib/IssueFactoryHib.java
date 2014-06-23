package org.training.model.factories.hib;

import org.training.ifaces.hib.IIssueDAOHib;
import org.training.model.hib.impls.IssueService;

public class IssueFactoryHib {
	
private static IssueService instance;
	
	public static String classesRealPath;
	
	public static IIssueDAOHib getClassFromFactory() {
		return getInstance();
	}
	
	private static IssueService getInstance() {
		if (instance == null) {
			instance = new IssueService();
		}
		return instance;
	}

}
