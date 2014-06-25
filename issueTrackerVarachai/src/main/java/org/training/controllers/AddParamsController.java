package org.training.controllers;

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
import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.factories.hib.TableDataFactoryHib;
import org.training.model.factories.hib.UserFactoryHib;
import org.training.model.impls.DaoException;

/**
 * Servlet implementation class AddParamsController
 */

public class AddParamsController extends AbstractBaseController {

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

		String projectName = request.getParameter(ServletConstants.JSP_ADD_PROJECT_NAME);
		String projectDescr = request.getParameter(ServletConstants.JSP_ADD_PROJECT_DESCRIPTION);
		String projectBuild = request.getParameter(ServletConstants.JSP_ADD_PROJECT_BUILD);
		int projectManagerId = Integer.parseInt(request
				.getParameter(ServletConstants.JSP_ADD_PROJECT_MANAGERS));
		String addedType = request.getParameter(ServletConstants.JSP_ADD_TYPE);
		String addedResolution = request.getParameter(ServletConstants.JSP_ADD_RESOLUTION);
		String addedPriority = request.getParameter(ServletConstants.JSP_ADD_PRIORITY);


		String inputResult = getInputResult(projectName, projectDescr, projectBuild,
				addedType, addedResolution, addedPriority);
		if (inputResult != null) {
			jump(ServletConstants.JUMP_ADD_PARAMS_PAGE, inputResult, request,
					response);
			return;
		}
		
		Project project = null;
		Type type = null;
		Resolution resolution = null;
		Priority priority = null;
		
		try {
			ITableDataDAOHib tableDataDAO = TableDataFactoryHib.getClassFromFactory();
			if (!addedType.equals("")) {
				type = new Type();
				type.setTypeName(addedType);
				tableDataDAO.setType(type);
			}
			if (!addedPriority.equals("")) {
				priority = new Priority();
				priority.setPriorityName(addedPriority);
				tableDataDAO.setPriority(priority);
			}
			if (!addedResolution.equals("")) {
				resolution = new Resolution();
				resolution.setResolutionName(addedResolution);
				tableDataDAO.setResolution(resolution);
			}
			if (!projectName.equals("") && !projectDescr.equals("")
					&& !projectBuild.equals("") && projectManagerId != 0) {
				project = new Project();
				project.setProjectName(projectName);
				project.setDescription(projectDescr);
				BuildFound buildFound = new BuildFound();
				buildFound.setBuildValue(projectBuild);
				project.getBuilds().add(buildFound);
				
				IUserDAOHib userDAO = UserFactoryHib.getClassFromFactory();
				User manager = userDAO.getUserById(projectManagerId);

				project.setManager(manager);

				tableDataDAO.setProject(project);
			}
			
			jumpError(ServletConstants.PARAMS_ADD_SUCCESSFULLY, request,
					response);
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
		jump(ServletConstants.JUMP_ADD_PARAMS_PAGE, message,
				request, response);
	}

	private String getInputResult(String projectName, String projectDescr, 
			String projectBuild, String addedType, 
			String addedResolution, String addedPriority) {
		if ((projectName == null || projectName.equals(""))
				&& (projectDescr == null || projectDescr.equals(""))
				&& (projectBuild == null || projectBuild.equals(""))
				&& (addedType == null || addedType.equals(""))
				&& (addedResolution == null || addedResolution.equals(""))
				&& (addedPriority == null || addedPriority.equals(""))) {
			return ServletConstants.ERROR_ADD_PARAMS_EMPTY;
		}
		return null;
	}
}
