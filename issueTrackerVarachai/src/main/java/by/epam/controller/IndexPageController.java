package by.epam.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.constants.ServletConstants;

/**
 * Servlet implementation class IndexPageController
 */

public class IndexPageController extends HttpServlet {
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
		
		LOG.info("START ISSUE TRACKER");
		
		HttpSession session= request.getSession(true);
			
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<head><title>LoggingPage</title></head>");
		out.println("<body>");
		out.println("<h1>Hello, my friend!</h1>");
		if (!session.getAttribute(ServletConstants.MESSAGE).toString()
				.equals(ServletConstants.LOGIN_INVALID)) {
			out.println("<p>you have to log in now</p>");
		} else {
			out.println("<p>That login " + session.getAttribute(ServletConstants.USERNAME) 
					+ " or PASSWORD is invalid!!!<br> You have to log in now</p>");
		}
		out.println("<form method=\"POST\" action=\"CheckUsernameController\">");

		out.println("<p>username:&nbsp");
		out.println("<input type=\"text\" name=\"" + ServletConstants.USERNAME
				+ "\" size=\"20\" maxlengh=\"20\">");
		out.println("</p>");

		out.println("<p>password:&nbsp");
		out.println("<input type=\"password\" name=\"" + ServletConstants.PASSWORD 
				+ "\" size=\"20\" maxlengh=\"20\">");
		out.println("</p>");

		out.println("<input type=\"submit\" value=\"log in for project\">");

		out.println("</form>");
		out.println("</body>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
