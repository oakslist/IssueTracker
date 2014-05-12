package by.epam.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.constants.ServletConstants;
import by.epam.ifaces.AbstractBaseController;
import by.epam.ifaces.IUserDAO;
import by.epam.model.beans.User;
import by.epam.model.beans.UserRole;
import by.epam.model.factories.UserFactory;
import by.epam.model.impls.DaoException;

/**
 * Servlet implementation class CheckUsername
 */
public class CheckUserController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(CheckUserController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUserController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		LOG.info("CheckUserController is working now");
		
		String username = request.getParameter(ServletConstants.USERNAME);
		String password = request.getParameter(ServletConstants.PASSWORD);
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			jumpError(ServletConstants.ERROR_NULL_SESSION, request, response);
			return;
		}
		
   
		// get all users from Users.xml
		List<User> users = new ArrayList<User>();
		IUserDAO userDAO = UserFactory.getClassFromFactory();
		try {
			users = userDAO.getExistUsers();
		} catch (DaoException e) {
			e.printStackTrace();
		}

		int value = 0;
		
		//TODO create a double submit pattern. Now it works without that pattern
		
		for (User user : users) {
			if (user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {
				value++;
				session.setAttribute(ServletConstants.MESSAGE,
						ServletConstants.LOGIN_VALID);
				session.setAttribute(ServletConstants.USER, 
						new User(username, password, user.getRole()));
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/welcome.html");
				rd.forward(request, response);
				break;
			}
		}

		if (value == 0) {
			session.setAttribute(ServletConstants.MESSAGE,
					ServletConstants.LOGIN_INVALID);
			session.setAttribute(ServletConstants.USER, 
					new User(username, password, UserRole.GUEST));
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
		}
	}
	
	protected void jump(String url, String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ServletConstants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void jumpError(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		jump(ServletConstants.JUMP_INDEX_PAGE, message, request, response);
	}

}
