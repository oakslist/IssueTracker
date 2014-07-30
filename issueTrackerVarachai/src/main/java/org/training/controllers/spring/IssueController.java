package org.training.controllers.spring;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.training.constants.ServletConstants;
import org.training.form.IssueForm;
import org.training.form.LoginForm;
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Attachment;
import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Comment;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.file.FileValidator;
import org.training.model.file.UploadedFile;
import org.training.model.hib.ifaces.IAttachmentService;
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

	@Autowired
	private IAttachmentService attachmentService;

	@Autowired
	FileValidator fileValidator;

	@RequestMapping(value = "/all")
	public String allIssuesPage(HttpServletRequest request, Model model, ModelMap modelMap) {

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
		LoginForm loginForm = new LoginForm();
		modelMap.put("loginForm", loginForm);

		return ServletConstants.ALL_ISSUES_PAGE;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public String allIssuesPage(HttpServletRequest request, Model model, ModelMap modelMap,
			@Valid LoginForm loginForm, BindingResult result) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		if (result.hasErrors()) {
			getAllIssues(session, model);
			return ServletConstants.ALL_ISSUES_PAGE;
		}

		String emailAddress = loginForm.getEmail();
		String password = loginForm.getPassword();
		
		// check password and email in bd
		try {
			// get exist user
			User user = userService.getExistUser(emailAddress, password);
			if (user != null) {
				// write data in session
				session.setAttribute(ServletConstants.JSP_USER, user);
			} else {
				// user not found
				getAllIssues(session, model);
				return jump(ServletConstants.ALL_ISSUES_PAGE,
						ServletConstants.ERROR_USER_NOT_FOUND, model);
			}
		} catch (DaoException e) {
			getAllIssues(session, model);
			return jump(ServletConstants.ALL_ISSUES_PAGE,
					ServletConstants.ERROR_USER_NOT_FOUND, model);
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
	public String addIssue(HttpServletRequest request, Model model, ModelMap modelMap) {

		HttpSession session = request.getSession(false);
		
		IssueForm issueForm = new IssueForm();
		
		if (session == null) {
			session = request.getSession(true);
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			model.addAttribute(ServletConstants.JSP_TYPES_LIST, commonService.getTypes());
			model.addAttribute(ServletConstants.JSP_STATUSES_LIST, commonService.getStatuses());
			model.addAttribute(ServletConstants.JSP_RESOLUTIONS_LIST, commonService.getResolutions());
			model.addAttribute(ServletConstants.JSP_PRIORITIES_LIST, commonService.getPriorities());
			model.addAttribute(ServletConstants.JSP_PROJECT_BUILDS_LIST, commonService.getBuildFounds());
			model.addAttribute(ServletConstants.JSP_ASSIGNEES_LIST, commonService.getUsers());
			// get all projects
			List<Project> projects = commonService.getProjects();
			// set a Map builds depend on project. For javaScript functionality
			Map<Integer, Set<BuildFound>> buildsMapList = new HashMap<Integer, Set<BuildFound>>();
			for (Project project : projects) {
				buildsMapList.put(project.getId(), project.getBuilds());
			}
			model.addAttribute(ServletConstants.JSP_PROJECTS_LIST, commonService.getProjects());
			model.addAttribute(
					ServletConstants.JSP_PROJECTS_CURRENT_BUILDS_LIST,
					buildsMapList);
			List<User> usersList = new ArrayList<User>();
			// get all users from db
			usersList = userService.getExistUsers();
			
			modelMap.put("assigneesList", usersList);
			
			modelMap.put("issueForm", issueForm);
			return jumpPage(ServletConstants.SUBMIT_ISSUE_PAGE, model);
		} catch (DaoException e) {
			modelMap.put("issueForm", issueForm);
			return jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveIssue(@Valid IssueForm issueForm,
			BindingResult result, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		getDefaultData(model);
		
		if (result.hasErrors()) {
			return ServletConstants.SUBMIT_ISSUE_PAGE;
		}
		
		try {
			// set issue
			Issue issue = new Issue();

			issue.setSummary(issueForm.getSummary());
			issue.setDescription(issueForm.getDescription());
			
			User curAssignee = userService.getUserById(Integer.parseInt(issueForm.getAssignee()));
			issue.setAssignee(curAssignee);			
			User createdBy = (User) session
					.getAttribute(ServletConstants.JSP_USER);
			User createdUser = userService.getUserById(createdBy.getUserId());
			issue.setCreatedBy(createdUser);
			Calendar calendar = Calendar.getInstance();
			issue.setCreateDate(calendar.getTime());

			Type curType = commonService.getTypeByName(issueForm.getType());
			issue.setType(curType);

			Status curStatus = commonService.getStatusByName(issueForm.getStatus());
			issue.setStatus(curStatus);

			Priority curPriority = commonService.getPriorityByName(issueForm.getPriority());
			issue.setPriority(curPriority);

			Project curProject = commonService.getProjectByNameAndBuild(
					issueForm.getProject(), issueForm.getBuildFound());
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
			HttpServletRequest request, Model model,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {

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

			// get all attachments relate to issue
			List<Attachment> attachments = new ArrayList<Attachment>();
			attachments = attachmentService.getExistAttachmentsByIssueId(id);
			model.addAttribute(ServletConstants.JSP_ATTACHMENT_LIST,
					attachments);

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
			HttpServletRequest request, Model model,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {

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
	public String issueAddComment(@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_COMMENT) String commentStr,
			HttpServletRequest request, Model model,
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {

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
				return jump(ServletConstants.EDIT_ISSUE_PAGE,
						ServletConstants.COMMENT_ADD_SUCCESSFULLY, model);
			} else {
				// user not add
				return jump(ServletConstants.EDIT_ISSUE_PAGE,
						ServletConstants.ERROR_COMMENT_NOT_ADD, model);
			}
		} catch (DaoException e) {
			getEditIssue(model, id);
			return jump(ServletConstants.EDIT_ISSUE_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/file/upload", method = RequestMethod.POST)
	public String setFile(@PathVariable("id") int id,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		String name = null;
		boolean isUploaded = false;
		File dir = null;

		if (!file.isEmpty()) {
			try {
				name = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				dir = new File(rootPath + File.separator + id + File.separator
						+ "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				isUploaded = true;
			} catch (Exception e) {
				getEditIssue(model, id);
				return jump(ServletConstants.EDIT_ISSUE_PAGE,
						ServletConstants.ERROR_ATTACHMENT_NOT_ADD, model);
			}
		} else {
			getEditIssue(model, id);
			return jump(ServletConstants.EDIT_ISSUE_PAGE,
					ServletConstants.ERROR_ATTACHMENT_IS_EMPTY, model);
		}

		// update attachment
		if (isUploaded == true) {
			try {
				User user = (User) session
						.getAttribute(ServletConstants.JSP_USER);
				Attachment attachment = new Attachment();
				attachment.setAddedBy(user);
				Calendar calendar = Calendar.getInstance();
				attachment.setAddedDate(calendar.getTime());

				attachment.setLink(dir.toString());
				attachment.setName(file.getOriginalFilename());
				Issue issue = issueService.getIssueById(id);
				attachment.setIssue(issue);

				// set comment in db
				boolean isSet = attachmentService.addAttachment(attachment);
				model.addAttribute(ServletConstants.JSP_ATTACHMENT_ISSUE_ID,
						attachment.getIssue().getId());
				getEditIssue(model, id);
				if (isSet == true) {
					return jump(ServletConstants.EDIT_ISSUE_PAGE,
							ServletConstants.ATTACHMENT_ADD_SUCCESSFULLY, model);
				} else {
					// user not add
					return jump(ServletConstants.EDIT_ISSUE_PAGE,
							ServletConstants.ERROR_ATTACHMENT_NOT_ADD, model);
				}
			} catch (DaoException e) {
				getEditIssue(model, id);
				return jump(ServletConstants.EDIT_ISSUE_PAGE, e.getMessage(),
						model);
			}
		} else {
			getEditIssue(model, id);
			return jump(ServletConstants.EDIT_ISSUE_PAGE,
					ServletConstants.ERROR_ATTACHMENT_NOT_ADD, model);
		}

	}

	@RequestMapping(value = "/{id}/file/{idFile}/download", method = RequestMethod.GET)
	public void getFile(@PathVariable("id") int id,
			@PathVariable("idFile") int idFile, HttpServletRequest request,
			HttpServletResponse response) {

		final int BUFFER_SIZE = 4096;

		Attachment attachment = null;
		try {
			attachment = attachmentService.getAttachmentBuId(idFile);
		} catch (DaoException e) {
			e.printStackTrace();
		}

		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + id + File.separator
				+ "tmpFiles");

		// get absolute path of the application
		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		System.out.println("appPath = " + appPath);

		// construct the complete absolute path of the file
		String fullPath = dir.toString() + File.separator + attachment.getName();
		File downloadFile = new File(fullPath);
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(downloadFile);
			// get MIME type of the file
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}
			System.out.println("MIME type: " + mimeType);

			// set content attributes for the response
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream;
			outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			// get all attachments relate to issue
			List<Attachment> attachments = new ArrayList<Attachment>();
			attachments = attachmentService.getExistAttachmentsByIssueId(id);
			model.addAttribute(ServletConstants.JSP_ATTACHMENT_LIST,
					attachments);

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
	
	private void getAllIssues(HttpSession session, Model model) {
		// get all issues from db
		List<Issue> issuesList = new ArrayList<Issue>();
		try {
			// IIssueDAOHib issueDAO = IssueFactoryHib.getClassFromFactory();
			System.out
					.println("session.getAttribute(ServletConstants.JSP_USER) = "
							+ session.getAttribute(ServletConstants.JSP_USER));
			User curUser = (User) session
					.getAttribute(ServletConstants.JSP_USER);
			if (curUser == null
					|| curUser.getRole().getRoleName()
							.equals(UserRoleEnum.GUEST.toString())) {
				issuesList = issueService.getAllIssues();
			} else {
				if (curUser.getRole().getRoleName()
						.equals(UserRoleEnum.USER.toString())
						|| curUser.getRole().getRoleName()
								.equals(UserRoleEnum.ADMINISTRATOR.toString())) {
					issuesList = issueService
							.getUserIssues(curUser.getUserId());
				}
			}
			// write data in session
			model.addAttribute(ServletConstants.JSP_ISSUES_LIST, issuesList);
		} catch (DaoException e) {
			jumpError(e.getMessage(), model);
		}
	}

}
