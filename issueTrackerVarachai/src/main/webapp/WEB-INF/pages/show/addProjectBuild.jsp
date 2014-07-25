<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="org.training.constants.ServletConstants" %>
<%@ page import="org.training.model.beans.enums.UserRoleEnum" %>
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
			<c:out value="${errorMessage}"/><hr>
		</c:if>
	</div>
	<div class="main-page">
		<div class="main-header">
			<jsp:include page="/WEB-INF/pages/includies/header.jsp" />
		</div>

		<div class="main">

			<h1>Add New Project Build:</h1>
			<c:choose>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="add-submit-issue">
					<p>Current project: <b>${editProject.projectName}</b></p>					
						<form method="POST" action="<c:url value='/project/${editProject.id}/build/save'/>">
							<table class="add-user-table">
								<tr>
									<td>Add New Build:</td>
									<td><input type="text" class="first-name" name=<%= ServletConstants.JSP_ADD_BUILD%> size="15"></td>
								</tr>
							</table>
							<input class="add-btn" type="submit" value="Add Build">
							<input type="hidden" id="hidden3" value="${editProject.id}" name="hidden3">
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker Add project build page now<br>
					Please login or go to the <a href="<c:url value='/index.jsp'/>">main page</a>.</font></p>
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