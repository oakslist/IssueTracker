package org.training.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.constants.ServletConstants;
import org.training.ifaces.AbstractBaseController;
import org.training.ifaces.hib.ITableDataDAOHib;
import org.training.model.factories.hib.TableDataFactoryHib;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class BeforeSubmitIssueController
 */
public class BeforeSubmitIssueController extends AbstractBaseController {
	
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
		
		// get data from db
		ITableDataDAOHib tableDAO = TableDataFactoryHib.getClassFromFactory();
		try {
			session.setAttribute(ServletConstants.JSP_TYPES_LIST, tableDAO.getTypes());
			session.setAttribute(ServletConstants.JSP_STATUSES_LIST, tableDAO.getStatuses());
			session.setAttribute(ServletConstants.JSP_RESOLUTIONS_LIST, tableDAO.getResolutions());
			session.setAttribute(ServletConstants.JSP_PRIORITIES_LIST, tableDAO.getPriorities());
			session.setAttribute(ServletConstants.JSP_PROJECTS_LIST, tableDAO.getProjects());
			session.setAttribute(ServletConstants.JSP_PROJECT_BUILDS_LIST, tableDAO.getBuildFounds());
			session.setAttribute(ServletConstants.JSP_ASSIGNEES_LIST, tableDAO.getUsers());
			jumpPage(ServletConstants.JUMP_SUBMIT_ISSUE_PAGE, request, response);
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
		jump(ServletConstants.JUMP_SUBMIT_ISSUE_PAGE, message, request, response);
	}
	
	
}
