package by.epam.impls;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.constants.ServletConstants;

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

	public UserRolePrivileges(String userRole) {

		// set our file to read by XMLInputFactory
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		try {
			reader = factory.createXMLStreamReader(getClass()
					.getResourceAsStream(ServletConstants.XML_PRIVILEGES_FILE));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		// read UsersRolePrivileges.xml file
		try {
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ("role".equals(reader.getLocalName())
							&& reader.getAttributeValue(0).equals(userRole)) {
						readOn = true;
					}
					if ("role".equals(reader.getLocalName())
							&& !reader.getAttributeValue(0).equals(userRole)) {
						readOn = false;
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					if (readOn == true) {
						content = reader.getText().trim();
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					if (readOn == true) {
						switch (reader.getLocalName()) {
						case "viewDefect":
							viewDefect = contentToBoolean(content);
							break;
						case "editDefect":
							this.editDefect = contentToBoolean(content);
							break;
						case "addDefect":
							this.addDefect = contentToBoolean(content);
							break;
						case "addComments":
							this.addComments = contentToBoolean(content);
							break;
						case "addFiles":
							this.addFiles = contentToBoolean(content);
							break;
						case "closeDefect":
							this.closeDefect = contentToBoolean(content);
							break;
						case "reopenDefect":
							this.reopenDefect = contentToBoolean(content);
							break;
						case "purposeDefect":
							this.purposeDefect = contentToBoolean(content);
							break;
						case "searchDefects":
							this.searchDefects = contentToBoolean(content);
							break;
						case "searchUsers":
							this.searchUsers = contentToBoolean(content);
							break;
						case "getNotifications":
							this.getNotifications = contentToBoolean(content);
							break;
						case "editOwnUserData":
							this.editOwnUserData = contentToBoolean(content);
							break;
						case "viewUsersData":
							this.viewUsersData = contentToBoolean(content);
							break;
						case "editUsersData":
							this.editUsersData = contentToBoolean(content);
							break;
						case "addUser":
							this.addUser = contentToBoolean(content);
							break;
						case "addProject":
							this.addProject = contentToBoolean(content);
							break;
						case "editProject":
							this.editProject = contentToBoolean(content);
							break;
						case "editStatus":
							this.editStatus = contentToBoolean(content);
							break;
						case "addResolution":
							this.addResolution = contentToBoolean(content);
							break;
						case "editResolution":
							this.editResolution = contentToBoolean(content);
							break;
						case "addPriority":
							this.addPriority = contentToBoolean(content);
							break;
						case "editPriority":
							this.editPriority = contentToBoolean(content);
							break;
						case "addType":
							this.addType = contentToBoolean(content);
							break;
						case "editType":
							this.editType = contentToBoolean(content);
							break;
						}
					}
					break;
				case XMLStreamConstants.START_DOCUMENT:
					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private boolean contentToBoolean(String con) {
		if (con.equals("true")) {
			return true;
		}
		return false;
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
