package by.epam.model.beans;


public class UserRolePrivileges {

	private boolean viewDefect = false;
	private boolean editDefect = false;
	private boolean addDefect = false;
	private boolean addComments = false;
	private boolean addFiles = false;
	private boolean closeDefect = false;
	private boolean reopenDefect = false;
	private boolean purposeDefect = false;
	private boolean searchDefects = false;
	private boolean searchUsers = false;
	private boolean getNotifications = false;
	private boolean editOwnUserData = false;
	private boolean viewUsersData = false;
	private boolean editUsersData = false;
	private boolean addUser = false;
	private boolean addProject = false;
	private boolean editProject = false;
	private boolean editStatus = false;
	private boolean addResolution = false;
	private boolean editResolution = false;
	private boolean addPriority = false;
	private boolean editPriority = false;
	private boolean addType = false;
	private boolean editType = false;

	private String content = null;
	private boolean readOn = false;

	public UserRolePrivileges() {
		
	}

	public boolean isViewDefect() {
		return viewDefect;
	}

	public boolean isEditDefect() {
		return editDefect;
	}

	public boolean isAddDefect() {
		return addDefect;
	}

	public boolean isAddComments() {
		return addComments;
	}

	public boolean isAddFiles() {
		return addFiles;
	}

	public boolean isCloseDefect() {
		return closeDefect;
	}

	public boolean isReopenDefect() {
		return reopenDefect;
	}

	public boolean isPurposeDefect() {
		return purposeDefect;
	}

	public boolean isSearchDefects() {
		return searchDefects;
	}

	public boolean isSearchUsers() {
		return searchUsers;
	}

	public boolean isGetNotifications() {
		return getNotifications;
	}

	public boolean isViewUsersData() {
		return viewUsersData;
	}

	public boolean isEditUsersData() {
		return editUsersData;
	}

	public boolean isAddUser() {
		return addUser;
	}

	public boolean isAddProject() {
		return addProject;
	}

	public boolean isEditProject() {
		return editProject;
	}

	public boolean isEditStatus() {
		return editStatus;
	}

	public boolean isAddResolution() {
		return addResolution;
	}

	public boolean isEditResolution() {
		return editResolution;
	}

	public boolean isAddPriority() {
		return addPriority;
	}

	public boolean isEditPriority() {
		return editPriority;
	}

	public boolean isAddType() {
		return addType;
	}

	public boolean isEditType() {
		return editType;
	}
	
	public boolean isEditOwnUserData() {
		return editOwnUserData;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isReadOn() {
		return readOn;
	}

	public void setReadOn(boolean readOn) {
		this.readOn = readOn;
	}

	public void setViewDefect(boolean viewDefect) {
		this.viewDefect = viewDefect;
	}

	public void setEditDefect(boolean editDefect) {
		this.editDefect = editDefect;
	}

	public void setAddDefect(boolean addDefect) {
		this.addDefect = addDefect;
	}

	public void setAddComments(boolean addComments) {
		this.addComments = addComments;
	}

	public void setAddFiles(boolean addFiles) {
		this.addFiles = addFiles;
	}

	public void setCloseDefect(boolean closeDefect) {
		this.closeDefect = closeDefect;
	}

	public void setReopenDefect(boolean reopenDefect) {
		this.reopenDefect = reopenDefect;
	}

	public void setPurposeDefect(boolean purposeDefect) {
		this.purposeDefect = purposeDefect;
	}

	public void setSearchDefects(boolean searchDefects) {
		this.searchDefects = searchDefects;
	}

	public void setSearchUsers(boolean searchUsers) {
		this.searchUsers = searchUsers;
	}

	public void setGetNotifications(boolean getNotifications) {
		this.getNotifications = getNotifications;
	}

	public void setEditOwnUserData(boolean editOwnUserData) {
		this.editOwnUserData = editOwnUserData;
	}

	public void setViewUsersData(boolean viewUsersData) {
		this.viewUsersData = viewUsersData;
	}

	public void setEditUsersData(boolean editUsersData) {
		this.editUsersData = editUsersData;
	}

	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}

	public void setAddProject(boolean addProject) {
		this.addProject = addProject;
	}

	public void setEditProject(boolean editProject) {
		this.editProject = editProject;
	}

	public void setEditStatus(boolean editStatus) {
		this.editStatus = editStatus;
	}

	public void setAddResolution(boolean addResolution) {
		this.addResolution = addResolution;
	}

	public void setEditResolution(boolean editResolution) {
		this.editResolution = editResolution;
	}

	public void setAddPriority(boolean addPriority) {
		this.addPriority = addPriority;
	}

	public void setEditPriority(boolean editPriority) {
		this.editPriority = editPriority;
	}

	public void setAddType(boolean addType) {
		this.addType = addType;
	}

	public void setEditType(boolean editType) {
		this.editType = editType;
	}

	@Override
	public String toString() {
		return "UserRolePrivileges [viewDefect=" + viewDefect + ", editDefect="
				+ editDefect + ", addDefect=" + addDefect + ", addComments="
				+ addComments + ", addFiles=" + addFiles + ", closeDefect="
				+ closeDefect + ", reopenDefect=" + reopenDefect
				+ ", purposeDefect=" + purposeDefect + ", searchDefects="
				+ searchDefects + ", searchUsers=" + searchUsers
				+ ", getNotifications=" + getNotifications
				+ ", editOwnUserData=" + editOwnUserData + ", viewUsersData="
				+ viewUsersData + ", editUsersData=" + editUsersData
				+ ", addUser=" + addUser + ", addProject=" + addProject
				+ ", editProject=" + editProject + ", editStatus=" + editStatus
				+ ", addResolution=" + addResolution + ", editResolution="
				+ editResolution + ", addPriority=" + addPriority
				+ ", editPriority=" + editPriority + ", addType=" + addType
				+ ", editType=" + editType + "]";
	}

}
