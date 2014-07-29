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
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.hib.ifaces.ITableDataService;
import org.training.model.impls.DaoException;

@Controller
@RequestMapping(value = "/resolution")
public class ResolutionsController {

	@Autowired
	private ITableDataService commonService;

	@RequestMapping(value = "/all")
	public String showAllResolutions(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			model.addAttribute(ServletConstants.JSP_RESOLUTIONS_LIST,
					commonService.getResolutions());
			return jumpPage(ServletConstants.RESOLUTIONS_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/edit")
	public String resolutionEdit(@PathVariable("id") int id,
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
			Resolution resolution = commonService.getResolutionById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_RESOLUTION, resolution);
			return jumpPage(ServletConstants.EDIT_RESOLUTION_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.RESOLUTIONS_PAGE, e.getMessage(),
					model);
		}
	}

	@RequestMapping(value = "/{id}/edit/save", method = RequestMethod.POST)
	public String resolutionChangeSave(@Valid AddSimpleNameForm addSimpleNameForm,
			BindingResult result, @PathVariable("id") int id,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		if (result.hasErrors()) {
			getEditResolution(id, model);
			return ServletConstants.EDIT_RESOLUTION_PAGE;
		}

		Resolution resolution = getEditResolution(id, model);

		try {
			// update status in db
			resolution.setResolutionName(addSimpleNameForm.getName());
			boolean isUpdated = commonService.updateResolution(resolution);
			if (isUpdated == true) {
				return jump(ServletConstants.EDIT_RESOLUTION_PAGE,
						ServletConstants.RESOLUTION_UPDATE_SUCCESSFULLY, model);
			} else {
				// status not update
				return jump(ServletConstants.EDIT_RESOLUTION_PAGE,
						ServletConstants.ERROR_RESOLUTION_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_RESOLUTION_PAGE, e.getMessage(),
					model);
		}
	}

	@RequestMapping(value = "/add")
	public String resolutionAddNew(HttpServletRequest request, Model model, ModelMap modelMap) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		AddSimpleNameForm addSimpleNameForm = new AddSimpleNameForm();
		modelMap.put("addSimpleNameForm", addSimpleNameForm);

		return jumpPage(ServletConstants.ADD_NEW_RESOLUTION_PAGE, model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String resolutionSave(@Valid AddSimpleNameForm addSimpleNameForm,
			BindingResult result, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		if (result.hasErrors()) {
			return ServletConstants.ADD_NEW_RESOLUTION_PAGE;
		}

		try {
			// set type in db
			Resolution resolution = new Resolution();
			resolution.setResolutionName(addSimpleNameForm.getName());
			boolean isSet = commonService.setResolution(resolution);
			if (isSet == true) {
				return jump(ServletConstants.ADD_NEW_RESOLUTION_PAGE,
						ServletConstants.RESOLUTION_UPDATE_SUCCESSFULLY, model);
			} else {
				// type not update
				return jump(ServletConstants.ADD_NEW_RESOLUTION_PAGE,
						ServletConstants.ERROR_RESOLUTION_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.ADD_NEW_RESOLUTION_PAGE,
					e.getMessage(), model);
		}
	}

	private Resolution getEditResolution(int id, Model model) {
		Resolution resolution = null;
		try {
			// get status from db
			resolution = commonService.getResolutionById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_RESOLUTION, resolution);
			jumpPage(ServletConstants.EDIT_RESOLUTION_PAGE, model);
		} catch (DaoException e) {
			jump(ServletConstants.EDIT_RESOLUTION_PAGE, e.getMessage(), model);
		}
		return resolution;
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
