<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		
			<h1>Edit resolution:</h1>
				
			<c:choose>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR'}">
					<c:url var="addUrl" value="/resolution/${editResolution.id}/edit/save"/>
					<form:form method="post" commandName="addSimpleNameForm" action="${addUrl}">
						<table class="users-table">
							<thead>
								<tr>
									<th class="table-id-name">Id</th>
									<th>Name</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value="${editResolution.id}" /></td>
									<td><c:out value="${editResolution.resolutionName}" /></td>
								</tr>
							</tbody>
						</table>
						<table class="add-user-table">
								<tr>
									<td>New Resolution:</td>
									<td><form:input path="name" /></td>
									<td class="red-text"><span class="error"><form:errors
												path="name" /></span></td>
								</tr>
								<tr>
									<td colspan="3"><input class="add-btn" type="submit"
										value="Add Resolution" /></td>
								</tr>
						</table>
					</form:form>
				</c:when>
				<c:otherwise>
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker edit resolution page now<br>
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