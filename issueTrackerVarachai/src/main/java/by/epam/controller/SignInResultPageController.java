package by.epam.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.constants.ServletConstants;

/**
 * Servlet implementation class SignInResultPageController
 */
public class SignInResultPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		HttpSession session= request.getSession(true);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<head><title>LoggingPage</title></head>");
		out.println("<body>");
		out.println("<h1>Hello, " 
				+ session.getAttribute(ServletConstants.USERNAME) + "!</h1>");
		out.println("<p>Welcome to the IssueTracker!</p>");
		
		out.println("<p><br><br> Some links and working context should be here in future</p>");
		
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
