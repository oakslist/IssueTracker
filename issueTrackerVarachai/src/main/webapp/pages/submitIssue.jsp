<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.training.constants.ServletConstants" %>
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

			<h1>Submit Issue</h1>
			<c:choose>
				<c:when test="${user.role eq 'GUEST'}">
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker now<br>
					Please login or work as a guest.</font></p>
				</c:when>
				<c:when test="${user.role eq 'USER' || user.role eq 'ADMINISTRATOR'}">
					<div class="add-submit-issue">
						<form method="POST" action="<c:url value='/SubmitIssueController'/>">
							<table class="add-issue-table">
								<tr>
									<td>Summary:</td>
									<td><input type="text" class="summary-select" name=<%= ServletConstants.JSP_SUMMARY%> size="15"></td>
								</tr>
								<tr>
									<td>Description:</td>
									<td><input type="text" class="description-select" name=<%= ServletConstants.JSP_DESCRIPTION%> size="15"></td>
								</tr>
								<tr>
									<td>Status:</td>
									<td>
									<select class="status-select" name=<%= ServletConstants.JSP_STATUS%>>
										<c:forEach items="${issueStatuses}" var="status">
        									<option value="${status.key}">${status.value}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Type:</td>
									<td>
									<select class="type-select" name=<%= ServletConstants.JSP_TYPE%>>
										<c:forEach items="${issueTypes}" var="type">
        									<option value="${type.key}">${type.value}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Priority:</td>
									<td>
									<select class="priority-select" name=<%= ServletConstants.JSP_PRIORITY%>>
										<c:forEach items="${issuePriorities}" var="priority">
        									<option value="${priority.key}" class="color-${priority.key}">${priority.value}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Project:</td>
									<td>
									<select class="project-select" name=<%= ServletConstants.JSP_PROJECT%>>
										<c:forEach items="${issueProjects}" var="project">
        									<option value="${project.key}">${project.value.name}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Build Found:</td>
									<td>
									<select class="build-select" name=<%= ServletConstants.JSP_BUILD_FOUND%>>
										<c:forEach items="${issueProjectBuilds}" var="build">
        									<option value="${build.key}">${build.value}</option>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Assignee:</td>
									<td>
									<select class="assignee-select" name=<%= ServletConstants.JSP_ASSIGNEE%>>
										<c:forEach items="${issueAssignees}" var="assignee">
        									<option value="${assignee.key}">${assignee.value}</option>
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
			<jsp:include page="/pages/includies/aside.jsp" />
		</div>
		<div class="main-footer">
			<jsp:include page="/pages/includies/footer.jsp" />
		</div>
	</div>



</body>
</html>