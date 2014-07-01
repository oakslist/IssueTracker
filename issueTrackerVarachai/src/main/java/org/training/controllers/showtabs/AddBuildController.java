package org.training.controllers.showtabs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.constants.ServletConstants;
import org.training.ifaces.AbstractBaseController;
import org.training.ifaces.hib.ITableDataDAOHib;
import org.training.model.beans.hibbeans.Project;
import org.training.model.factories.hib.TableDataFactoryHib;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class AddBuildController
 */

public class AddBuildController extends AbstractBaseController {
	
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
		
		String newBuild = request.getParameter(ServletConstants.JSP_ADD_BUILD);
		
		String inputResult = getInputResult(newBuild);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_ADD_BUILD_PAGE, inputResult, request, response);
			return;
		}
		
		Project project = (Project) session.getAttribute(ServletConstants.JSP_EDIT_PROJECT);
		
		try {
			//set build to db
			ITableDataDAOHib tableDataDAO = TableDataFactoryHib.getClassFromFactory();
			boolean isSet = tableDataDAO.setBuild(project, newBuild);
			if (isSet == true) {
				jumpError(ServletConstants.BUILD_ADD_SUCCESSFULLY, request, response);
			} else {
				// build not add
				jumpError(ServletConstants.ERROR_BUILD_NOT_ADD, request, response);
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
		jump(ServletConstants.JUMP_BEFORE_EDIT_PROJECT_PAGE, message, request, response);
	}
	
	
	private String getInputResult(String newBuild) {
		if(newBuild == null || newBuild.equals("")) {
			return ServletConstants.ERROR_ADD_BUILD_EMPTY;
		}
		return null;
	}

}
