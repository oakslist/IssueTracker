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
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.factories.UserFactory;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class EditUserController
 */
public class EditUserController extends AbstractBaseController {
	
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
		
		
		User user = (User) session.getAttribute(ServletConstants.JSP_USER);
		
		String firstName = request.getParameter(ServletConstants.JSP_FIRST_NAME);
		String lastName = request.getParameter(ServletConstants.JSP_LAST_NAME);
		String emailAddress = request.getParameter(ServletConstants.JSP_EMAIL_ADDRESS);
		
		String inputResult = getInputResult(firstName, lastName, emailAddress);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_EDIT_USER_PAGE, inputResult, request, response);
			return;
		}
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailAddress(emailAddress);
		
		if (user.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
			String role = request.getParameter(ServletConstants.JSP_ROLE);
			user.setRole(UserRoleEnum.valueOf(role));
		}

		
		try {
			//save issue in db
			IUserDAO userDAO = UserFactory.getClassFromFactory();
			boolean isUpdated = userDAO.updateUser(user);
			if (isUpdated == true) {
				jumpError(ServletConstants.USER_UPDATE_SUCCESSFULLY, request, response);
			} else {
				//  user not found
				jumpError(ServletConstants.ERROR_USER_NOT_UPDATE, request, response);
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

	private String getInputResult(String firstName, String lastName, String emailAddress) {
		if(firstName == null || firstName.equals("")) {
			return ServletConstants.ERROR_FIRST_NAME_EMPTY;
		}
		if (lastName == null || lastName.equals("")) {
			return ServletConstants.ERROR_LAST_NAME_EMPTY;
		}
		if (emailAddress == null || emailAddress.equals("")) {
			return ServletConstants.ERROR_EMAIL_EMPTY;
		}
		return null;
	}

}
