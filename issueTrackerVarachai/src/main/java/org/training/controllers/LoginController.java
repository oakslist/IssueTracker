package org.training.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.constants.ServletConstants;
import org.training.ifaces.AbstractBaseController;
import org.training.ifaces.IUserDAO;
import org.training.model.beans.User;
import org.training.model.factories.UserFactory;
import org.training.model.impls.DaoException;


/**
 * Servlet implementation class LoginController
 * 
 * That servlet check form for login 
 * Here we get emailAddress and password from user
 *     
 */
public class LoginController extends AbstractBaseController {
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

		String emailAddress = request.getParameter(ServletConstants.JSP_EMAIL_ADDRESS);
		String password = request.getParameter(ServletConstants.JSP_PASSWORD);
		
		String inputResult = getInputResult(emailAddress, password);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_MAIN_PAGE, inputResult, request, response);
			return;
		}
		
		
		//check password and email in bd
		IUserDAO userDAO = UserFactory.getClassFromFactory();
		try {
			// get exist user
			User user = userDAO.getExistUser(emailAddress, password);
			if (user != null) {
				// 	write data in session
				session.setAttribute(ServletConstants.JSP_USER, user);
				// 	jump to main.jsp
				jumpPage(ServletConstants.JUMP_MAIN_PAGE, request, response);
			} else {
				//  user not found
				jumpError(ServletConstants.ERROR_USER_NOT_FOUND, request, response);
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
		jump(ServletConstants.JUMP_MAIN_PAGE, message, request, response);
	}
	
	private String getInputResult(String emailAddress, String password) {
		if(emailAddress == null || emailAddress.equals("")) {
			return ServletConstants.ERROR_EMAIL_EMPTY;
		}
		if (password == null || password.equals("")) {
			return ServletConstants.ERROR_PASSWORD_EMPTY;
		}
		return null;
	}

}
