<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="org.training.constants.ServletConstants"%>
<%@ page import="org.training.model.beans.enums.UserRoleEnum"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
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
			<c:out value="${errorMessage}" />
			<hr>
		</c:if>
	</div>
	<div class="main-page">
		<div class="main-header">
			<jsp:include page="/WEB-INF/pages/includies/header.jsp" />
		</div>

		<div class="main">

			<h1>Add New Params:</h1>
			<c:choose>
				<c:when
					test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="add-params">
						<form method="POST" action="<c:url value='/AddParamsController'/>">
							<h3>Add New Project:</h3>
							<table class="add-user-table">
								<tr>
									<td>Project Name:</td>
									<td><input type="text" class="project-name"
										name=<%=ServletConstants.JSP_ADD_PROJECT_NAME%> size="15"></td>
								</tr>
								<tr>
									<td>Description:</td>
									<td><input type="text" class="description-name"
										name=<%=ServletConstants.JSP_ADD_PROJECT_DESCRIPTION%> size="15"></td>
								</tr>
								<tr>
									<td>Build:</td>
									<td><input type="text" class="build-name"
										name=<%=ServletConstants.JSP_ADD_PROJECT_BUILD%> size="15"></td>
								</tr>
								<tr>
									<td>Manager:</td>
									<td>
									<select class="assignee-select" name=<%= ServletConstants.JSP_ADD_PROJECT_MANAGERS%>>
										<c:forEach items="${addProjectManagers}" var="manager">
        									<option id="manager-${manager.userId}" value="${manager.userId}">${manager.firstName} ${manager.lastName} : ${manager.emailAddress}</option>
        									<script type="text/javascript">
        										if ('${manager.userId}' === '${user.userId}') {
													selectedIssue("manager-" + '${manager.userId}');
												}
         									</script>
    									</c:forEach>
    								</select>
									</td>
								</tr>
							</table>
							<h3>Add New Type:</h3>
							<table class="add-user-table">
								<tr>
									<td>Type Name:</td>
									<td><input type="text" class="type-name"
										name=<%=ServletConstants.JSP_ADD_TYPE%> size="15"></td>
								</tr>
							</table>
							<h3>Add New Priority:</h3>
							<table class="add-user-table">
								<tr>
									<td>Priority Name:</td>
									<td><input type="text" class="priority-name"
										name=<%=ServletConstants.JSP_ADD_PRIORITY%> size="15"></td>
								</tr>
							</table>
							<h3>Add New Resolution:</h3>
							<table class="add-user-table">
								<tr>
									<td>Resolution Name:</td>
									<td><input type="text" class="resolution-name"
										name=<%=ServletConstants.JSP_ADD_RESOLUTION%> size="15"></td>
								</tr>
							</table>
							<input class="add-btn" type="submit" value="Add Params">
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<p>
						<font color=&quot#AABBCC&quot>Your are in IssueTracker Add Params page now<br> Please login or go to the <a
							href="<c:url value='/index.jsp'/>">main page</a>.
						</font>
					</p>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="main-aside">
			<jsp:include page="/WEB-INF/pages/includies/aside.jsp" />
		</div>
		<div class="main-footer">
			<jsp:include page="/WEB-INF/pages/includies/footer.jsp" />
		</div>
	</div>



</body>
</html>