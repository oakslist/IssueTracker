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
<script src="<c:url value="/resources/js/script.js" />"></script>
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
			<jsp:include page="/WEB-INF/pages/includies/header.jsp" />
		</div>

		<div class="main">
		
			<h1>All Issues:</h1>
			
			<c:if test="${user.role.roleName eq 'GUEST'}">
				<p><font color=&quot#AABBCC&quot>Your are in IssueTracker Search user page now<br>
				Please login or go to the <a href="<c:url value='/index.jsp'/>">main page</a>.</font></p>
			</c:if>
				
					<form method="POST" action="<c:url value='/issue/'/>">
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
						<c:forEach items="${AllIssuesList}" var="issue">
							<tr>
								<c:choose>
									<c:when test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
										<td class="table-id"><a href="javascript:myEditParams('${issue.id}','edit')">${issue.id}</a></td>
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
					<input type="hidden" id="hidden1" value="" name="hidden1">
					</form>

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