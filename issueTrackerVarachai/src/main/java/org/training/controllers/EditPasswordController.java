package org.training.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.constants.ServletConstants;
import org.training.ifaces.AbstractBaseController;
import org.training.ifaces.hib.IUserDAOHib;
import org.training.model.beans.hibbeans.User;
import org.training.model.factories.hib.UserFactoryHib;
import org.training.model.impls.DaoException;


/**
 * Servlet implementation class EditPasswordController
 */

public class EditPasswordController extends AbstractBaseController {
	
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
		
		User editUser = null;
		
		if (session.getAttribute(ServletConstants.JSP_EDIT_PASSWORD_BY_ID) == null) {
			editUser = (User) session.getAttribute(ServletConstants.JSP_USER);
		} else {
			editUser = (User) session.getAttribute(ServletConstants.JSP_EDIT_PASSWORD_BY_ID);
			session.removeAttribute(ServletConstants.JSP_EDIT_PASSWORD_BY_ID);
		}
		
		String password = request.getParameter(ServletConstants.JSP_PASSWORD);
		String passwordConfirmation = request.getParameter(ServletConstants.JSP_PASSWORD_CONFIRMATION);
		
		String inputResult = getInputResult(password, passwordConfirmation);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_EDIT_PASSWORD_PAGE, inputResult, request, response);
			return;
		}
		
		editUser.setPassword(password);
		
		try {
			//save user in db
			IUserDAOHib userDAO = UserFactoryHib.getClassFromFactory();
			boolean isUpdated = userDAO.updateUserPassword(editUser);
			if (isUpdated == true) {
				jumpError(ServletConstants.PASSWORD_UPDATE_SUCCESSFULLY, request, response);
			} else {
				//  password not update
				jumpError(ServletConstants.ERROR_PASSWORD_NOT_UPDATE, request, response);
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

	private String getInputResult(String password, String passwordConfirmation) {
		if(password == null || password.equals("")) {
			return ServletConstants.ERROR_PASSWORD_EMPTY;
		}
		if (passwordConfirmation == null || passwordConfirmation.equals("")) {
			return ServletConstants.ERROR_PASSWORD_CONFIRM_EMPTY;
		}
		if (!password.equals(passwordConfirmation)) {
			return ServletConstants.ERROR_PASSWORDS_NOT_EQUAL;
		}
		return null;
	}

}
