package by.epam.impls;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.beans.User;
import by.epam.beans.UserRole;
import by.epam.constants.ServletConstants;

public class StaxParserReaderUsers {
	
	private List<User> usersList = null;
	private User user = null;
	private String content = null;

	public StaxParserReaderUsers() {
		// set our file to read by XMLInputFactory
		XMLInputFactory factory = XMLInputFactory.newInstance();
	    XMLStreamReader reader = null;
		try {
			reader = factory.createXMLStreamReader(getClass()
					.getResourceAsStream(ServletConstants.XML_USERS_FILE));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		// read Users.xml file and set data into usersList
		try {
			while (reader.hasNext()) {
				int event = reader.next();

				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ("user".equals(reader.getLocalName())) {
						user = new User();
					}
					if ("users".equals(reader.getLocalName())) {
						usersList = new ArrayList<>();
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					content = reader.getText().trim();
					break;

				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "user":
						usersList.add(user);
						break;
					case "username":
						user.setUsername(content);
						break;
					case "password":
						user.setPassword(content);
						break;
					case "role":
						user.setRole(UserRole.valueOf(content));
						break;
					}
					break;

				case XMLStreamConstants.START_DOCUMENT:
					usersList = new ArrayList<>();
					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public List<User> getUsersList() {
		return usersList;
	}

}
