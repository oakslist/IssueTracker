package org.training.controllers.spring;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.training.model.beans.hibbeans.Comment;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.ifaces.ICommentService;
import org.training.model.hib.ifaces.IIssueService;
import org.training.model.hib.ifaces.ITableDataService;
import org.training.model.hib.ifaces.IUserService;
import org.training.model.impls.DaoException;

@Controller
@RequestMapping(value = "/issue")
public class IssueController {

	@Autowired
	private IIssueService issueService;

	@Autowired
	private ITableDataService commonService;

	@Autowired
	private IUserService userService;

	@Autowired
	private ICommentService commentService;

	@RequestMapping(value = "/all")
	public String allIssuesPage(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			session.setAttribute(ServletConstants.JSP_ALL_ISSUES_LIST,
					issueService.getAllIssues());

			jumpPage(ServletConstants.ALL_ISSUES_PAGE, model);
		} catch (DaoException e) {
			return jumpError(e.getMessage(), model);
		}

		return ServletConstants.ALL_ISSUES_PAGE;
	}

	@RequestMapping(value = "/add")
	public String addIssue(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			model.addAttribute(ServletConstants.JSP_TYPES_LIST,
					commonService.getTypes());
			model.addAttribute(ServletConstants.JSP_STATUSES_LIST,
					commonService.getStatuses());
			model.addAttribute(ServletConstants.JSP_RESOLUTIONS_LIST,
					commonService.getResolutions());
			model.addAttribute(ServletConstants.JSP_PRIORITIES_LIST,
					commonService.getPriorities());
			model.addAttribute(ServletConstants.JSP_PROJECT_BUILDS_LIST,
					commonService.getBuildFounds());
			model.addAttribute(ServletConstants.JSP_ASSIGNEES_LIST,
					commonService.getUsers());
			// get all projects
			List<Project> projects = commonService.getProjects();
			// set a Map builds depend on project. For javaScript functionality
			Map<Integer, Set<BuildFound>> buildsMapList = new HashMap<Integer, Set<BuildFound>>();
			for (Project project : projects) {
				buildsMapList.put(project.getId(), project.getBuilds());
			}
			model.addAttribute(ServletConstants.JSP_PROJECTS_LIST, projects);
			model.addAttribute(
					ServletConstants.JSP_PROJECTS_CURRENT_BUILDS_LIST,
					buildsMapList);
			return jumpPage(ServletConstants.SUBMIT_ISSUE_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/add/save", method = RequestMethod.POST)
	public String saveIssue(
			@RequestParam(ServletConstants.JSP_SUMMARY) String summary,
			@RequestParam(ServletConstants.JSP_DESCRIPTION) String description,
			@RequestParam(ServletConstants.JSP_STATUS) String status,
			@RequestParam(ServletConstants.JSP_TYPE) String type,
			@RequestParam(ServletConstants.JSP_PRIORITY) String priority,
			@RequestParam(ServletConstants.JSP_PROJECT) String project,
			@RequestParam(ServletConstants.JSP_BUILD_FOUND) String buildFound,
			@RequestParam(ServletConstants.JSP_ASSIGNEE) int assignee,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		getDefaultData(model);

		String inputResult = getInputResult(summary, description, status, type,
				priority, project, buildFound);
		if (inputResult != null) {
			return jump(ServletConstants.SUBMIT_ISSUE_PAGE, inputResult, model);
		}

		try {
			// set issue
			Issue issue = new Issue();

			issue.setSummary(summary);
			issue.setDescription(description);
			User curAssignee = userService.getUserById(assignee);
			issue.setAssignee(curAssignee);
			User createdBy = (User) session
					.getAttribute(ServletConstants.JSP_USER);
			User createdUser = userService.getUserById(createdBy.getUserId());
			issue.setCreatedBy(createdUser);
			Calendar calendar = Calendar.getInstance();
			issue.setCreateDate(calendar.getTime());

			Type curType = commonService.getTypeByName(type);
			issue.setType(curType);

			Status curStatus = commonService.getStatusByName(status);
			issue.setStatus(curStatus);

			Priority curPriority = commonService.getPriorityByName(priority);
			issue.setPriority(curPriority);

			Project curProject = commonService.getProjectByNameAndBuild(
					project, buildFound);
			issue.setProject(curProject);

			curStatus.getIssues().add(issue);
			curType.getIssues().add(issue);
			curPriority.getIssues().add(issue);
			curProject.getIssues().add(issue);

			// set issue in db
			boolean isSet = issueService.setIssue(issue);
			if (isSet == true) {
				return jump(ServletConstants.SUBMIT_ISSUE_PAGE,
						ServletConstants.ISSUE_ADD_SUCCESSFULLY, model);
			} else {
				// issue not set
				return jump(ServletConstants.SUBMIT_ISSUE_PAGE,
						ServletConstants.ERROR_ISSUE_NOT_ADD, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.SUBMIT_ISSUE_PAGE, e.getMessage(),
					model);
		}
	}

	@RequestMapping(value = "/{id}/edit")
	public String issueEdit(@PathVariable("id") int id,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			Issue issue = issueService.getIssueById(id);

			model.addAttribute(ServletConstants.JSP_ISSUE, issue);
			model.addAttribute(ServletConstants.JSP_TYPES_LIST,
					commonService.getTypes());
			model.addAttribute(ServletConstants.JSP_STATUSES_LIST,
					commonService.getStatuses());
			model.addAttribute(ServletConstants.JSP_RESOLUTIONS_LIST,
					commonService.getResolutions());
			model.addAttribute(ServletConstants.JSP_PRIORITIES_LIST,
					commonService.getPriorities());
			model.addAttribute(ServletConstants.JSP_PROJECTS_LIST,
					commonService.getProjects());
			model.addAttribute(ServletConstants.JSP_PROJECT_BUILDS_LIST,
					commonService.getBuildFounds());
			model.addAttribute(ServletConstants.JSP_ASSIGNEES_LIST,
					commonService.getUsers());
			// get all comments relate to issue
			List<Comment> comments = new ArrayList<Comment>();
			comments = commentService.getExistCommentsByIssueId(id);

			model.addAttribute(ServletConstants.JSP_COMMENT_LIST, comments);

			return jumpPage(ServletConstants.EDIT_ISSUE_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/edit/save", method = RequestMethod.POST)
	public String issueChangeSave(@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_SUMMARY) String summary,
			@RequestParam(ServletConstants.JSP_DESCRIPTION) String description,
			@RequestParam(ServletConstants.JSP_STATUS) String status,
			@RequestParam(ServletConstants.JSP_TYPE) String type,
			@RequestParam(ServletConstants.JSP_PRIORITY) String priority,
			@RequestParam(ServletConstants.JSP_RESOLUTION) String resolution,
			@RequestParam(ServletConstants.JSP_PROJECT) String project,
			@RequestParam(ServletConstants.JSP_BUILD_FOUND) String buildFound,
			@RequestParam(ServletConstants.JSP_ASSIGNEE) int assigneeId,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		
		String inputResult = getInputResult(summary, description, status, type,
				priority, project, buildFound);
		if (inputResult != null) {
			getEditIssue(model, id);
			return jump(ServletConstants.EDIT_ISSUE_PAGE, inputResult, model);
		}

		try {
			// set issue
			Issue issue = issueService.getIssueById(id);

			issue.setSummary(summary);
			issue.setDescription(description);
			User curAssignee = userService.getUserById(assigneeId);
			issue.setAssignee(curAssignee);
			User modifiedBy = (User) session
					.getAttribute(ServletConstants.JSP_USER);
			User modifiedUser = userService.getUserById(modifiedBy.getUserId());
			issue.setModifiedBy(modifiedUser);
			Calendar calendar = Calendar.getInstance();
			issue.setModifyDate(calendar.getTime());

			Type curType = commonService.getTypeByName(type);
			issue.setType(curType);

			Status curStatus = commonService.getStatusByName(status);
			issue.setStatus(curStatus);

			if (!resolution.equals("") || resolution != null) {
				Resolution curResolution = commonService
						.getResolutionByName(resolution);
				issue.setResolution(curResolution);
			}

			Priority curPriority = commonService.getPriorityByName(priority);
			issue.setPriority(curPriority);

			Project curProject = commonService.getProjectByNameAndBuild(
					project, buildFound);
			issue.setProject(curProject);

			curStatus.getIssues().add(issue);
			curType.getIssues().add(issue);
			curPriority.getIssues().add(issue);
			curProject.getIssues().add(issue);

			// save issue in db
			boolean isUpdated = issueService.updateIssue(issue);
			getEditIssue(model, id);
			if (isUpdated == true) {
				return jump(ServletConstants.EDIT_ISSUE_PAGE,
						ServletConstants.ISSUE_UPDATE_SUCCESSFULLY, model);
			} else {
				// user not found
				return jump(ServletConstants.EDIT_ISSUE_PAGE,
						ServletConstants.ERROR_ISSUE_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			getEditIssue(model, id);
			return jump(ServletConstants.EDIT_ISSUE_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/edit/addComment", method = RequestMethod.POST)
	public String issueAddComment(
			@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_COMMENT) String commentStr,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		String inputResult = getInputResultComment(commentStr);
		if (inputResult != null) {
			getEditIssue(model, id);
			return jump(ServletConstants.EDIT_ISSUE_PAGE, inputResult, model);
		}

		try {
			User user = (User) session.getAttribute(ServletConstants.JSP_USER);

			Comment comment = new Comment();
			comment.setAddedBy(user);
			Calendar calendar = Calendar.getInstance();
			comment.setAddedDate(calendar.getTime());
			comment.setComment(commentStr);
			Issue issue = issueService.getIssueById(id);
			comment.setIssue(issue);

			// set comment in db
			boolean isSet = commentService.addComment(comment);
			model.addAttribute(ServletConstants.JSP_COMMENT_ISSUE_ID, comment
					.getIssue().getId());
			getEditIssue(model, id);
			if (isSet == true) {
				return jump(ServletConstants.EDIT_ISSUE_PAGE, ServletConstants.COMMENT_ADD_SUCCESSFULLY, model);
			} else {
				// user not add
				return jump(ServletConstants.EDIT_ISSUE_PAGE, ServletConstants.ERROR_COMMENT_NOT_ADD, model);
			}
		} catch (DaoException e) {
			getEditIssue(model, id);
			return jump(ServletConstants.EDIT_ISSUE_PAGE, e.getMessage(), model);
		}
	}

	private void getDefaultData(Model model) {
		try {
			model.addAttribute(ServletConstants.JSP_TYPES_LIST,
					commonService.getTypes());
			model.addAttribute(ServletConstants.JSP_STATUSES_LIST,
					commonService.getStatuses());
			model.addAttribute(ServletConstants.JSP_RESOLUTIONS_LIST,
					commonService.getResolutions());
			model.addAttribute(ServletConstants.JSP_PRIORITIES_LIST,
					commonService.getPriorities());
			model.addAttribute(ServletConstants.JSP_PROJECT_BUILDS_LIST,
					commonService.getBuildFounds());
			model.addAttribute(ServletConstants.JSP_ASSIGNEES_LIST,
					commonService.getUsers());
			// get all projects
			List<Project> projects = commonService.getProjects();
			// set a Map builds depend on project. For javaScript functionality
			Map<Integer, Set<BuildFound>> buildsMapList = new HashMap<Integer, Set<BuildFound>>();
			for (Project project : projects) {
				buildsMapList.put(project.getId(), project.getBuilds());
			}
			model.addAttribute(ServletConstants.JSP_PROJECTS_LIST, projects);
			model.addAttribute(
					ServletConstants.JSP_PROJECTS_CURRENT_BUILDS_LIST,
					buildsMapList);
			jumpPage(ServletConstants.SUBMIT_ISSUE_PAGE, model);
		} catch (DaoException e) {
			jump(ServletConstants.SUBMIT_ISSUE_PAGE, e.getMessage(), model);
		}
	}

	private void getEditIssue(Model model, int id) {
		// get data from db
		try {
			Issue issue = issueService.getIssueById(id);

			model.addAttribute(ServletConstants.JSP_ISSUE, issue);
			model.addAttribute(ServletConstants.JSP_TYPES_LIST,
					commonService.getTypes());
			model.addAttribute(ServletConstants.JSP_STATUSES_LIST,
					commonService.getStatuses());
			model.addAttribute(ServletConstants.JSP_RESOLUTIONS_LIST,
					commonService.getResolutions());
			model.addAttribute(ServletConstants.JSP_PRIORITIES_LIST,
					commonService.getPriorities());
			model.addAttribute(ServletConstants.JSP_PROJECTS_LIST,
					commonService.getProjects());
			model.addAttribute(ServletConstants.JSP_PROJECT_BUILDS_LIST,
					commonService.getBuildFounds());
			model.addAttribute(ServletConstants.JSP_ASSIGNEES_LIST,
					commonService.getUsers());
			// get all comments relate to issue
			List<Comment> comments = new ArrayList<Comment>();
			comments = commentService.getExistCommentsByIssueId(id);

			model.addAttribute(ServletConstants.JSP_COMMENT_LIST, comments);

			jumpPage(ServletConstants.EDIT_ISSUE_PAGE, model);
		} catch (DaoException e) {
			jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
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

	protected String jumpError(String message, Model model) {
		return jump(ServletConstants.MAIN_PAGE, message, model);
	}

	private String getInputResult(String summary, String description,
			String status, String type, String priority, String project,
			String buildFound) {
		if (summary == null || summary.equals("")) {
			return ServletConstants.ERROR_SUMMARY_EMPTY;
		}
		if (description == null || description.equals("")) {
			return ServletConstants.ERROR_DESCRIPTION_EMPTY;
		}
		if (status == null || status.equals("")) {
			return ServletConstants.ERROR_STATUS_EMPTY;
		}
		if (type == null || type.equals("")) {
			return ServletConstants.ERROR_STATUS_EMPTY;
		}
		if (priority == null || priority.equals("")) {
			return ServletConstants.ERROR_PRIORITY_EMPTY;
		}
		if (project == null || project.equals("")) {
			return ServletConstants.ERROR_PROJECT_EMPTY;
		}
		if (buildFound == null || buildFound.equals("")) {
			return ServletConstants.ERROR_BUILD_FOUND_EMPTY;
		}
		return null;
	}
	
	private String getInputResultComment(String comment) {
		if (comment == null || comment.equals("")) {
			return ServletConstants.ERROR_COMMENT_EMPTY;
		}
		return null;
	}

}
