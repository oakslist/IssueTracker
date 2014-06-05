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
import org.training.model.beans.Issue;
import org.training.model.beans.User;
import org.training.model.factories.IssueFactory;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class EditIssueController
 */

public class EditIssueController extends AbstractBaseController {
	
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
		
		int id = Integer.parseInt(request.getParameter(ServletConstants.JSP_ISSUE_ID));
		String summary = request.getParameter(ServletConstants.JSP_SUMMARY);
		String description = request.getParameter(ServletConstants.JSP_DESCRIPTION);
		String status = request.getParameter(ServletConstants.JSP_STATUS);
		String type = request.getParameter(ServletConstants.JSP_TYPE);
		String priority = request.getParameter(ServletConstants.JSP_PRIORITY);
		String resolution = request.getParameter(ServletConstants.JSP_RESOLUTION);
		String project = request.getParameter(ServletConstants.JSP_PROJECT);
		String buildFound = request.getParameter(ServletConstants.JSP_BUILD_FOUND);
		int assigneeId = Integer.parseInt(request.getParameter(ServletConstants.JSP_ASSIGNEE));

		String inputResult = getInputResult(summary, description, status, type, priority,
				project, buildFound);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_SUBMIT_ISSUE_PAGE, inputResult, request, response);
			return;
		}
		
		Issue issue = new Issue();
		issue.setId(id);
		issue.setSummary(summary);
		issue.setDescription(description);
		issue.setStatus(status);
		issue.setType(type);
		issue.setPriority(priority);
		issue.setResolution(resolution);
		issue.setProject(project);
		issue.setBuildFound(buildFound);
		issue.setAssigneeId(assigneeId);
		User user = (User) session.getAttribute(ServletConstants.JSP_USER);
		issue.setModifyDate(new java.sql.Date(System.currentTimeMillis()));
		issue.setModifiedById(user.getId());
		
		try {
			//save issue in db
			IIssueDAO issueDAO = IssueFactory.getClassFromFactory();
			boolean isUpdate = issueDAO.updateIssue(issue);
			if (isUpdate == true) {
				jumpError(ServletConstants.ISSUE_UPDATE_SUCCESSFULLY, request, response);
			} else {
				//  user not found
				jumpError(ServletConstants.ERROR_ISSUE_NOT_UPDATE, request, response);
			}
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
		jump(ServletConstants.JUMP_INDEX_PAGE, message, request, response);
	}
	
	private String getInputResult(String summary, String description, String status, 
			String type, String priority, String project, String buildFound) {
		if(summary == null || summary.equals("")) {
			return ServletConstants.ERROR_SUMMARY_EMPTY;
		}
		if (description == null || description.equals("")) {
			return ServletConstants.ERROR_DESCRIPTION_EMPTY;
		}
		if (status == null || status.equals("")) {
			return ServletConstants.ERROR_STATUS_EMPTY;
		}
		if (type == null || type.equals("")) {
			return ServletConstants.ERROR_STATUS_EMPTY;
		}
		if (priority == null || priority.equals("")) {
			return ServletConstants.ERROR_PRIORITY_EMPTY;
		}
		if (project == null || project.equals("")) {
			return ServletConstants.ERROR_PROJECT_EMPTY;
		}
		if (buildFound == null || buildFound.equals("")) {
			return ServletConstants.ERROR_BUILD_FOUND_EMPTY;
		}
		return null;
	}

}
