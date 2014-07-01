package org.training.constants;

import org.training.model.beans.enums.UserRoleEnum;

public class ServletConstants {
	
	public static final int NUMBER_N = 10;
	public static final boolean IS_CREATE_ALL_TABLES = true;
	public static final boolean IS_SET_DEFAULT_DATA = true;
	
	public static final String DEFAULT_ADMIN_FIRST_NAME = "Siarhei";
	public static final String DEFAULT_ADMIN_LAST_NAME = "Varachai";
	public static final String DEFAULT_ADMIN_EMAIL_ADDRESS = "ad@ad.ad";
	public static final String DEFAULT_ADMIN_PASSWORD = "ad";
	public static final UserRoleEnum DEFAULT_ADMIN_ROLE = UserRoleEnum.ADMINISTRATOR;
	
	public static final String DEFAULT_USER_FIRST_NAME = "Ivan";
	public static final String DEFAULT_USER_LAST_NAME = "Ivanov";
	public static final String DEFAULT_USER_EMAIL_ADDRESS = "us@us.us";
	public static final String DEFAULT_USER_PASSWORD = "us";
	public static final UserRoleEnum DEFAULT_USER_ROLE = UserRoleEnum.USER;
		
	public static final String XML_USERS_FILE = "/Users.xml";
	public static final String XML_PRIVILEGES_FILE = "/RolePrivileges.xml";
	public static final String XML_ISSUE_TYPES_FILE = "/IssueTypes.xml";
	public static final String XML_ISSUE_PRIORITIES_FILE = "/IssuePriorities.xml";
	public static final String XML_ISSUE_RESOLUTIONS_FILE = "/IssueResolutions.xml";
	public static final String XML_ISSUE_STATUSES_FILE = "/IssueStatuses.xml";
	
	public static final String JSP_USER = "user";
	public static final String JSP_USER_LIST = "userList";
	public static final String JSP_COMMENT_LIST = "commentList";
	public static final String JSP_FIRST_NAME = "firstName";
	public static final String JSP_LAST_NAME = "lastName";
	public static final String JSP_EMAIL_ADDRESS = "emailAddress";
	public static final String JSP_ROLE = "role";
	public static final String JSP_PASSWORD = "password";
	public static final String JSP_PASSWORD_CONFIRMATION = "passwordConfirmation";
	public static final String JSP_SUMMARY = "summary";
	public static final String JSP_DESCRIPTION = "description";
	public static final String JSP_STATUS = "status";
	public static final String JSP_TYPE = "type";
	public static final String JSP_RESOLUTION = "resolution";
	public static final String JSP_PRIORITY = "priority";
	public static final String JSP_PROJECT = "project";
	public static final String JSP_BUILD_FOUND = "buildFound";
	public static final String JSP_ASSIGNEE = "assignee";
	public static final String JSP_CREATE_DATE = "createDate";
	public static final String JSP_CREATED_BY = "createdBy";
	public static final String JSP_MODIFY_DATE = "modifyDate";
	public static final String JSP_MODIFIED_BY = "modifiedBy";
	public static final String JSP_ISSUES_LIST = "issuesList";
	public static final String JSP_ISSUE = "issue";
	public static final String JSP_ISSUE_ID = "issueId";
	public static final String JSP_EDIT_USER_BY_ID = "editUser";
	public static final String JSP_EDIT_DIFF_USER_BY_ID = "editDiffUser";
	public static final String JSP_EDIT_PASSWORD_BY_ID = "editPassword";
	public static final String JSP_COMMENT = "comment";
	public static final String JSP_COMMENT_ISSUE_ID = "commentIssueId";
	public static final String JSP_EDIT_STATUS = "editStatus";
	public static final String JSP_EDIT_TYPE = "editType";
	public static final String JSP_EDIT_PRIORITY = "editPriority";
	public static final String JSP_EDIT_RESOLUTION = "editResolution";
	public static final String JSP_EDIT_PROJECT = "editProject";


