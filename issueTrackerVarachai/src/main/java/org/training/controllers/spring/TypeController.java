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
import org.training.model.beans.hibbeans.Type;
import org.training.model.hib.ifaces.ITableDataService;
import org.training.model.impls.DaoException;

@Controller
@RequestMapping(value = "/type")
public class TypeController {

	@Autowired
	private ITableDataService commonService;

	@RequestMapping(value = "/all")
	public String showAllTypes(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		// get data from db
		try {
			model.addAttribute(ServletConstants.JSP_TYPES_LIST,
					commonService.getTypes());
			return jumpPage(ServletConstants.TYPES_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/edit")
	public String typeEdit(@PathVariable("id") int id,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		try {
			// get status from db
			Type type = commonService.getTypeById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_TYPE, type);
			return jumpPage(ServletConstants.EDIT_TYPE_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.TYPES_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/{id}/save", method = RequestMethod.POST)
	public String typeChangeSave(@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_EDIT_TYPE) String typeName,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		Type type = getEditType(id, model);

		String inputResult = getInputResultSave(typeName);
		if (inputResult != null) {
			return jump(ServletConstants.EDIT_TYPE_PAGE, inputResult, model);
		}

		try {
			// update status in db
			type.setTypeName(typeName);
			boolean isUpdated = commonService.updateType(type);
			if (isUpdated == true) {
				return jump(ServletConstants.EDIT_TYPE_PAGE,
						ServletConstants.TYPE_UPDATE_SUCCESSFULLY, model);
			} else {
				// status not update
				return jump(ServletConstants.EDIT_TYPE_PAGE,
						ServletConstants.ERROR_TYPE_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_TYPE_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/add")
	public String typeAddNew(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		return jumpPage(ServletConstants.ADD_NEW_TYPE_PAGE, model);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String typeSave(
			@RequestParam(ServletConstants.JSP_ADD_TYPE) String typeName,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jump(ServletConstants.INDEX_PAGE,
					ServletConstants.ERROR_NULL_SESSION, model);
		}

		String inputResult = getInputResultSave(typeName);
		if (inputResult != null) {
			return jump(ServletConstants.ADD_NEW_TYPE_PAGE, inputResult, model);
		}

		try {
			// set type in db
			Type type = new Type();
			type.setTypeName(typeName);
			boolean isSet = commonService.setType(type);
			if (isSet == true) {
				return jump(ServletConstants.ADD_NEW_TYPE_PAGE,
						ServletConstants.TYPE_UPDATE_SUCCESSFULLY, model);
			} else {
				// type not update
				return jump(ServletConstants.ADD_NEW_TYPE_PAGE,
						ServletConstants.ERROR_TYPE_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.ADD_NEW_TYPE_PAGE, e.getMessage(),
					model);
		}
	}

	private Type getEditType(int id, Model model) {
		Type type = null;
		try {
			// get status from db
			type = commonService.getTypeById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_TYPE, type);
			jumpPage(ServletConstants.EDIT_TYPE_PAGE, model);
		} catch (DaoException e) {
			jump(ServletConstants.EDIT_TYPE_PAGE, e.getMessage(), model);
		}
		return type;
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

	private String getInputResultSave(String typeName) {
		if (typeName == null || typeName.equals("")) {
			return ServletConstants.ERROR_TYPE_NAME_EMPTY;
		}
		return null;
	}

}
