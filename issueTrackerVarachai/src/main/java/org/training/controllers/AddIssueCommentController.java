package org.training.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.constants.ServletConstants;
import org.training.ifaces.AbstractBaseController;
import org.training.ifaces.ICommentDAO;
import org.training.model.beans.Comment;
import org.training.model.beans.User;
import org.training.model.factories.CommentFactory;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class AddIssueCommentController
 */

public class AddIssueCommentController extends AbstractBaseController {
	
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
		
		
		String commentStr = request.getParameter(ServletConstants.JSP_COMMENT);
		int issueId = Integer.parseInt(request.getParameter("hidden-issue-id"));
		
		String inputResult = getInputResult(commentStr);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_EDIT_ISSUE_PAGE, inputResult, request, response);
			return;
		}
		
		User user = (User) session.getAttribute(ServletConstants.JSP_USER);
		
		Comment comment = new Comment();
		comment.setAddedById(user.getId());
		comment.setComment(commentStr);
		comment.setIssueId(issueId);
				
		try {
			//set comment in db
			ICommentDAO commentDAO = CommentFactory.getClassFromFactory();
			boolean isSet = commentDAO.setComment(comment);
			session.setAttribute(ServletConstants.JSP_COMMENT_ISSUE_ID, comment.getIssueId());
			if (isSet == true) {
				jumpError(ServletConstants.COMMENT_ADD_SUCCESSFULLY, request, response);
			} else {
				//  user not add
				jumpError(ServletConstants.ERROR_COMMENT_NOT_ADD, request, response);
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
		jump(ServletConstants.JUMP_BEFORE_EDIT_ISSUE_CONTROLLER, message, request, response);
	}
	
	
	private String getInputResult(String comment) {
		if(comment == null || comment.equals("")) {
			return ServletConstants.ERROR_COMMENT_EMPTY;
		}
		return null;
	}

}
