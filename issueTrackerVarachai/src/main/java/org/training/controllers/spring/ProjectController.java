package org.training.controllers.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.constants.ServletConstants;
import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.ifaces.ITableDataService;
import org.training.model.hib.ifaces.IUserService;
import org.training.model.impls.DaoException;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

	@Autowired
	private ITableDataService commonService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/all")
	public String showAllProjects(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			model.addAttribute(ServletConstants.JSP_PROJECTS_LIST,
					commonService.getProjects());
			return jumpPage(ServletConstants.PROJECTS_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_PROJECT_PAGE, e.getMessage(),
					model);
		}

	}

	@RequestMapping(value = "/edit")
	public String projectEdit(@RequestParam("hidden3") int editProjectId,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		getEditProject(editProjectId, model);

		return ServletConstants.EDIT_PROJECT_PAGE;
	}

	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
	public String projectChangeSave(
			@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_EDIT_PROJECT_NAME) String projectName,
			@RequestParam(ServletConstants.JSP_EDIT_PROJECT_DESCRIPTION) String projectDescription,
			@RequestParam(ServletConstants.JSP_EDIT_PROJECT_MANAGER) int projectManagerId,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		String inputResult = getInputResultSave(projectName, projectDescription);
		if (inputResult != null) {
			return jump(ServletConstants.EDIT_PROJECT_PAGE, inputResult, model);
		}

		try {

			Project project = getEditProject(id, model);

			if (project.getProjectName().equals(projectName)
					&& project.getDescription().equals(projectDescription)
					&& project.getManager().getUserId() == projectManagerId) {
				getEditProject(id, model);
				return jump(ServletConstants.EDIT_PROJECT_PAGE,
						ServletConstants.PROJECT_EDIT_DATA_EQUAL, model);
			}

			// update project in db
			project.setProjectName(projectName);
			project.setDescription(projectDescription);

			// get user manager from db
			User manager = userService.getUserById(projectManagerId);

			project.setManager(manager);
			boolean isUpdated = commonService.updateProject(project);
			if (isUpdated == true) {
				return jump(ServletConstants.EDIT_PROJECT_PAGE,
						ServletConstants.PROJECT_UPDATE_SUCCESSFULLY, model);
			} else {
				// project not update
				getEditProject(id, model);
				return jump(ServletConstants.EDIT_PROJECT_PAGE,
						ServletConstants.ERROR_PROJECT_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_PROJECT_PAGE, e.getMessage(),
					model);
		}
	}

	@RequestMapping(value = "{id}/build/add")
	public String newProjectsBuildPage(@PathVariable("id") int id,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		getEditProject(id, model);

		return jumpPage(ServletConstants.ADD_PROJECT_BUILD_PAGE, model);
	}

	@RequestMapping(value = "{id}/build/save", method = RequestMethod.POST)
	public String saveNewProjectsBuild(@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_ADD_BUILD) String newBuild,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		String inputResult = getInputResultSaveBuild(newBuild);
		if (inputResult != null) {
			return jump(ServletConstants.ADD_PROJECT_BUILD_PAGE, inputResult,
					model);
		}

		Project project = getEditProject(id, model);

		try {
			// set build to db
			boolean isSet = commonService.setBuild(project, newBuild);
			if (isSet == true) {
				return jump(ServletConstants.ADD_PROJECT_BUILD_PAGE,
						ServletConstants.BUILD_ADD_SUCCESSFULLY, model);
			} else {
				// build not add
				return jump(ServletConstants.ADD_PROJECT_BUILD_PAGE,
						ServletConstants.ERROR_BUILD_NOT_ADD, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.ADD_PROJECT_BUILD_PAGE,
					e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/add")
	public String projectAddNew(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			model.addAttribute(ServletConstants.JSP_ADD_PROJECT_MANAGERS,
					commonService.getUsers());
			return jumpPage(ServletConstants.ADD_NEW_PROJECT_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String projectSave(
			@RequestParam(ServletConstants.JSP_ADD_PROJECT_NAME) String projectName,
			@RequestParam(ServletConstants.JSP_ADD_PROJECT_DESCRIPTION) String projectDescr,
			@RequestParam(ServletConstants.JSP_ADD_PROJECT_BUILD) String projectBuild,
			@RequestParam(ServletConstants.JSP_ADD_PROJECT_MANAGERS) int projectManagerId,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		String inputResult = getInputResultAddNewProject(projectName,
				projectDescr, projectBuild);
		if (inputResult != null) {
			return jump(ServletConstants.ADD_NEW_PROJECT_PAGE, inputResult,
					model);
		}

		try {
			// set project in db
			Project project = new Project();
			project.setProjectName(projectName);
			project.setDescription(projectDescr);
			BuildFound buildFound = new BuildFound();
			buildFound.setBuildValue(projectBuild);
			project.getBuilds().add(buildFound);

			User manager = userService.getUserById(projectManagerId);

			project.setManager(manager);

			boolean isSet = commonService.setProject(project);
			if (isSet == true) {
				return jump(ServletConstants.ADD_NEW_PROJECT_PAGE,
						ServletConstants.PROJECT_UPDATE_SUCCESSFULLY, model);
			} else {
				// type not update
				return jump(ServletConstants.ADD_NEW_PROJECT_PAGE,
						ServletConstants.ERROR_PROJECT_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.ADD_NEW_PROJECT_PAGE, e.getMessage(),
					model);
		}
	}

	private Project getEditProject(int editProjectId, Model model) {

		List<BuildFound> builds = new ArrayList<BuildFound>();
		List<User> managers = new ArrayList<User>();
		Project project = null;
		try {
			// get project from db
			project = commonService.getProjectById(editProjectId);
			model.addAttribute(ServletConstants.JSP_EDIT_PROJECT, project);

			// get project's builds from db
			builds = commonService.getBuildsByProjectId(project.getId());
			model.addAttribute(ServletConstants.JSP_EDIT_PROJECT_BUILDS, builds);

			// get managers from db
			managers = userService.getExistUsers();
			model.addAttribute(ServletConstants.JSP_EDIT_PROJECT_MANAGERS,
					managers);

			jumpPage(ServletConstants.EDIT_PROJECT_PAGE, model);
		} catch (DaoException e) {
			jump(ServletConstants.EDIT_PROJECT_PAGE, e.getMessage(), model);
		}

		return project;
	}

	// jump to the jsp page
	protected String jump(String url, String message, Model model) {
		model.addAttribute(ServletConstants.KEY_ERROR_MESSAGE, message);
		return url;
	}

	// jump to the next valid page
	protected String jumpPage(String url, Model model) {
		return jump(url, ServletConstants.KEY_EMPTY, model);
	}

	private String getInputResultSave(String projectName,
			String projectDescription) {
		if (projectName == null || projectName.equals("")) {
			return ServletConstants.ERROR_PROJECT_NAME_EMPTY;
		}
		if (projectDescription == null || projectDescription.equals("")) {
			return ServletConstants.ERROR_PROJECT_DESCRIPTION_EMPTY;
		}
		return null;
	}

	private String getInputResultSaveBuild(String newBuild) {
		if (newBuild == null || newBuild.equals("")) {
			return ServletConstants.ERROR_ADD_BUILD_EMPTY;
		}
		return null;
	}

	private String getInputResultAddNewProject(String projectName,
			String projectDescr, String projectBuild) {
		if (projectName == null || projectName.equals("")) {
			return ServletConstants.ERROR_ADD_PROJECT_NAME_EMPTY;
		}
		if (projectDescr == null || projectDescr.equals("")) {
			return ServletConstants.ERROR_ADD_PROJECT_DESCR_EMPTY;
		}
		if (projectBuild == null || projectBuild.equals("")) {
			return ServletConstants.ERROR_ADD_PROJECT_BUILD_EMPTY;
		}
		return null;
	}

}
