package org.training.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.constants.ServletConstants;
import org.training.ifaces.AbstractBaseController;
import org.training.ifaces.hib.IIssueDAOHib;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.factories.hib.IssueFactoryHib;
import org.training.model.hib.impls.CommonService;
import org.training.model.hib.impls.IssueService;
import org.training.model.hib.impls.UserService;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class SubmitIssueController
 */
public class SubmitIssueController extends AbstractBaseController {

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

		String summary = request.getParameter(ServletConstants.JSP_SUMMARY);
		String description = request
				.getParameter(ServletConstants.JSP_DESCRIPTION);
		String status = request.getParameter(ServletConstants.JSP_STATUS);
		String type = request.getParameter(ServletConstants.JSP_TYPE);
		String priority = request.getParameter(ServletConstants.JSP_PRIORITY);
		String project = request.getParameter(ServletConstants.JSP_PROJECT);
		String buildFound = request
				.getParameter(ServletConstants.JSP_BUILD_FOUND);
		int assignee = Integer.parseInt(request
				.getParameter(ServletConstants.JSP_ASSIGNEE));
		
		try {
			// set issue
			Issue issue = new Issue();

			issue.setSummary(summary);
			issue.setDescription(description);
			User curAssignee = new UserService().getUserById(assignee);
			issue.setAssignee(curAssignee);
			User createdBy = (User) session.getAttribute(ServletConstants.JSP_USER);
			User createdUser = new UserService().getUserById(createdBy.getUserId());
			issue.setCreatedBy(createdUser);
			Calendar calendar = Calendar.getInstance();
			issue.setCreateDate(calendar.getTime());

			CommonService commonService = new CommonService();
			Type curType = commonService.getTypeByName(type);
			issue.setType(curType);

			Status curStatus = commonService.getStatusByName(status);
			issue.setStatus(curStatus);

			Priority curPriority = commonService.getPriorityByName(priority);
			issue.setPriority(curPriority);

			Project curProject = commonService.getProjectByNameAndBuild(
					project, buildFound);
			issue.setProject(curProject);
			
			User user = (User) session.getAttribute(ServletConstants.JSP_USER);
			if (user != null) {
				User curCreatedBy = new UserService().getUserById(user
						.getUserId());
			}
			
			curStatus.getIssues().add(issue);
			curType.getIssues().add(issue);
			curPriority.getIssues().add(issue);
			curProject.getIssues().add(issue);

			// set issue in db
			IIssueDAOHib issueDAO = IssueFactoryHib.getClassFromFactory();
			// set issue
			boolean isSet = issueDAO.setIssue(issue);
			if (isSet == true) {
				jumpError(ServletConstants.ISSUE_ADD_SUCCESSFULLY, request,
						response);
			} else {
				// user not found
				jumpError(ServletConstants.ERROR_ISSUE_NOT_ADD, request,
						response);
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

}
