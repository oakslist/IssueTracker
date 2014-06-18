package org.training.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.factories.IssueFactory;
import org.training.model.hib.impls.DefaultDataTables;
import org.training.model.impls.DaoException;


/**
 * Servlet implementation class SessionController
 */
public class SessionController extends AbstractBaseController {
	
	private static final long serialVersionUID = 1L;
	
	private static boolean isTableNotCreated = true;
       
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
		
		HttpSession session= request.getSession(false);
		
		if (session == null) {
			session= request.getSession(true);
			session.setAttribute(ServletConstants.JSP_USER, new User());
			return;
		}
		
		//create default data in the tables
		if (isTableNotCreated) {
			new DefaultDataTables();
			isTableNotCreated = false;
		}
		
		// get all issues from db
		List<Issue> issuesList = new ArrayList<Issue>();
		try {
			IIssueDAO issueDAO = IssueFactory.getClassFromFactory();
			User curUser = (User) session.getAttribute(ServletConstants.JSP_USER);
			if (curUser == null || curUser.getRole().equals(UserRoleEnum.GUEST)) {
				issuesList = issueDAO.getAllIssues();
			} else { 
				if (curUser.getRole().equals(UserRoleEnum.USER) 
						|| curUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) { 
					issuesList = issueDAO.getUserIssues(curUser.getId());
				}
			}
			//write data in session
			session.setAttribute(ServletConstants.JSP_ISSUES_LIST, issuesList);
		} catch (DaoException e) {
			jumpError(e.getMessage(), request, response);
		}

		jumpPage(ServletConstants.JUMP_MAIN_PAGE, request, response);
	}

	
	// jump to the jsp page
	protected void jump(String url, String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ServletConstants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	// jump to the next valid page
	protected void jumpPage(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(url, ServletConstants.KEY_EMPTY, request, response);
	}
	
	protected void jumpError(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(ServletConstants.JUMP_MAIN_PAGE, message, request, response);
	}
	
}
