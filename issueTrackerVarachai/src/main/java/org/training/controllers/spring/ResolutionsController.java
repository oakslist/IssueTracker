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
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

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

	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
	public String resolutionChangeSave(
			@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_EDIT_RESOLUTION) String resolutionName,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		Resolution resolution = getEditResolution(id, model);

		String inputResult = getInputResultSave(resolutionName);
		if (inputResult != null) {
			return jump(ServletConstants.EDIT_RESOLUTION_PAGE, inputResult,
					model);
		}

		try {
			// update status in db
			resolution.setResolutionName(resolutionName);
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
	public String resolutionAddNew(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		return jumpPage(ServletConstants.ADD_NEW_RESOLUTION_PAGE, model);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String resolutionSave(
			@RequestParam(ServletConstants.JSP_ADD_RESOLUTION) String resolutionName,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		String inputResult = getInputResultSave(resolutionName);
		if (inputResult != null) {
			return jump(ServletConstants.ADD_NEW_RESOLUTION_PAGE, inputResult,
					model);
		}

		try {
			// set type in db
			Resolution resolution = new Resolution();
			resolution.setResolutionName(resolutionName);
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

	private String getInputResultSave(String resolutionName) {
		if (resolutionName == null || resolutionName.equals("")) {
			return ServletConstants.ERROR_RESOLUTION_NAME_EMPTY;
		}
		return null;
	}

}
