package by.epam.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import by.epam.model.factories.UserFactory;
import by.epam.model.impls.DaoException;


/**
 * Servlet implementation class IndexPageController
 */

public class IndexPageController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(IndexPageController.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexPageController() {
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

		LOG.info("START ISSUE TRACKER");
		
		HttpSession session= request.getSession();
		
		User user;		
		if (session.getAttribute(ServletConstants.USER) == null) {
			user = new User ();
			session.setAttribute(ServletConstants.USER, user);
		} else {
			user = (User) session.getAttribute(ServletConstants.USER);
		}
			
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<head><title>LoggingPage</title></head>");
		out.println("<body>");
		out.println("<h1>Hello, my friend!</h1>");
		out.println("<p><font color=&quot#AABBCC&quot>Your role is " 
				+ ((User) session.getAttribute(ServletConstants.USER))
				.getRole().toString() + " now</font></p>");
		if (session.getAttribute(ServletConstants.MESSAGE) != null ) {
			out.println("<p><font color=&qout#ff00FF&quot>You have to log "
					+ "in now</font></p>");
			if (!((User) session.getAttribute(ServletConstants.USER))
						.getUsername().equals(ServletConstants.DEFAULT_GUEST)) {
				User tmpUser = (User) session.getAttribute(ServletConstants.USER);
				out.println("<p>The login &quot" + tmpUser.getUsername() 
						+ "&quot or PASSWORD is invalid!!!</p>");
			}
		} else {
			out.println("<p><font color=&qout#ff00FF&quot>You have to log "
					+ "in now</font></p>");
		}
		out.println("<form method=\"POST\" action=\"CheckUserController\">");

		out.println("<p>username:&nbsp");
		out.println("<input type=\"text\" name=\"" + ServletConstants.USERNAME
				+ "\" size=\"20\" maxlengh=\"20\">");
		out.println("</p>");

		out.println("<p>password:&nbsp");
		out.println("<input type=\"password\" name=\"" + ServletConstants.PASSWORD 
				+ "\" size=\"20\" maxlengh=\"20\">");
		out.println("</p>");

		out.println("<input type=\"submit\" value=\"log in\">");
		
		
		// additional ===========================		
		//just show all users for checking
		// get all users from Users.xml
		List<User> users = new ArrayList<User>();
		IUserDAO userDAO = UserFactory.getClassFromFactory();
		try {
			users = userDAO.getExistUsers();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		int index = 0;
		out.println("<p><br>just show all users in Users.xml for checking: <br>");
		for (User userTmp : users) {
			index++;
			out.println("&nbsp&nbsp id=" + index + ", username=" + userTmp.getUsername()
					+ ", password=" + userTmp.getPassword() + ", role=" 
					+ userTmp.getRole() + ";<br>");
		}
		out.println("</p>");
		// additional ===========================	
		
		out.println("</form>");
		out.println("</body>");
		out.close();
		
	}
	
}
