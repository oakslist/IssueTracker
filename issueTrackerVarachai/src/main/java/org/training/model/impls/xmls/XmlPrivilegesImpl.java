package org.training.model.impls.xmls;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.training.constants.ServletConstants;
import org.training.ifaces.xmlDAO.IPrivilegesDAO;
import org.training.model.beans.UserRolePrivileges;
import org.training.model.impls.DaoException;

public class XmlPrivilegesImpl implements IPrivilegesDAO {
	
	private String content = null;
	private boolean readOn = false;
	UserRolePrivileges userRolePrivileges = null;

	@Override
	public UserRolePrivileges getExistPrivileges(String role)
			throws DaoException {

		userRolePrivileges = new UserRolePrivileges();

		// set our file to read by XMLInputFactory
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		try {
			reader = factory.createXMLStreamReader(getClass()
					.getResourceAsStream(ServletConstants.XML_PRIVILEGES_FILE));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		// read IssuePrivileges.xml file
		try {
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ("role".equals(reader.getLocalName())
							&& reader.getAttributeValue(0).equals(role)) {
						readOn = true;
					}
					if ("role".equals(reader.getLocalName())
							&& !reader.getAttributeValue(0).equals(role)) {
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
							userRolePrivileges
									.setViewDefect(contentToBoolean(content));
							break;
						case "editDefect":
							userRolePrivileges
									.setEditDefect(contentToBoolean(content));
							break;
						case "addDefect":
							userRolePrivileges
									.setAddDefect(contentToBoolean(content));
							break;
						case "addComments":
							userRolePrivileges
									.setAddComments(contentToBoolean(content));
							break;
						case "addFiles":
							userRolePrivileges
									.setAddFiles(contentToBoolean(content));
							break;
						case "closeDefect":
							userRolePrivileges
									.setCloseDefect(contentToBoolean(content));
							break;
						case "reopenDefect":
							userRolePrivileges
									.setReopenDefect(contentToBoolean(content));
							break;
						case "purposeDefect":
							userRolePrivileges
									.setPurposeDefect(contentToBoolean(content));
							break;
						case "searchDefects":
							userRolePrivileges
									.setSearchDefects(contentToBoolean(content));
							break;
						case "searchUsers":
							userRolePrivileges
									.setSearchUsers(contentToBoolean(content));
							break;
						case "getNotifications":
							userRolePrivileges
									.setGetNotifications(contentToBoolean(content));
							break;
						case "editOwnUserData":
							userRolePrivileges
									.setEditOwnUserData(contentToBoolean(content));
							break;
						case "viewUsersData":
							userRolePrivileges
									.setViewUsersData(contentToBoolean(content));
							break;
						case "editUsersData":
							userRolePrivileges
									.setEditUsersData(contentToBoolean(content));
							break;
						case "addUser":
							userRolePrivileges
									.setAddUser(contentToBoolean(content));
							break;
						case "addProject":
							userRolePrivileges
									.setAddProject(contentToBoolean(content));
							break;
						case "editProject":
							userRolePrivileges
									.setEditProject(contentToBoolean(content));
							break;
						case "editStatus":
							userRolePrivileges
									.setEditStatus(contentToBoolean(content));
							break;
						case "addResolution":
							userRolePrivileges
									.setAddResolution(contentToBoolean(content));
							break;
						case "editResolution":
							userRolePrivileges
									.setEditResolution(contentToBoolean(content));
							break;
						case "addPriority":
							userRolePrivileges
									.setAddPriority(contentToBoolean(content));
							break;
						case "editPriority":
							userRolePrivileges
									.setEditPriority(contentToBoolean(content));
							break;
						case "addType":
							userRolePrivileges
									.setAddType(contentToBoolean(content));
							break;
						case "editType":
							userRolePrivileges
									.setEditType(contentToBoolean(content));
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

		return this.userRolePrivileges;
	}
	
	private boolean contentToBoolean(String con) {
		if (con.equals("true")) {
			return true;
		}
		return false;
	}

}
