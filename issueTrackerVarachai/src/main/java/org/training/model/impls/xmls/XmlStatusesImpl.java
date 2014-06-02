package org.training.model.impls.xmls;

import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.training.constants.ServletConstants;
import org.training.ifaces.xmlDAO.IStatusesDAO;
import org.training.model.impls.DaoException;

public class XmlStatusesImpl implements IStatusesDAO {

	private Map<Integer, String> statusesMap;
	private Integer id = 0;
	private String content = null;
	
	
	@Override
	public Map<Integer, String> getExistStatuses() throws DaoException {

		statusesMap = new HashMap<Integer, String>();

		// set our file to read by XMLInputFactory
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		try {
			reader = factory
					.createXMLStreamReader(getClass().getResourceAsStream(
							ServletConstants.XML_ISSUE_STATUSES_FILE));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		// read IssueStatuses.xml file
		try {
			while (reader.hasNext()) {
				int event = reader.next();

				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ("status".equals(reader.getLocalName())) {
						id = Integer.parseInt(reader.getAttributeValue(0));
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					content = reader.getText().trim();
					break;

				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "name":
						statusesMap.put(id, content);
						break;
					}
					break;

				case XMLStreamConstants.START_DOCUMENT:
					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return statusesMap;
	}
	
	
}
