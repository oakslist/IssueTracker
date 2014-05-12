package by.epam.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.constants.ServletConstants;
import by.epam.ifaces.AbstractBaseController;
import by.epam.model.beans.User;

/**
 * Servlet implementation class SignInResultPageController
 */
public class SignInResultPageController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(SignInResultPageController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignInResultPageController() {
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
				
		LOG.info("SignInResultPageController is working now");
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			jumpError(ServletConstants.ERROR_NULL_SESSION, request, response);
			return;
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<head><title>LoggingPage</title></head>");
		out.println("<body>");
		out.println("<h1>Hello, " + ((User) session
				.getAttribute(ServletConstants.USER)).getUsername() + "!</h1>");
		out.println("<p>Welcome to the IssueTracker!</p>");
		out.println("<p><font color=&quot#AABBCC&quot>Your role is " 
				+ ((User) session.getAttribute(ServletConstants.USER))
				.getRole().toString() + " now</font></p>");
		
		out.println("<p><br><br> Some links and working context should "
				+ "be here in future</p>");
		
		out.println("</body>");
		out.close();
		
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
