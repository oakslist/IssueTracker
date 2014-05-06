package by.epam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.beans.User;
import by.epam.constants.ServletConstants;
import by.epam.impls.StaxParserReaderUsers;

/**
 * Servlet implementation class CheckUsername
 */
public class CheckUsernameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUsernameController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter(ServletConstants.USERNAME);
		String password = request.getParameter(ServletConstants.PASSWORD);
		
		// get all users from Users.xml
		StaxParserReaderUsers readerUsers = new StaxParserReaderUsers();
		
		HttpSession session= request.getSession(true);
	    session.setAttribute(ServletConstants.USERNAME, username);
	    session.setAttribute(ServletConstants.PASSWORD, password);

		int value = 0;
		
		//TODO create a double submit pattern. Now it works without that pattern
		
		for (User user : readerUsers.getUsersList()) {
			if (user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {
				value++;
				session.setAttribute(ServletConstants.MESSAGE,
						ServletConstants.LOGIN_VALID);
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/welcome.html");
				rd.forward(request, response);
				break;
			}
		}

		if (value == 0) {
			session.setAttribute(ServletConstants.MESSAGE,
					ServletConstants.LOGIN_INVALID);
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
		}
	}

}
