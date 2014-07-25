package org.training.controllers.spring;

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
import org.training.model.beans.hibbeans.Priority;
import org.training.model.hib.ifaces.ITableDataService;
import org.training.model.impls.DaoException;

@Controller
@RequestMapping(value = "/priority")
public class PriorityController {

	@Autowired
	private ITableDataService commonService;

	@RequestMapping(value = "/all")
	public String showAllPriorities(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			model.addAttribute(ServletConstants.JSP_PRIORITIES_LIST,
					commonService.getPriorities());
			return jumpPage(ServletConstants.PRIORITIES_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/edit")
	public String priorityEdit(@PathVariable("id") int id,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		try {
			// get status from db
			Priority priority = commonService.getPriorityById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_PRIORITY, priority);
			return jumpPage(ServletConstants.EDIT_PRIORITY_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.PRIORITIES_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
	public String priorityChangeSave(
			@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_EDIT_PRIORITY) String priorityName,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		Priority priority = getEditPriority(id, model);

		String inputResult = getInputResultSave(priorityName);
		if (inputResult != null) {
			return jump(ServletConstants.EDIT_PRIORITY_PAGE, inputResult, model);
		}

		try {
			// update status in db
			priority.setPriorityName(priorityName);
			boolean isUpdated = commonService.updatePriority(priority);
			if (isUpdated == true) {
				return jump(ServletConstants.EDIT_PRIORITY_PAGE,
						ServletConstants.RESOLUTION_UPDATE_SUCCESSFULLY, model);
			} else {
				// status not update
				return jump(ServletConstants.EDIT_PRIORITY_PAGE,
						ServletConstants.ERROR_RESOLUTION_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_PRIORITY_PAGE, e.getMessage(),
					model);
		}
	}

	@RequestMapping(value = "/add")
	public String priorityAddNew(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		return jumpPage(ServletConstants.ADD_NEW_PRIORITY_PAGE, model);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String prioritySave(
			@RequestParam(ServletConstants.JSP_ADD_PRIORITY) String priorityName,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		String inputResult = getInputResultSave(priorityName);
		if (inputResult != null) {
			return jump(ServletConstants.ADD_NEW_PRIORITY_PAGE, inputResult,
					model);
		}

		try {
			// set type in db
			Priority priority = new Priority();
			priority.setPriorityName(priorityName);
			boolean isSet = commonService.setPriority(priority);
			if (isSet == true) {
				return jump(ServletConstants.ADD_NEW_PRIORITY_PAGE,
						ServletConstants.PRIORITY_UPDATE_SUCCESSFULLY, model);
			} else {
				// type not update
				return jump(ServletConstants.ADD_NEW_PRIORITY_PAGE,
						ServletConstants.ERROR_PRIORITY_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.ADD_NEW_PRIORITY_PAGE, e.getMessage(),
					model);
		}
	}

	private Priority getEditPriority(int id, Model model) {
		Priority priority = null;
		try {
			// get status from db
			priority = commonService.getPriorityById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_PRIORITY, priority);
			jumpPage(ServletConstants.EDIT_PRIORITY_PAGE, model);
		} catch (DaoException e) {
			jump(ServletConstants.EDIT_PRIORITY_PAGE, e.getMessage(), model);
		}
		return priority;
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

	private String getInputResultSave(String priorityName) {
		if (priorityName == null || priorityName.equals("")) {
			return ServletConstants.ERROR_PRIORITY_NAME_EMPTY;
		}
		return null;
	}

}
