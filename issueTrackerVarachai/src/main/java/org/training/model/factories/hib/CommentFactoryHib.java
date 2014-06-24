package org.training.model.factories.hib;

import org.training.ifaces.hib.ICommentDAOHib;
import org.training.ifaces.hib.IIssueDAOHib;
import org.training.model.hib.impls.CommentService;
import org.training.model.hib.impls.IssueService;

public class CommentFactoryHib {
	
private static CommentService instance;
	
	public static String classesRealPath;
	
	public static ICommentDAOHib getClassFromFactory() {
		return getInstance();
	}
	
	private static CommentService getInstance() {
		if (instance == null) {
			instance = new CommentService();
		}
		return instance;
	}

}
