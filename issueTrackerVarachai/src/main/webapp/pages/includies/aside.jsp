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
	<aside class="main-aside">
		<div class="pages-aside">
			<ul>
				<li class="home-page"><a href="<c:url value='/index.jsp'/>">HOME</a></li>
				<li class="defects-page"><a href="#">ISSUES</a></li>
			</ul>
		</div>	
	</aside>
</body>
</html>