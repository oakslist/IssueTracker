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
import org.training.ifaces.hib.IUserDAOHib;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.User;
import org.training.model.factories.hib.TableDataFactoryHib;
import org.training.model.factories.hib.UserFactoryHib;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class EditProjectController
 */

public class EditProjectController extends AbstractBaseController {
	
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
		
		String projectName = request.getParameter(ServletConstants
				.JSP_EDIT_PROJECT_NAME);
		String projectDescription = request.getParameter(ServletConstants
				.JSP_EDIT_PROJECT_DESCRIPTION);
		int projectManagerId = Integer.parseInt(request.getParameter(ServletConstants
				.JSP_EDIT_PROJECT_MANAGER));

		String inputResult = getInputResult(projectName, projectDescription);
		if(inputResult != null) {
			jump(ServletConstants.JUMP_EDIT_PROJECT_PAGE, inputResult, request, response);
			return;
		}
		
		Project project = (Project) session.getAttribute(ServletConstants.JSP_EDIT_PROJECT);
		
		if (project.getProjectName().equals(projectName)
				&& project.getDescription().equals(projectDescription)
				&& project.getManager().getUserId() == projectManagerId) {
			jumpError(ServletConstants.PROJECT_EDIT_DATA_EQUAL, request, response);
			return;
		}
		
		try {
			//update project in db
			ITableDataDAOHib tableDataDAO = TableDataFactoryHib.getClassFromFactory();
			project.setProjectName(projectName);
			project.setDescription(projectDescription);
			
			//get user manager from db
			IUserDAOHib userDAO = UserFactoryHib.getClassFromFactory();
			User manager = userDAO.getUserById(projectManagerId);
			
			project.setManager(manager);
			boolean isUpdated = tableDataDAO.updateProject(project);
			if (isUpdated == true) {
				session.removeAttribute(ServletConstants.JSP_EDIT_PROJECT_NAME);
				session.removeAttribute(ServletConstants.JSP_EDIT_PROJECT_DESCRIPTION);
				session.removeAttribute(ServletConstants.JSP_EDIT_PROJECT_BUILD);
				session.removeAttribute(ServletConstants.JSP_EDIT_PROJECT_MANAGER);
				jumpError(ServletConstants.PROJECT_UPDATE_SUCCESSFULLY, request, response);
			} else {
				//  project not update
				jumpError(ServletConstants.ERROR_PROJECT_NOT_UPDATE, request, response);
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
		jump(ServletConstants.JUMP_MAIN_PAGE, message, request, response);
	}

	private String getInputResult(String projectName, String projectDescription) {
		if(projectName == null || projectName.equals("")) {
			return ServletConstants.ERROR_PROJECT_NAME_EMPTY;
		}
		if(projectDescription == null || projectDescription.equals("")) {
			return ServletConstants.ERROR_PROJECT_DESCRIPTION_EMPTY;
		}
		return null;
	}

}
