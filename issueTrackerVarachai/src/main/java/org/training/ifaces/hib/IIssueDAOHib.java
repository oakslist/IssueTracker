package org.training.ifaces.hib;

import java.util.List;

import org.training.model.beans.hibbeans.Issue;
import org.training.model.impls.DaoException;

public interface IIssueDAOHib {
	
	public List<Issue> getAllIssues() throws DaoException;
	public List<Issue> getUserIssues(int userId) throws DaoException;
	public List<Issue> getIssues(int firstNumber, int number) throws DaoException;
	public Issue getIssueById(int issueId) throws DaoException;
	public boolean setIssue(Issue issue) throws DaoException;
	public boolean updateIssue(Issue issue) throws DaoException;

}
