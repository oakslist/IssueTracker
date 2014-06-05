package org.training.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.constants.ServletConstants;
import org.training.ifaces.AbstractBaseController;
import org.training.ifaces.IIssueDAO;
import org.training.ifaces.ITableDataDAO;
import org.training.ifaces.IUserDAO;
import org.training.model.beans.Issue;
import org.training.model.beans.User;
import org.training.model.factories.IssueFactory;
import org.training.model.factories.TableDataFactory;
import org.training.model.factories.UserFactory;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class BeforeEditIssueController
 */

public class BeforeEditIssueController extends AbstractBaseController {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	
	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			jumpError(ServletConstants.ERROR_NULL_SESSION, request, response);
			return;
		}
		
		int issueEditId = Integer.parseInt(request.getParameter("hidden1"));
		
		// get data from db
		try {
			ITableDataDAO tableDAO = TableDataFactory.getClassFromFactory();
			IIssueDAO issueDAO = IssueFactory.getClassFromFactory();
			Issue issue = issueDAO.getIssue(issueEditId);
			IUserDAO userDAO = UserFactory.getClassFromFactory();
			if (issue.getModifiedById() != 0) {
				User user = userDAO.getUserById(issue.getModifiedById());
				issue.setModifiedBy(user.getFirstName() + " " + user.getLastName());
			}
			User user = userDAO.getUserById(issue.getAssigneeId());
			issue.setAssigneeId(user.getId());
			issue.setAssignee(user.getEmailAddress() + " : " 
					+ user.getFirstName() + " " + user.getLastName());
			
			session.setAttribute(ServletConstants.JSP_ISSUE, issue);
			session.setAttribute(ServletConstants.JSP_TYPES_LIST, 
					tableDAO.getTypes());
			session.setAttribute(ServletConstants.JSP_STATUSES_LIST,
					tableDAO.getStatuses());
			session.setAttribute(ServletConstants.JSP_RESOLUTIONS_LIST,
					tableDAO.getResolutions());
			session.setAttribute(ServletConstants.JSP_PRIORITIES_LIST,
					tableDAO.getPriorities());
			session.setAttribute(ServletConstants.JSP_PROJECTS_LIST,
					tableDAO.getProjects());
			session.setAttribute(ServletConstants.JSP_PROJECT_BUILDS_LIST,
					tableDAO.getBuildFound());
			session.setAttribute(ServletConstants.JSP_ASSIGNEES_LIST,
					tableDAO.getAssignee());
			jumpPage(ServletConstants.JUMP_EDIT_ISSUE_PAGE, request, response);
		} catch (DaoException e) {
			jumpError(e.getMessage(), request, response);
		}

	}

	protected void jump(String url, String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ServletConstants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void jumpPage(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(url, ServletConstants.KEY_EMPTY, request, response);
	}

	protected void jumpError(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(ServletConstants.JUMP_EDIT_ISSUE_PAGE, message, request, response);
	}
	
	
}