	public static final String JSP_ADD_STATUS = "addStatus";
	public static final String JSP_ADD_TYPE = "addType";
	public static final String JSP_ADD_RESOLUTION = "addResolution";
	public static final String JSP_ADD_PRIORITY = "addPriority";
	public static final String JSP_ADD_PROJECT_NAME = "addProjectName";
	public static final String JSP_ADD_PROJECT_DESCRIPTION = "addProjectDescription";
	public static final String JSP_ADD_PROJECT_BUILD = "addProjectBuild";
	public static final String JSP_ADD_PROJECT_MANAGERS = "addProjectManagers";
	public static final String JSP_ADD_BUILD_FOUND = "addBuildFound";

	
	public static final String JSP_ALL_ISSUES_LIST = "AllIssuesList";
	public static final String JSP_TYPES_LIST = "issueTypes";
	public static final String JSP_STATUSES_LIST = "issueStatuses";
	public static final String JSP_RESOLUTIONS_LIST = "issueResolutions";
	public static final String JSP_PRIORITIES_LIST = "issuePriorities";
	public static final String JSP_PROJECTS_LIST = "issueProjects";
	public static final String JSP_PROJECT_BUILDS_LIST = "issueProjectBuilds";
	public static final String JSP_ASSIGNEES_LIST = "issueAssignees";
	
		
	public static final String JSP_ERROR_MESSAGE = "errorMessage";
	
	public static final String ERROR_USER_NOT_FOUND = "User not found.";
	public static final String ERROR_DATA_NOT_FOUND = "Data not found.";
	public static final String ERROR_EMAIL_EMPTY = "Email address is empty.";
	public static final String ERROR_PASSWORD_EMPTY = "Password is empty.";
	public static final String ERROR_PASSWORD_CONFIRM_EMPTY = "Password confirmation is empty.";
	public static final String ERROR_PASSWORDS_NOT_EQUAL = "Password and Password Confirmation are not equal.";
	public static final String ERROR_FIRST_NAME_EMPTY = "First name is empty.";
	public static final String ERROR_LAST_NAME_EMPTY = "Last name is empty.";
	public static final String ERROR_ROLE_EMPTY = "Role is empty.";
	public static final String ERROR_SUMMARY_EMPTY = "Summary is empty.";
	public static final String ERROR_DESCRIPTION_EMPTY = "Description is empty.";
	public static final String ERROR_STATUS_EMPTY = "Status is empty.";
	public static final String ERROR_TYPE_EMPTY = "Type is empty.";
	public static final String ERROR_PRIORITY_EMPTY = "Priority is empty.";
	public static final String ERROR_PROJECT_EMPTY = "Project is empty.";
	public static final String ERROR_BUILD_FOUND_EMPTY = "Build Found is empty.";
	public static final String ERROR_COMMENT_EMPTY = "Comment is empty.";
	public static final String ERROR_ADD_PARAMS_EMPTY = "All necessary params is empty.";
	public static final String ERROR_STATUS_NAME_EMPTY = "Status name is empty.";
	public static final String ERROR_TYPE_NAME_EMPTY = "Type name is empty.";
	public static final String ERROR_RESOLUTION_NAME_EMPTY = "Resolution name is empty.";
	public static final String ERROR_PRIORITY_NAME_EMPTY = "Priority name is empty.";
	
	public static final String ERROR_ISSUE_NOT_ADD = "Issue not add.";
	public static final String ERROR_USER_NOT_ADD = "User not add.";
	public static final String ERROR_ISSUE_NOT_UPDATE = "Issue not update.";
	public static final String ERROR_USER_NOT_UPDATE = "User not update.";
	public static final String ERROR_PASSWORD_NOT_UPDATE = "Password not update.";
	public static final String ERROR_COMMENT_NOT_ADD = "Comment not add.";
	public static final String ERROR_STATUS_NOT_UPDATE = "Status not update.";
	public static final String ERROR_TYPE_NOT_UPDATE = "Type not update.";
	public static final String ERROR_RESOLUTION_NOT_UPDATE = "Resolution not update.";
	public static final String ERROR_PRIORITY_NOT_UPDATE = "Priority not update.";
	
