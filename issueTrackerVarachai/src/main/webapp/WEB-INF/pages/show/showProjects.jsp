<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.training.constants.ServletConstants" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		
			<h1>Projects:</h1>
				
			<c:choose>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR'}">
					<form method="POST" action="<c:url value='/project/edit'/>">
							<table class="users-table">
							<thead>
								<tr>
									<th class="table-id-name">Id</th>
									<th>Name</th>
									<th>Manager</th>
									<th>Description</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${issueProjects}" var="project">
									<tr>
										<td><c:out value="${project.id}"/></td>
										<td><a href="javascript:editParams('${project.id}')"><c:out value="${project.projectName}"/></a></td>
										<td><c:out value="${project.manager.firstName} ${project.manager.lastName} : ${project.manager.emailAddress}"/></td>
										<td><c:out value="${project.description}"/></td>
        							</tr>
    							</c:forEach>
    						</tbody>
							</table>
							<input type="hidden" id="hidden3" value="" name="hidden3">
						</form>
				</c:when>
				<c:otherwise>
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker show Projects page now<br>
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