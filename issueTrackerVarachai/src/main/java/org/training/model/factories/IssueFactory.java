package org.training.model.factories;

import org.training.ifaces.IIssueDAO;
import org.training.model.impls.DaoException;
import org.training.model.impls.H2IssueImpl;


public class IssueFactory {

	public static IIssueDAO getClassFromFactory() throws DaoException {
		return new H2IssueImpl();
	}
}
