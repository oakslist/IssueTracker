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
		
			<h1>Search User</h1>
				
			<h2>All Users:</h2>
			<c:choose>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR' || user.role.roleName eq 'USER'}">
					<form method="POST" action="<c:url value='/user/'/>">
							<table class="users-table">
							<thead>
								<tr>
									<th class="table-id-name">Id</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Email Address</th>
									<th>Role</th>
									<th>Links</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList}" var="user">
									<tr>
										<td><c:out value="${user.userId}"/></td>
										<td><c:out value="${user.firstName}"/></td>
										<td><c:out value="${user.lastName}"/></td>
										<td><c:out value="${user.emailAddress}"/></td>
										<td><c:out value="${user.role.roleName}"/></td>
										<td>
											<div>
												<a href="javascript:editUser('${user.userId}', '/edit/user')">Edit user</a>
											</div>
											<div>
												<a href="javascript:editUser('${user.userId}', '/edit/password')">Change password</a>
											</div>
										</td>
        							</tr>
    							</c:forEach>
    						</tbody>
							</table>
							<input type="hidden" id="hidden2" value="" name="hidden2">
						</form>
				</c:when>
				<c:otherwise>
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker Search user page now<br>
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