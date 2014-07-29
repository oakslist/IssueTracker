package org.training.controllers.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.training.constants.ServletConstants;
import org.training.form.AddSimpleNameForm;
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
			HttpServletRequest request, Model model, ModelMap modelMap) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		AddSimpleNameForm addSimpleNameForm = new AddSimpleNameForm();
		modelMap.put("addSimpleNameForm", addSimpleNameForm);

		try {
			// get status from db
			Priority priority = commonService.getPriorityById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_PRIORITY, priority);
			return jumpPage(ServletConstants.EDIT_PRIORITY_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.PRIORITIES_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/edit/save", method = RequestMethod.POST)
	public String priorityChangeSave(@Valid AddSimpleNameForm addSimpleNameForm,
			BindingResult result, @PathVariable("id") int id,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		if (result.hasErrors()) {
			getEditPriority(id, model);
			return ServletConstants.EDIT_PRIORITY_PAGE;
		}


		Priority priority = getEditPriority(id, model);

		try {
			// update status in db
			priority.setPriorityName(addSimpleNameForm.getName());
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
	public String priorityAddNew(HttpServletRequest request, Model model, ModelMap modelMap) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		AddSimpleNameForm addSimpleNameForm = new AddSimpleNameForm();
		modelMap.put("addSimpleNameForm", addSimpleNameForm);

		return jumpPage(ServletConstants.ADD_NEW_PRIORITY_PAGE, model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String prioritySave(@Valid AddSimpleNameForm addSimpleNameForm,
			BindingResult result, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		if (result.hasErrors()) {
			return ServletConstants.ADD_NEW_PRIORITY_PAGE;
		}

		try {
			// set type in db
			Priority priority = new Priority();
			priority.setPriorityName(addSimpleNameForm.getName());
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

}
