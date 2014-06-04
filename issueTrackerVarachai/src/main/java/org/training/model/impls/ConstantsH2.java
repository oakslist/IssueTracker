package org.training.model.impls;

public class ConstantsH2 {
	
	public static final String H2_DRIVER = "org.h2.Driver";
	public static final String H2_DB_USER = "issueTrackerDB";
	public static final String H2_DB_PATH = "jdbc:h2:./src/main/resources/h2db/issueTrackerDB" + H2_DB_USER;
	public static final String H2_BD_PASSWORD = "";
	
	public static final String H2_ERROR_CLOSE_CONNECTION = "Can't close connection.";
		
	public final static String SELECT_USER = "SELECT * FROM users LEFT JOIN roles "
			+ "ON users.roleId = roles.roleId WHERE emailAddress=? AND password=?";
	
		
	public final static String SELECT_ALL_ISSUES = "SELECT * FROM issues GROUP BY issueId";
	public final static String SELECT_ALL_STATUSES = "SELECT * FROM statuses";
	public final static String SELECT_ALL_TYPES = "SELECT * FROM types";
	public final static String SELECT_ALL_RESOLUTIONS = "SELECT * FROM resolutions";
	public final static String SELECT_ALL_PRIORITIES = "SELECT * FROM priorities";
	public final static String SELECT_ALL_PROJECT_BUILDS = "SELECT * FROM builds WHERE buildNumber = ?";
	public final static String SELECT_ALL_BUILDS = "SELECT * FROM builds";
	public final static String SELECT_ALL_PROJECTS = "SELECT * FROM projects LEFT JOIN users "
			+ "ON users.userId = projects.managerId LEFT JOIN builds "
			+ "ON builds.buildId = projects.buildId";
	public final static String SELECT_ALL_USERS = "SELECT * FROM users LEFT JOIN roles "
			+ "ON users.roleId = roles.roleId";
	public final static String SELECT_USER_BY_ID = "SELECT * FROM users LEFT JOIN roles "
			+ "ON users.roleId = roles.roleId WHERE userId = ?";
	
	public final static String SELECT_ALL_ISSUES2 = "SELECT * FROM issues "
			+ "LEFT JOIN statuses ON issues.statusId = statuses.statusId "
			+ "LEFT JOIN types ON issues.typeId = types.typeId "
			+ "LEFT JOIN resolutions ON issues.resolutionId = resolutions.resolutionId "
			+ "LEFT JOIN priorities ON issues.priorityId = priorities.priorityId "
			+ "LEFT JOIN projects ON issues.projectId = projects.projectId "
			+ "LEFT JOIN builds ON issues.buildId = builds.buildId "
			+ "GROUP BY issueId";
	
	public final static String INSERT_ISSUE = "INSERT INTO issues (summary, description, statusId, "
			+ "typeId, priorityId, projectId, buildId, assignee, createdBy) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
	public final static String UPDATE_ISSUE = "UPDATE issues SET modifyDate = ?, "
			+ "modifiedBy = ?, summary = ?, description = ?, statusId = ?, "
			+ "resolutionId = ?, typeId = ?, priorityId = ?, projectId = ?, "
			+ "buildId = ?, assignee = ? WHERE issueId = ?";
	
	public final static String SELECT_ISSUE = "SELECT * FROM issues "
			+ "LEFT JOIN statuses ON issues.statusId = statuses.statusId "
			+ "LEFT JOIN types ON issues.typeId = types.typeId "
			+ "LEFT JOIN resolutions ON issues.resolutionId = resolutions.resolutionId "
			+ "LEFT JOIN priorities ON issues.priorityId = priorities.priorityId "
			+ "LEFT JOIN projects ON issues.projectId = projects.projectId "
			+ "LEFT JOIN builds ON issues.buildId = builds.buildId "
			+ "LEFT JOIN users ON issues.createdBy = users.userId "
			+ "WHERE issueId = ? GROUP BY issueId";	
	
	public final static String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE users.login=?";
	public final static String ADD_NEW_USER = "INSERT INTO users (login,password) VALUES (?,?)";
	
	public final static String SELECT_NOTES = "SELECT * FROM notes WHERE userId = ? AND date = ? AND status = ? ORDER BY id";
	public final static String SELECT_NOTES_SOMEDAY = "SELECT * FROM notes WHERE userId = ? AND (date < ? OR date > ?) AND status = ? ORDER BY id";
	public final static String SELECT_NOTES_BY_STATUS = "SELECT * FROM notes WHERE userId = ? AND status = ? ORDER BY id";
	public final static String ADD_NEW_NOTE = "INSERT INTO notes (userId, task, date, status) VALUES (?, ?, ?, 0)";
	public final static String UPDATE_STATUS_NOTE = "UPDATE notes SET status = ? WHERE id = ?";
	public final static String UPDATE_RESTORE_STATUS = "UPDATE notes SET status = 0, date = ? WHERE id = ?";
	public final static String DELETE_FOREVER_NOTE = "DELETE FROM notes WHERE id = ?";
	public final static String UPDATE_FILE_NOTE = "UPDATE notes SET file = ? WHERE id = ?";
	
	
	
	public final static String INSERT_INTO_ROLES = "INSERT INTO roles (roleName) VALUES (?)";
	public final static String INSERT_INTO_STATUSES = "INSERT INTO statuses (statusName) VALUES (?)";
	public final static String INSERT_INTO_TYPES = "INSERT INTO types (typeName) VALUES (?)";
	public final static String INSERT_INTO_RESOLUTIONS = "INSERT INTO resolutions (resolutionName) VALUES (?)";
	public final static String INSERT_INTO_PRIORITIES = "INSERT INTO priorities (priorityName) VALUES (?)";
	
	public static final int KEY_NOTE_STATUS_DEFAULT = 0;
	public static final int KEY_NOTE_STATUS_FIXED = 1;
	public static final int KEY_NOTE_STATUS_RECYCLE_BIN = 2;
	
}