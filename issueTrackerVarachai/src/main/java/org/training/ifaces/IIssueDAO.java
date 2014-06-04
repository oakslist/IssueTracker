package org.training.ifaces;

import java.util.List;

import org.training.model.beans.Issue;
import org.training.model.impls.DaoException;

public interface IIssueDAO {
	public List<Issue> getAllIssues() throws DaoException;
	public List<Issue> getIssues(int firstNumber, int number) throws DaoException;
	public Issue getIssue(int number) throws DaoException;
	public boolean setIssue(Issue issue) throws DaoException;
	public boolean updateIssue(Issue issue) throws DaoException;
}
