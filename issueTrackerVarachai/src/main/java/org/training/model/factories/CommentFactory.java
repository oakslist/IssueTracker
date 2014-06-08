package org.training.model.factories;

import org.training.ifaces.ICommentDAO;
import org.training.model.impls.H2CommentImpl;

public class CommentFactory {
	
	private static H2CommentImpl instance;
	
	public static String classesRealPath;
	
	public static ICommentDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static H2CommentImpl getInstance() {
		if (instance == null) {
			instance = new H2CommentImpl();
		}
		return instance;
	}

}
