<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="org.training.constants.ServletConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<header class="main-header">
				<span class="lang">
    		<a href="?lang=en"><img src="<c:url value="/resources/images/gb.png" />"/></a>
    		<a href="?lang=ru"><img src="<c:url value="/resources/images/ru.png"/>"/></a>
		</span>		
		<c:choose>
		<c:when test="${empty user.emailAddress}">
			<form method="POST" action="<c:url value='/login'/>">
				<div class="enter-login-header"><spring:message code="label.help"/>
				<br>
					<span class="input-name"><spring:message code="label.email"/></span><input type="text"
						name=<%= ServletConstants.JSP_EMAIL_ADDRESS%> size="15">
					<span class="input-name"><spring:message code="label.password"/></span><input type="password"
						name=<%= ServletConstants.JSP_PASSWORD%> size="15">
					<input type="submit" value="<spring:message code='label.login'/>">
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<div class="login-place-header">
				<div class="user-menu-header">
					<a href="<c:url value='/user/${user.userId}/menu'/>"><spring:message code="label.userMenu"/></a>
				</div>
				<div class="first-name-header">
					<span><spring:message code="label.hello"/>, <a href="<c:url value='/user/${user.userId}/menu'/>">${user.firstName}</a>!</span>
				</div>
				<div class="logout-header">
					<a href="<c:url value='/logout'/>"><spring:message code="label.logout"/></a>
				</div>
			</div>
 		</c:otherwise>
		</c:choose>

		<div class="pages-header">
			<ul>
				<li class="home-page"><a href="<c:url value='/index'/>"><spring:message code="label.HOME"/></a></li>
				<li class="defects-page"><a href="<c:url value='/issue/all'/>"><spring:message code="label.ISSUES"/></a></li>
			</ul>
		</div>
	</header>
</body>
</html>