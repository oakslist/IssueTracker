<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="org.training.constants.ServletConstants"%>
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

			<h1>Submit Issue</h1>
			<c:choose>
				<c:when test="${user.role.roleName eq 'GUEST'}">
					<p>
						<font color=&quot#AABBCC&quot>Your are in IssueTracker now<br>
							Please login or work as a guest.
						</font>
					</p>
				</c:when>
				<c:when
					test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="add-submit-issue">
						<c:url var="addUrl" value="/issue/add"/>
						<form:form method="post" commandName="issueForm" action="${addUrl}">
							<table class="add-user-table">
								<tr>
									<td>Summary:</td>
									<td><form:input path="summary" /></td>
									<td class="red-text"><span class="error"><form:errors
												path="summary" /></span></td>
								</tr>
								<tr>
									<td>Description:</td>
									<td><form:input path="description" /></td>
									<td class="red-text"><span class="error"><form:errors
												path="description" /></span></td>
								</tr>
								<tr>
									<td>Status:</td>
									<td><form:select path="status">
											<c:forEach items="${issueStatuses}" var="status">
												<form:option value="${status.statusName}"
													label="${status.statusName}" />
											</c:forEach>
										</form:select></td>
									<td class="red-text"><span class="error"><form:errors
												path="status" /></span></td>
								</tr>
								<tr>
									<td>Type:</td>
									<td><form:select path="type">
											<c:forEach items="${issueTypes}" var="type">
												<form:option value="${type.typeName}"
													label="${type.typeName}" />
											</c:forEach>
										</form:select></td>
									<td class="red-text"><span class="error"><form:errors
												path="type" /></span></td>
								</tr>
								<tr>
									<td>Priority:</td>
									<td><form:select path="priority">
											<c:forEach items="${issuePriorities}" var="priority">
												<form:option value="${priority.priorityName}"
													label="${priority.priorityName}" />
											</c:forEach>
										</form:select></td>
									<td class="red-text"><span class="error"><form:errors
												path="priority" /></span></td>
								</tr>
								<tr>
									<td>Project:</td>
									<td><form:select path="project">
											<c:forEach items="${issueProjects}" var="project">
												<form:option value="${project.projectName}"
													label="${project.projectName}" />
											</c:forEach>
										</form:select></td>
									<td class="red-text"><span class="error"><form:errors
												path="project" /></span></td>
								</tr>
								<tr>
									<td>Build Found:</td>
									<td><form:select path="buildFound">
											<c:forEach items="${issueProjectBuilds}" var="build">
												<form:option value="${build.buildValue}"
													label="${build.buildValue}" />
											</c:forEach>
										</form:select></td>
									<td class="red-text"><span class="error"><form:errors
												path="buildFound" /></span></td>
								</tr>
								<tr>
									<td>Assignee:</td>
									<td>
									<form:select path="assignee">									
											<c:forEach items="${issueAssignees}" var="assignee">
												<form:option value="${assignee.userId}"
 													label="${assignee.firstName} ${assignee.lastName} : ${assignee.emailAddress}" />
 											</c:forEach>
 										</form:select>
										
										</td>
									<td class="red-text"><span class="error"><form:errors
												path="assignee" /></span></td>
								</tr>
							</table>
							<input class="add-btn" type="submit" value="Add Issue">
						</form:form>
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