	public static final String ISSUE_ADD_SUCCESSFULLY = "Issue was added successfully.";
	public static final String USER_ADD_SUCCESSFULLY = "USER was added successfully.";
	public static final String ISSUE_UPDATE_SUCCESSFULLY = "Issue was updated successfully.";
	public static final String USER_UPDATE_SUCCESSFULLY = "User was updated successfully.";
	public static final String STATUS_UPDATE_SUCCESSFULLY = "Status was updated successfully.";
	public static final String TYPE_UPDATE_SUCCESSFULLY = "Type was updated successfully.";
	public static final String RESOLUTION_UPDATE_SUCCESSFULLY = "Resolution was updated successfully.";
	public static final String PRIORITY_UPDATE_SUCCESSFULLY = "Priority was updated successfully.";
	public static final String PASSWORD_UPDATE_SUCCESSFULLY = "Password was updated successfully.";
	public static final String COMMENT_ADD_SUCCESSFULLY = "Comment was added successfully.";
	public static final String PARAMS_ADD_SUCCESSFULLY = "Params was added successfully.";
		
	public static final String LOGIN_VALID = "login ok";
	public static final String LOGIN_INVALID = "error: invalid username or password!";
	
	public static final String ERROR_NULL_SESSION = "error: session was lost!";
	public static final String KEY_ERROR_MESSAGE = "errorMessage";
	
	public static final String MESSAGE = "message";
	public static final String KEY_EMPTY = "";
	
	public static final String JUMP_INDEX_PAGE = "/index.jsp";
	public static final String JUMP_LOGIN = "/pages/login.jsp";
	public static final String JUMP_MAIN_PAGE = "/pages/main.jsp";
	public static final String JUMP_SUBMIT_ISSUE_PAGE = "/pages/submitIssue.jsp";
	public static final String JUMP_EDIT_USER_PAGE = "/pages/editUser.jsp";
	public static final String JUMP_EDIT_PASSWORD_PAGE = "/pages/editPassword.jsp";
	public static final String JUMP_ADD_USER_PAGE = "/pages/addUser.jsp";
	public static final String JUMP_EDIT_ISSUE_PAGE = "/pages/editIssue.jsp";
	public static final String JUMP_ADD_PARAMS_PAGE = "/pages/addParams.jsp";
	public static final String JUMP_SHOW_STATUSES_PAGE = "/pages/show/showStatuses.jsp";
	public static final String JUMP_SEARCH_USER_PAGE = "/pages/searchUser.jsp";
	public static final String JUMP_BEFORE_EDIT_ISSUE_CONTROLLER = "/BeforeEditIssueController";
	public static final String JUMP_SHOW_STATUS_PAGE = "/pages/show/showStatuses.jsp";
	public static final String JUMP_SHOW_TYPE_PAGE = "/pages/show/showTypes.jsp";
	public static final String JUMP_SHOW_RESOLUTION_PAGE = "/pages/show/showResolutions.jsp";
	public static final String JUMP_SHOW_PRIORITY_PAGE = "/pages/show/showPriorities.jsp";
	public static final String JUMP_SHOW_ALL_ISSUES_PAGE = "/pages/show/showIssues.jsp";
	public static final String JUMP_EDIT_STATUS_PAGE = "/pages/show/editStatus.jsp";
	public static final String JUMP_EDIT_TYPE_PAGE = "/pages/show/editType.jsp";
	public static final String JUMP_EDIT_RESOLUTION_PAGE = "/pages/show/editResolution.jsp";
	public static final String JUMP_EDIT_PRIORITY_PAGE = "/pages/show/editPriority.jsp";


}
