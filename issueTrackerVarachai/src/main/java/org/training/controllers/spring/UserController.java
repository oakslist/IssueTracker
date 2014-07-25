package org.training.controllers.spring;

import java.util.ArrayList;
import java.util.List;

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
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Role;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.ifaces.IRoleService;
import org.training.model.hib.ifaces.IUserService;
import org.training.model.impls.DaoException;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@RequestMapping(value = "/{id}/menu")
	public String userMenu(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		return ServletConstants.USER_MENU_PAGE;
	}

	@RequestMapping(value = "/all")
	public String userAll(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		List<User> usersList = new ArrayList<User>();

		try {
			// get all users from db
			usersList = userService.getExistUsers();
			model.addAttribute(ServletConstants.JSP_USER_LIST, usersList);
			return jump(ServletConstants.SEARCH_USER_PAGE, "", model);
		} catch (DaoException e) {
			return jump(ServletConstants.MAIN_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "/add")
	public String addUserPage(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		return ServletConstants.ADD_USER_PAGE;
	}

	@RequestMapping(value = "/add/save", method = RequestMethod.POST)
	public String saveNewUser(
			@RequestParam(ServletConstants.JSP_FIRST_NAME) String firstName,
			@RequestParam(ServletConstants.JSP_LAST_NAME) String lastName,
			@RequestParam(ServletConstants.JSP_EMAIL_ADDRESS) String emailAddress,
			@RequestParam(ServletConstants.JSP_ROLE) String role,
			@RequestParam(ServletConstants.JSP_PASSWORD) String password,
			@RequestParam(ServletConstants.JSP_PASSWORD_CONFIRMATION) String passwordConfirmation,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		String inputResult = getInputResultNewUser(firstName, lastName,
				emailAddress, role, password, passwordConfirmation);
		if (inputResult != null) {
			return jump(ServletConstants.ADD_USER_PAGE, inputResult, model);
		}

		User newUser = new User();

		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmailAddress(emailAddress);
		newUser.setPassword(password);

		try {
			// get Role from db
			Role curRole = roleService.getExistRole(UserRoleEnum.valueOf(role));
			newUser.setRole(curRole);

			// set user in db
			boolean isSet = userService.addNewUser(newUser);
			if (isSet == true) {
				return jump(ServletConstants.ADD_USER_PAGE,
						ServletConstants.USER_ADD_SUCCESSFULLY, model);
			} else {
				// user not add
				return jump(ServletConstants.ADD_USER_PAGE,
						ServletConstants.ERROR_USER_NOT_ADD, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.ADD_USER_PAGE, e.getMessage(), model);
		}

	}

	@RequestMapping(value = "{id}/edit/user")
	public String editUser(@PathVariable("id") int id,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);
		
		if (session == null) {
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		try {
			// get all users from db
			User user = userService.getUserById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_USER_BY_ID, user);
			return jumpPage(ServletConstants.EDIT_USER_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_USER_PAGE, e.getMessage(), model);
		}
	}

	@RequestMapping(value = "{id}/edit/password")
	public String editPassword(@PathVariable("id") int id, 
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}
		
		try {
			// get all users from db
			User user = userService.getUserById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_USER_BY_ID, user);
			return jumpPage(ServletConstants.EDIT_PASSWORD_PAGE, model);
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_PASSWORD_PAGE, e.getMessage(), model);
		}

	}

	@RequestMapping(value = "{id}/edit/user/save", method = RequestMethod.POST)
	public String saveEditUser(
			@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_FIRST_NAME) String firstName,
			@RequestParam(ServletConstants.JSP_LAST_NAME) String lastName,
			@RequestParam(ServletConstants.JSP_EMAIL_ADDRESS) String emailAddress,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}
		getUserFromDB(id, model);
		String inputResult = getInputResultUser(firstName, lastName,
				emailAddress);
		if (inputResult != null) {
			getUserFromDB(id, model);
			return jump(ServletConstants.EDIT_USER_PAGE, inputResult, model);
		}

		try {
			// update user in db
			User editUser = userService.getUserById(id);
			editUser.setFirstName(firstName);
			editUser.setLastName(lastName);
			editUser.setEmailAddress(emailAddress);
			boolean isUpdated = userService.updateUser(editUser);
			getUserFromDB(id, model);
			if (isUpdated == true) {
				return jump(ServletConstants.EDIT_USER_PAGE,
						ServletConstants.USER_UPDATE_SUCCESSFULLY, model);
			} else {
				// user not found
				return jump(ServletConstants.EDIT_USER_PAGE,
						ServletConstants.ERROR_USER_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_USER_PAGE, e.getMessage(), model);
		}

	}

	@RequestMapping(value = "{id}/edit/password/save", method = RequestMethod.POST)
	public String saveEditPassword(
			@PathVariable("id") int id,
			@RequestParam(ServletConstants.JSP_PASSWORD) String password,
			@RequestParam(ServletConstants.JSP_PASSWORD_CONFIRMATION) String passwordConfirmation,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

	
		String inputResult = getInputResultPassword(password,
				passwordConfirmation);
		if (inputResult != null) {
			getUserFromDB(id, model);
			return jump(ServletConstants.EDIT_PASSWORD_PAGE, inputResult, model);
		}

		try {
			User editUser = userService.getUserById(id);
			editUser.setPassword(password);
			// save user in db
			boolean isUpdated = userService.updateUserPassword(editUser);
			getUserFromDB(id, model);
			if (isUpdated == true) {
				return jump(ServletConstants.EDIT_PASSWORD_PAGE,
						ServletConstants.PASSWORD_UPDATE_SUCCESSFULLY, model);
			} else {
				// password not update
				return jump(ServletConstants.EDIT_PASSWORD_PAGE,
						ServletConstants.ERROR_PASSWORD_NOT_UPDATE, model);
			}
		} catch (DaoException e) {
			return jump(ServletConstants.EDIT_PASSWORD_PAGE, e.getMessage(),
					model);
		}

	}
	
	private User getUserFromDB(int id, Model model) {
		User user = null;
		try {
			// get all users from db
			user = userService.getUserById(id);
			model.addAttribute(ServletConstants.JSP_EDIT_USER_BY_ID, user);
			jumpPage(ServletConstants.EDIT_PASSWORD_PAGE, model);
		} catch (DaoException e) {
			jump(ServletConstants.EDIT_PASSWORD_PAGE, e.getMessage(), model);
		}
		return user;
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

	protected String jumpError(String message, Model model) {
		return jump(ServletConstants.INDEX_PAGE, message, model);
	}

	private String getInputResultUser(String firstName, String lastName,
			String emailAddress) {
		if (firstName == null || firstName.equals("")) {
			return ServletConstants.ERROR_FIRST_NAME_EMPTY;
		}
		if (lastName == null || lastName.equals("")) {
			return ServletConstants.ERROR_LAST_NAME_EMPTY;
		}
		if (emailAddress == null || emailAddress.equals("")) {
			return ServletConstants.ERROR_EMAIL_EMPTY;
		}
		return null;
	}

	private String getInputResultPassword(String password,
			String passwordConfirmation) {
		if (password == null || password.equals("")) {
			return ServletConstants.ERROR_PASSWORD_EMPTY;
		}
		if (passwordConfirmation == null || passwordConfirmation.equals("")) {
			return ServletConstants.ERROR_PASSWORD_CONFIRM_EMPTY;
		}
		if (!password.equals(passwordConfirmation)) {
			return ServletConstants.ERROR_PASSWORDS_NOT_EQUAL;
		}
		return null;
	}

	private String getInputResultNewUser(String firstName, String lastName,
			String emailAddress, String role, String password,
			String passwordConfirmation) {
		if (firstName == null || firstName.equals("")) {
			return ServletConstants.ERROR_FIRST_NAME_EMPTY;
		}
		if (lastName == null || lastName.equals("")) {
			return ServletConstants.ERROR_LAST_NAME_EMPTY;
		}
		if (emailAddress == null || emailAddress.equals("")) {
			return ServletConstants.ERROR_EMAIL_EMPTY;
		}
		if (role == null || role.equals("")) {
			return ServletConstants.ERROR_ROLE_EMPTY;
		}
		if (password == null || password.equals("")) {
			return ServletConstants.ERROR_PASSWORD_EMPTY;
		}
		if (passwordConfirmation == null || passwordConfirmation.equals("")) {
			return ServletConstants.ERROR_PASSWORD_CONFIRM_EMPTY;
		}
		if (!password.equals(passwordConfirmation)) {
			return ServletConstants.ERROR_PASSWORDS_NOT_EQUAL;
		}
		return null;
	}

}
