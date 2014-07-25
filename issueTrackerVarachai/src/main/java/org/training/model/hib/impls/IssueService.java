package org.training.model.hib.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.hib.dao.impls.ifaces.IIssueDAO;
import org.training.model.hib.ifaces.IIssueService;
import org.training.model.impls.DaoException;

@Service(value="issueService")
public class IssueService implements IIssueService {
	
	@Autowired
	// (required=false)
	private IIssueDAO issueDAOImpl;


	@Override
	@Transactional
	public List<Issue> getAllIssues() throws DaoException {
		return issueDAOImpl.getAllIssues();
	}

	@Override
	@Transactional
	public List<Issue> getUserIssues(int userId) throws DaoException {
		return issueDAOImpl.getUserIssues(userId);
	}

	@Override
	@Transactional
	public List<Issue> getIssues(int firstNumber, int number)
			throws DaoException {
		return issueDAOImpl.getIssues(firstNumber, number);
	}

	@Override
	@Transactional
	public Issue getIssueById(int issueId) throws DaoException {
		return issueDAOImpl.getIssueById(issueId);
	}

	@Override
	@Transactional
	public boolean setIssue(Issue issue) throws DaoException {
		return issueDAOImpl.setIssue(issue);
	}

	@Override
	@Transactional
	public boolean updateIssue(Issue issue) throws DaoException {
		return issueDAOImpl.updateIssue(issue);
	}



}
