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
import org.training.ifaces.hib.ICommentDAOHib;
import org.training.ifaces.hib.IIssueDAOHib;
import org.training.ifaces.hib.ITableDataDAOHib;
import org.training.model.beans.hibbeans.Comment;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.factories.hib.CommentFactoryHib;
import org.training.model.factories.hib.IssueFactoryHib;
import org.training.model.factories.hib.TableDataFactoryHib;
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
		
		int issueEditId;
		
		if (request.getParameter("hidden1") != null) {
			issueEditId = Integer.parseInt(request.getParameter("hidden1"));
		} else {
			issueEditId = (Integer) session.getAttribute(ServletConstants.JSP_COMMENT_ISSUE_ID);
			session.removeAttribute(ServletConstants.JSP_COMMENT_ISSUE_ID);
		}
		
				
		// get data from db
		try {
			ITableDataDAOHib tableDAO = TableDataFactoryHib.getClassFromFactory();
			IIssueDAOHib issueDAO = IssueFactoryHib.getClassFromFactory();
			Issue issue = issueDAO.getIssueById(issueEditId);
			
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
					tableDAO.getBuildFounds());
			session.setAttribute(ServletConstants.JSP_ASSIGNEES_LIST,
					tableDAO.getUsers());
			//get all comments relate to issue
			ICommentDAOHib commentDAO = CommentFactoryHib.getClassFromFactory();
			List<Comment> comments = new ArrayList<Comment>();
			comments = commentDAO.getExistCommentsByIssueId(issueEditId);
			
			session.setAttribute(ServletConstants.JSP_COMMENT_LIST, comments);
				
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
