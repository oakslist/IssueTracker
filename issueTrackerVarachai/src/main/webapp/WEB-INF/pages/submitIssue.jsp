<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="org.training.constants.ServletConstants" %>
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

			<h1>Submit Issue</h1>
			<c:choose>
				<c:when test="${user.role.roleName eq 'GUEST'}">
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker now<br>
					Please login or work as a guest.</font></p>
				</c:when>
				<c:when test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="add-submit-issue">
						<form method="POST" action="<c:url value='/issue/add/save'/>">
							<table class="add-issue-table">
								<tr>
									<td>Summary:</td>
									<td><input type="text" class="summary-select" name=<%= ServletConstants.JSP_SUMMARY%> size="15"></td>
								</tr>
								<tr>
									<td>Description:</td>
									<td><textarea class="description-select" name=<%= ServletConstants.JSP_DESCRIPTION%>></textarea></td>
								</tr>
								<tr>
									<td>Status:</td>
									<td>
									<select class="status-select" name=<%= ServletConstants.JSP_STATUS%>>
										<c:forEach items="${issueStatuses}" var="status">
        									<option value="${status.statusName}">${status.statusName}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Type:</td>
									<td>
									<select class="type-select" name=<%= ServletConstants.JSP_TYPE%>>
										<c:forEach items="${issueTypes}" var="type">
        									<option value="${type.typeName}">${type.typeName}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Priority:</td>
									<td>
									<select class="priority-select" name=<%= ServletConstants.JSP_PRIORITY%>>
										<c:forEach items="${issuePriorities}" var="priority">
        									<option value="${priority.priorityName}" class="color-${priority.priorityName}">${priority.priorityName}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Project:</td>
									<td>
									<select class="project-select" name=<%= ServletConstants.JSP_PROJECT%>>
										<c:forEach items="${issueProjects}" var="project">
        									<option value="${project.projectName}">${project.projectName}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Build Found:</td>
									<td>
									<select class="build-select" name=<%= ServletConstants.JSP_BUILD_FOUND%>>
										<c:forEach items="${issueProjectBuilds}" var="build">
        									<option value="${build.buildValue}">${build.buildValue}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Assignee:</td>
									<td>
									<select class="assignee-select" name=<%= ServletConstants.JSP_ASSIGNEE%>>
										<c:forEach items="${issueAssignees}" var="assignee">
        									<option value="${assignee.userId}">${assignee.firstName} ${assignee.lastName} : ${assignee.emailAddress}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
							</table>
							<input class="add-btn" type="submit" value="Add Issue">
						</form>
					</div>
				</c:when>
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