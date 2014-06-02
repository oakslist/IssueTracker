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
		
			<h1>Issue Tracker</h1>
			<div class="search-btn">
				<input value="Search" onclick="#'" type="button">
			</div>
			<c:choose>
				<c:when test="${user.role eq 'GUEST'}">
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker now<br>
					Please login or work as a guest.</font></p>
				</c:when>
				<c:when test="${user.role eq 'USER'}">
					<div class="submit-issue-btn">
						<input value="Submit Issue" onclick="location.href='<c:url value='/BeforeSubmitIssueController'/>'" type="button">
					</div>
				</c:when>
				<c:when test="${user.role eq 'ADMINISTRATOR'}">
					<div class="submit-issue-btn">
						<input value="Submit Issue" onclick="location.href='<c:url value='/BeforeSubmitIssueController'/>'" type="button">
					</div>
					<div class="admin-links">
						<div class="pages-link-admin">
							<p>Pages:</p>
							<a href="<c:url value='#'/>">Projects</a> <a
								href="<c:url value='#'/>">Statuses</a> <a
								href="<c:url value='#'/>">Resolutions</a> <a
								href="<c:url value='#'/>">Priorities</a> <a
								href="<c:url value='#'/>">Types</a>
						</div>
						<div class="add-pages-link-admin">
							<p>Add Pages:</p>
							<a href="<c:url value='#'/>">Add Project</a> <a
								href="<c:url value='#'/>">Add Status</a> <a
								href="<c:url value='#'/>">Add Resolution</a> <a
								href="<c:url value='#'/>">Add Priority</a> <a
								href="<c:url value='#'/>">Add Type</a>
						</div>
						<div class="pages-link-user-admin">
							<p>Work with User:</p>
							<a href="<c:url value='#'/>">Search User</a>
						</div>
						<div class="pages-link-add-user-admin">
							<a href="<c:url value='#'/>">Add User</a>
						</div>
					</div>
				</c:when>
			</c:choose>
	
			<div class="main-issues">
						
				<table class="table-issues">
					<thead>
						<tr>
							<th>Id</th>
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
									<c:when test="${user.role eq 'USER' || user.role eq 'ADMINISTRATOR'}">
										<td><a href="<c:url value='/BeforeEditIssueController'/>">${issue.id}</a></td>
									</c:when>
									<c:otherwise>
										<td>${issue.id}</td>
									</c:otherwise>
								</c:choose>	
								<td class="color-${issue.priority}">${issue.priority}</td>
								<td>${issue.assignee}</td>
								<td>${issue.type}</td>
								<td>${issue.status}</td>
								<td>${issue.summary}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


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