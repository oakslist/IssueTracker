<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.training.constants.ServletConstants" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value='/pages/style/style.css'/>">
<script language="JavaScript" type="text/javascript" src="/pages/js/script.js"></script>
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

			<h1>Change password</h1>
			<c:choose>
				<c:when test="${user.role eq 'ADMINISTRATOR' || user.role eq 'USER'}">
					<form method="POST" action="<c:url value='/EditPasswordController'/>">
							<table class="add-user-table">
								<tr>
									<td>New Password:</td>
									<td><input type="password" class="password" name=<%= ServletConstants.JSP_PASSWORD%> 
										size="15"></td>
								</tr>
								<tr>
									<td>Password Confirmation:</td>
									<td><input type="password" class="password-confirmation" name=<%= ServletConstants.JSP_PASSWORD_CONFIRMATION%> 
										size="15"></td>
								</tr>
							</table>
							<input class="add-btn" type="submit" value="Change Password">
						</form>
				</c:when>
				<c:otherwise>
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker Change password page now<br>
					Please login or go to the <a href="<c:url value='/index.jsp'/>">main page</a>.</font></p>
				</c:otherwise>
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