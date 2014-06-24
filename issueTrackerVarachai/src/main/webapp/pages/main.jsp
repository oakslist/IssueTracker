<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.training.constants.ServletConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value='/pages/style/style.css'/>">
<script type="text/javascript" src="<c:url value='/pages/js/script.js'/>"></script>
<title>IssueTracker</title>
<script language="JavaScript">
	function editIssue(value) {
		var id = value;
		var curIssue = document.getElementById("hidden1");
		curIssue.value = id;
		document.forms[0].submit();
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
		
			<h1>Issue Tracker</h1>
			<div class="search-btn">
				<input value="Search" onclick="#'" type="button">
			</div>
			<c:choose>
				<c:when test="${user.role.roleName eq 'GUEST'}">
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker now<br>
					Please login or work as a guest.</font></p>
				</c:when>
				<c:when test="${user.role.roleName eq 'USER'}">
					<div class="submit-issue-btn">
						<input value="Submit Issue" onclick="location.href='<c:url value='/BeforeSubmitIssueController'/>'" type="button">
					</div>
				</c:when>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="submit-issue-btn">
						<input value="Submit Issue" onclick="location.href='<c:url value='/BeforeSubmitIssueController'/>'" type="button">
					</div>
					<div class="admin-links">
						<div class="pages-link-admin">
							<p>Pages:</p>
							<a href="<c:url value='#'/>">Projects</a> 
							<a href="<c:url value='#'/>">Statuses</a> 
							<a href="<c:url value='#'/>">Resolutions</a> 
							<a href="<c:url value='#'/>">Priorities</a> 
							<a href="<c:url value='#'/>">Types</a>
						</div>
						<div class="add-pages-link-admin">
							<p>Add Pages:</p>
							<a href="<c:url value='#'/>">Add Project</a> 
							<a href="<c:url value='#'/>">Add Status</a> 
							<a href="<c:url value='#'/>">Add Resolution</a> 
							<a href="<c:url value='#'/>">Add Priority</a> 
							<a href="<c:url value='#'/>">Add Type</a>
						</div>
						<div class="pages-link-user-admin">
							<p>Work with User:</p>
							<a href="<c:url value='/BeforeSearchUserController'/>">Search User</a>
							<a href="<c:url value='/pages/addUser.jsp'/>">Add User</a>
						</div>
					</div>
				</c:when>
			</c:choose>
	
			<div class="main-issues">
						
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
						<form action="<c:url value='/BeforeEditIssueController'/>" method="POST">
							<input type="hidden" id="hidden1" value="" name="hidden1">
						</form>	
						<c:forEach items="${issuesList}" var="issue">
							<tr>
								<c:choose>
									<c:when test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
										<td class="table-id"><a href="javascript:editIssue('${issue.id}')">${issue.id}</a></td>
									</c:when>
									<c:otherwise>
										<td class="table-id">${issue.id}</td>
									</c:otherwise>
								</c:choose>	
								<td class="color-${issue.priority.priorityName}">${issue.priority.priorityName}</td>
								<td>${issue.assignee.firstName} ${issue.assignee.lastName} ${issue.assignee.emailAddress}</td>
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
			<jsp:include page="/pages/includies/aside.jsp" />
		</div>
		<div class="main-footer">
			<jsp:include page="/pages/includies/footer.jsp" />
		</div>
	</div>
</body>
</html>