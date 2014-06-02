<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.training.constants.ServletConstants" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header class="main-header">
	
		<c:choose>
		<c:when test="${empty user.emailAddress}">
			<form method="POST" action="<c:url value='/LoginController'/>">
				<div class="enter-login-header">
				just USE email: admin pass: admin OR email: user pass: user
					<span class="input-name">email:</span><input type="text"
						name=<%= ServletConstants.JSP_EMAIL_ADDRESS%> size="15">
					<span class="input-name">password:</span><input type="text"
						name=<%= ServletConstants.JSP_PASSWORD%> size="15">
					<input type="submit" value="Login">
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<div class="login-place-header">
				<div class="user-menu-header">
					<a href="#">User Menu</a>
				</div>
				<div class="first-name-header">
					<span>Hello, <a href="#">${user.firstName}</a>!</span>
				</div>
				<div class="logout-header">
					<a href="<c:url value='/LogoutController'/>">Logout</a>
				</div>
			</div>
 		</c:otherwise>
		</c:choose>

		<div class="pages-header">
			<ul>
				<li class="home-page"><a href="<c:url value='/index.jsp'/>">HOME</a></li>
				<li class="defects-page"><a href="#">ISSUES</a></li>
			</ul>
			
		</div>			
	</header>
</body>
</html>