package org.training.controllers.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.training.constants.ServletConstants;
import org.training.form.LoginForm;
import org.training.model.beans.enums.UserRoleEnum;
import org.training.model.beans.hibbeans.Issue;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.ifaces.IDefaultDataTables;
import org.training.model.hib.ifaces.IIssueService;
import org.training.model.hib.ifaces.IUserService;
import org.training.model.impls.DaoException;

@Controller
public class MainController {

	private static final Logger LOG = Logger.getLogger(MainController.class);

	private static boolean isTableNotCreated = true;

	@Autowired
	private IDefaultDataTables defaultDataTables;

	@Autowired
	private IIssueService issueService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/index")
	public String indexPage(HttpServletRequest request, Model model,
			ModelMap modelMap) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			session.setAttribute(ServletConstants.JSP_USER, new User());
		}

		// create default data in the tables
		if (isTableNotCreated) {
			try {
				defaultDataTables.init();
				isTableNotCreated = false;
			} catch (Exception e) {
				System.out.println("This is Error message " + e);
				LOG.error("This is Error message ", e);
			}
		}

		getAllIssues(session, model);

		LoginForm loginForm = new LoginForm();
		modelMap.put("loginForm", loginForm);

		return ServletConstants.MAIN_PAGE;
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String indexPage(HttpServletRequest request, Model model,
			ModelMap modelMap, @Valid LoginForm loginForm, BindingResult result) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		if (result.hasErrors()) {
			getAllIssues(session, model);
			return ServletConstants.MAIN_PAGE;
		}

		String emailAddress = loginForm.getEmail();
		String password = loginForm.getPassword();
		
		// check password and email in bd
		try {
			// get exist user
			User user = userService.getExistUser(emailAddress, password);
			if (user != null) {
				// write data in session
				session.setAttribute(ServletConstants.JSP_USER, user);
				// jump to main.jsp
				jumpPage(ServletConstants.INDEX_PAGE, model);
			} else {
				// user not found
				getAllIssues(session, model);
				return jump(ServletConstants.MAIN_PAGE,
						ServletConstants.ERROR_USER_NOT_FOUND, model);
			}
		} catch (DaoException e) {
			return jumpError(e.getMessage(), model);
		}

		return ServletConstants.INDEX_PAGE;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return jumpError(ServletConstants.ERROR_NULL_SESSION, model);
		}

		session.setAttribute(ServletConstants.JSP_USER, null);

		return ServletConstants.INDEX_PAGE;
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
		return jump(ServletConstants.MAIN_PAGE, message, model);
	}

	private void getAllIssues(HttpSession session, Model model) {
		// get all issues from db
		List<Issue> issuesList = new ArrayList<Issue>();
		try {
			// IIssueDAOHib issueDAO = IssueFactoryHib.getClassFromFactory();
			System.out
					.println("session.getAttribute(ServletConstants.JSP_USER) = "
							+ session.getAttribute(ServletConstants.JSP_USER));
			User curUser = (User) session
					.getAttribute(ServletConstants.JSP_USER);
			if (curUser == null
					|| curUser.getRole().getRoleName()
							.equals(UserRoleEnum.GUEST.toString())) {
				issuesList = issueService.getAllIssues();
			} else {
				if (curUser.getRole().getRoleName()
						.equals(UserRoleEnum.USER.toString())
						|| curUser.getRole().getRoleName()
								.equals(UserRoleEnum.ADMINISTRATOR.toString())) {
					issuesList = issueService
							.getUserIssues(curUser.getUserId());
				}
			}
			// write data in session
			model.addAttribute(ServletConstants.JSP_ISSUES_LIST, issuesList);
		} catch (DaoException e) {
			jumpError(e.getMessage(), model);
		}
	}

}
