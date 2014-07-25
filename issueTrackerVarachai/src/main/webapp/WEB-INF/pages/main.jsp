<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.training.constants.ServletConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/script.js" />"></script>
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
			<jsp:include page="/WEB-INF/pages/includies/header.jsp" />
		</div>

		<div class="main">
			<%-- 			<h1>Message : ${msg}</h1> --%>
			<h1>Issue Tracker</h1>
			<div class="search-btn">
				<input value="Search" onclick="#'" type="button">
			</div>
			<c:choose>
				<c:when test="${user.role.roleName eq 'GUEST'}">
					<p>
						<font color=&quot#AABBCC&quot>Your are in IssueTracker now<br>
							Please login or work as a guest.
						</font>
					</p>
				</c:when>
				<c:when test="${user.role.roleName eq 'USER'}">
					<div class="submit-issue-btn">
						<input value="Submit Issue"
							onclick="location.href='<c:url value='/issue/add'/>'"
							type="button">
					</div>
				</c:when>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="submit-issue-btn">
						<input value="Submit Issue"
							onclick="location.href='<c:url value='/issue/add'/>'"
							type="button">
					</div>
					<div class="admin-links">
						<div class="pages-link-admin">
							<p>Pages:</p>
							<a href="<c:url value='/project/all'/>">Projects</a>
							<a href="<c:url value='/status/all'/>">Statuses</a>
							<a href="<c:url value='/resolution/all'/>">Resolutions</a>
							<a href="<c:url value='/priority/all'/>">Priorities</a>
							<a href="<c:url value='/type/all'/>">Types</a>
						</div>
						<div class="add-pages-link-admin">
							<p>Add Pages:</p>
							<a href="<c:url value='/project/add'/>">Add
								Project</a> <a href="<c:url value='/resolution/add'/>">Add
								Resolution</a> <a href="<c:url value='/priority/add'/>">Add
								Priority</a> <a href="<c:url value='/type/add'/>">Add
								Type</a>
						</div>
						<div class="pages-link-user-admin">
							<p>Work with User:</p>
							<a href="<c:url value='/user/all'/>">Search
								User</a>
							 <a href="<c:url value='/user/add'/>" title="Add User">Add User</a>
						</div>
					</div>
				</c:when>
			</c:choose>

			<div class="main-issues">
				<form action="<c:url value='/issue/'/>"
					method="POST">
				</form>
				<table class="table-issues">
					<thead>
						<tr>
							<th class="table-id-name">Id</th>
							<th>Priority</th>
							<th>Assignee</th>
							<th>Type</th>
							<th>Status</th>
							<th>Summary</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${issuesList}" var="issue">
							<tr>
								<c:choose>
									<c:when
										test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
										<td class="table-id"><a
											href="javascript:myEditParams('${issue.id}','edit')">${issue.id}</a></td>
									</c:when>
									<c:otherwise>
										<td class="table-id">${issue.id}</td>
									</c:otherwise>
								</c:choose>
								<td class="color-${issue.priority.priorityName}">${issue.priority.priorityName}</td>
								<td>${issue.assignee.firstName}${issue.assignee.lastName}
									${issue.assignee.emailAddress}</td>
								<td>${issue.type.typeName}</td>
								<td>${issue.status.statusName}</td>
								<td>${issue.summary}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


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