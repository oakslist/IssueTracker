<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.training.constants.ServletConstants" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/><hr>
	</c:if>
	
	<c:if test="${not empty user}">
	
	</c:if>
	
	<div class="main-page">
		<div class="main-header">
			<jsp:include page="/pages/includies/header.jsp" />
		</div>

		<div class="main">
			<form method="POST" action="<c:url value='/LoginController'/>" >
				<h1>Issue Tracker</h1>
				<div>
					<span>Please write your:</span>
				</div>
				
				<div>
					<span class="input-name">email:</span><input type="text"
						name="${ServletConstants.JSP_EMAIL_ADDRESS}" size="20"
						maxlengh="20">
				</div>
				<div>
					<span class="input-name">password:</span><input type="text"
						name="${ServletConstants.JSP_PASSWORD}" size="20"
						maxlengh="20">
				</div>
				<div class="submit">
					<input type="submit" value="Enter">
				</div>
			</form>
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