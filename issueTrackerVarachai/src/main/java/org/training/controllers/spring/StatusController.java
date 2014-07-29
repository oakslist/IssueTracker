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
import org.training.model.beans.hibbeans.Status;
import org.training.model.hib.ifaces.ITableDataService;
import org.training.model.impls.DaoException;

@Controller
@RequestMapping(value = "/status")
public class StatusController {

	@Autowired
	private ITableDataService commonService;

	@RequestMapping(value = "/all")
	public String showAllStatuses(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			model.addAttribute(ServletConstants.JSP_STATUSES_LIST,
					commonService.getStatuses());
			return jumpPage(ServletConstants.STATUSES_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.STATUSES_PAGE, e.getMessage(),
					model);
		}
	}

	@RequestMapping(value = "/{id}/edit")
	public String statusEdit(@PathVariable("id") int id,
			HttpServletRequest request, Model model,  ModelMap modelMap) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		AddSimpleNameForm addSimpleNameForm = new AddSimpleNameForm();
		modelMap.put("addSimpleNameForm", addSimpleNameForm);
		
		try {
			// get status from db
			Status status = commonService.getStatusById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_STATUS, status);
			return jumpPage(ServletConstants.EDIT_STATUS_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.STATUSES_PAGE, e.getMessage(),
					model);
		}
	}
	
	@RequestMapping(value = "/{id}/edit/save", method = RequestMethod.POST)
	public String statusSave(@Valid AddSimpleNameForm addSimpleNameForm,
			BindingResult result, @PathVariable("id") int id,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE, ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		if (result.hasErrors()) {
			getEditStatus(id, model);
			return ServletConstants.EDIT_STATUS_PAGE;
		}
		
		try {
			//update status in db
			Status status = getEditStatus(id, model);
			status.setStatusName(addSimpleNameForm.getName());
			boolean isUpdated = commonService.updateStatus(status);
			if (isUpdated == true) {
				return jump(ServletConstants.EDIT_STATUS_PAGE, ServletConstants.STATUS_UPDATE_SUCCESSFULLY, model);
			} else {
				//  status not update
				return jump(ServletConstants.EDIT_STATUS_PAGE, ServletConstants.ERROR_STATUS_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return  jump(ServletConstants.EDIT_STATUS_PAGE, e.getMessage(), model);
		}
	}
	
	private Status getEditStatus(int id, Model model) {
		Status status = null;
		try {
			// get status from db
			status = commonService.getStatusById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_STATUS, status);
			jumpPage(ServletConstants.EDIT_STATUS_PAGE, model);
		} catch (DaoException e) {
			jump(ServletConstants.STATUSES_PAGE, e.getMessage(),
					model);
		}
		return status;
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
