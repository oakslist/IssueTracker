<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.training.constants.ServletConstants"%>
<%@ page import="org.training.model.beans.enums.UserRoleEnum"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/pages/style/style.css'/>">
<script type="text/javascript" src="<c:url value='/pages/js/script.js'/>"></script>
<title>IssueTracker</title>
</head>
<body>
	<div class="error-message">
		<c:if test="${not empty errorMessage}">
			<c:out value="${errorMessage}" />
			<hr>
		</c:if>
	</div>
	<div class="main-page">
		<div class="main-header">
			<jsp:include page="/pages/includies/header.jsp" />
		</div>

		<div class="main">

			<h1>Edit User</h1>
			<c:choose>
				<c:when test="${not empty editUser}">
					<c:choose>
						<c:when test="${user.role.roleName eq 'ADMINISTRATOR'}">
							<div class="edit-user">
								<form method="POST" action="<c:url value='/EditUserController'/>">
									<table class="add-user-table">
										<tr>
											<td>First Name:</td>
											<td><input type="text" class="first-name"
												name=<%= ServletConstants.JSP_FIRST_NAME%> size="15"
												value="${editUser.firstName}"></td>
										</tr>
										<tr>
											<td>Last Name:</td>
											<td><input type="text" class="last-name"
												name=<%= ServletConstants.JSP_LAST_NAME%> size="15"
												value="${editUser.lastName}"></td>
										</tr>
										<tr>
											<td>Email Address:</td>
											<td><input type="text" class="email-address"
												name=<%= ServletConstants.JSP_EMAIL_ADDRESS%> size="15"
												value="${editUser.emailAddress}"></td>
										</tr>
										<tr>
											<td>Role:</td>
											<td><select class="role" name=<%=ServletConstants.JSP_ROLE%>>
													<c:forEach items="<%=UserRoleEnum.values()%>" var="role">
														<option id="${role.userRole}" value="${role.userRole}">${role.userRole}</option>
														<script type="text/javascript">
															if ('${role.userRole}' === '${editUser.role}') {
																selectedOption('${role.userRole}');
															}
														</script>
													</c:forEach>
											</select></td>
										</tr>
									</table>
									<input class="add-btn" type="submit" value="Edit User">
								</form>
							</div>
						</c:when>
						<c:otherwise>
							<p>
								<font color=&quot#AABBCC&quot>Your are in IssueTracker
									Edit User page now<br> Please login or go to the 
									<a href="<c:url value='/index.jsp'/>">main page</a>.
								</font>
							</p>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when
							test="${user.role.roleName eq 'ADMINISTRATOR' || user.role.roleName eq 'USER'}">
							<div class="edit-user">
								<form method="POST" action="<c:url value='/EditUserController'/>">
									<table class="add-user-table">
										<tr>
											<td>First Name:</td>
											<td><input type="text" class="first-name"
												name=<%= ServletConstants.JSP_FIRST_NAME%> size="15"
												value="${user.firstName}"></td>
										</tr>
										<tr>
											<td>Last Name:</td>
											<td><input type="text" class="last-name"
												name=<%= ServletConstants.JSP_LAST_NAME%> size="15"
												value="${user.lastName}"></td>
										</tr>
										<tr>
											<td>Email Address:</td>
											<td><input type="text" class="email-address"
												name=<%= ServletConstants.JSP_EMAIL_ADDRESS%> size="15"
												value="${user.emailAddress}"></td>
										</tr>
										<c:choose>
											<c:when test="${user.role eq 'ADMINISTRATOR'}">
												<tr>
													<td>Role:</td>
													<td><select class="role" name=<%=ServletConstants.JSP_ROLE%>>
															<c:forEach items="<%=UserRoleEnum.values()%>" var="role">
																<option id="${role.userRole}" value="${role.userRole}">${role.userRole}</option>
																<script type="text/javascript">
																	if ('${role.userRole}' === '${user.role}') {
																		selectedOption('${role.userRole}');
																	}
																</script>
															</c:forEach>
													</select></td>
												</tr>
											</c:when>
										</c:choose>
									</table>
									<input class="add-btn" type="submit" value="Edit User">
								</form>
							</div>
						</c:when>
						<c:otherwise>
							<p>
								<font color=&quot#AABBCC&quot>Your are in IssueTracker
									Edit User page now<br> Please login or go to the <a
									href="<c:url value='/index.jsp'/>">main page</a>.
								</font>
							</p>
						</c:otherwise>

					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="main-aside">
			<jsp:include page="/pages/includies/aside.jsp" />
		</div>
		<div class="main-footer">
			<jsp:include page="/pages/includies/footer.jsp" />
		</div>
	</div>



</body>
</html>