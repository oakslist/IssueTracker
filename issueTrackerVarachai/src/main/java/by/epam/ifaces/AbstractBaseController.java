package by.epam.ifaces;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AbstractBaseController
 */
public abstract class AbstractBaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbstractBaseController() {
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
	
	abstract protected void performTask(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException;

	protected void jump(String url, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	    rd.forward(request, response);
	  }
	
}
