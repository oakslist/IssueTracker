<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.training.constants.ServletConstants" %>
<%@ page import="org.training.model.beans.enums.UserRoleEnum" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value='/pages/style/style.css'/>">
<title>IssueTracker</title>
<script language="JavaScript">

	function loskSelectBuild() {
		var order = document.getElementByClassName("build-select");
		order.disabled = 'disabled';
	}
	
	function unlockSelectBuild() {
		var order = document.getElementByClassName("build-select");
		order.disabled = 'enable';
	}

</script>
</head>
<body>
	<div class="error-message">
		<c:if test="${not empty errorMessage}">
			<c:out value="${errorMessage}"/><hr>
		</c:if>
	</div>
	<div class="main-page">
		<div class="main-header">
			<jsp:include page="/pages/includies/header.jsp" />
		</div>

		<div class="main">

			<h1>Add New User</h1>
			<c:choose>
				<c:when test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="add-submit-issue">
						<form method="POST" action="<c:url value='/AddUserController'/>">
							<table class="add-user-table">
								<tr>
									<td>First Name:</td>
									<td><input type="text" class="first-name" name=<%= ServletConstants.JSP_FIRST_NAME%> size="15"></td>
								</tr>
								<tr>
									<td>Last Name:</td>
									<td><input type="text" class="last-name" name=<%= ServletConstants.JSP_LAST_NAME%> size="15"></td>
								</tr>
								<tr>
									<td>Email Address:</td>
									<td><input type="text" class="email-address" name=<%= ServletConstants.JSP_EMAIL_ADDRESS%> size="15"></td>
								</tr>
								<tr>
									<td>Role:</td>
									<td>
									<select class="role" name=<%= ServletConstants.JSP_ROLE%>>
										<c:forEach items="<%= UserRoleEnum.values()%>" var="role">
        									<option value="${role.userRole}">${role.userRole}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Password:</td>
									<td><input type="password" class="password" name=<%= ServletConstants.JSP_PASSWORD%> size="15"></td>
								</tr>
								<tr>
									<td>Password Confirmation:</td>
									<td><input type="password" class="password-confirmation" 
										name=<%= ServletConstants.JSP_PASSWORD_CONFIRMATION%> size="15"></td>
								</tr>
							</table>
							<input class="add-btn" type="submit" value="Add User">
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker Add User page now<br>
					Please login or go to the <a href="<c:url value='/index.jsp'/>">main page</a>.</font></p>
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