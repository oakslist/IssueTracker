<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="org.training.constants.ServletConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

	<header class="main-header"> <span class="lang"> <a
		href="?lang=en"><img
			src="<c:url value="/resources/images/gb.png" />" /></a> <a
		href="?lang=ru"><img
			src="<c:url value="/resources/images/ru.png"/>" /></a>
	</span> <c:choose>
		<c:when test="${empty user.emailAddress}">
			<form:form method="post" commandName="loginForm">
				<table>
					<tr>
						<td colspan="3"><spring:message code="label.help" /></td>
					</tr>
					<tr>
						<td class="login-text"><spring:message code="label.email" /></td>
						<td><form:input path="email" value="ad@ad.ad"/></td>
						<td class="red-text"><span class="error"><form:errors path="email"/></span></td>
					</tr>
					<tr>
						<td><spring:message	code="label.password" /></td>
						<td><form:password path="password"/></td>
						<td class="red-text"><span class="error"><form:errors path="password"/></span></td>
					</tr>
					<tr>
						<td colspan="3"><input class="login-sub" type="submit" value="<spring:message code='label.login'/>" /></td>
					</tr>
				</table>
			</form:form>
		</c:when>
		<c:otherwise>
			<div class="login-place-header">
				<div class="user-menu-header">
					<a href="<c:url value='/user/${user.userId}/menu'/>"><spring:message
							code="label.userMenu" /></a>
				</div>
				<div class="first-name-header">
					<span><spring:message code="label.hello" />, <a
						href="<c:url value='/user/${user.userId}/menu'/>">${user.firstName}</a>!</span>
				</div>
				<div class="logout-header">
					<a href="<c:url value='/logout'/>"><spring:message
							code="label.logout" /></a>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

	<div class="pages-header">
		<ul>
			<li class="home-page"><a href="<c:url value='/index'/>"><spring:message
						code="label.HOME" /></a></li>
			<li class="defects-page"><a href="<c:url value='/issue/all'/>"><spring:message
						code="label.ISSUES" /></a></li>
		</ul>
	</div>
	</header>
</body>
</html>