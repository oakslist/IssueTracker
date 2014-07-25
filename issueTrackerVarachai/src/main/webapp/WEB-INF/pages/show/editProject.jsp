<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.training.constants.ServletConstants"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			<c:out value="${errorMessage}" />
			<hr>
		</c:if>
	</div>
	<div class="main-page">
		<div class="main-header">
			<jsp:include page="/WEB-INF/pages/includies/header.jsp" />
		</div>

		<div class="main">

			<h1>Edit project:</h1>

			<c:choose>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR'}">
					<form method="POST"
						action="<c:url value='/project/${editProject.id}/save'/>">
						<table>
							<tr>
								<td>Edit Project Name:</td>
								<td><input type="text" class="param-name"
									name=<%=ServletConstants.JSP_EDIT_PROJECT_NAME%> size="15"
									value="${editProject.projectName}"></td>
							</tr>
							<tr>
								<td>Edit Project Description:</td>
								<td><textarea class="description-select"
									name=<%=ServletConstants.JSP_EDIT_PROJECT_DESCRIPTION%> cols="50" rows="4">${editProject.description}</textarea>
								</td>
							</tr>
							<tr>
								<td>Choose Project Build:</td>
								<td>
									<select class="type-select" name=<%= ServletConstants.JSP_EDIT_PROJECT_BUILD%>>
										<c:forEach items="${editProjectBuilds}" var="build">
											<option id="build-${build.id}" value="${build.buildValue}">${build.buildValue}</option>
    									</c:forEach>
    								</select>
								</td>
								<td><a href="<c:url value='/project/${editProject.id}/build/add'/>">Add Build</a></td>
							</tr>
							<tr>
								<td>Choose Project Manager:</td>
								<td>
									<select class="type-select" name=<%= ServletConstants.JSP_EDIT_PROJECT_MANAGER%>>
										<c:forEach items="${editProjectManagers}" var="manager">
											<option id="${manager.userId}" value="${manager.userId}">${manager.firstName} ${manager.lastName} : ${manager.emailAddress}</option>
        									<script type="text/javascript">
        										if ('${manager.userId}' === '${editProject.manager.userId}') {
													selectedOption('${manager.userId}');
												}	
         									</script>>
    									</c:forEach>
    								</select>
								</td>
								<td><a href="<c:url value='/user/add'/>">Add User</a></td>
							</tr>
						</table>
						<input class="add-btn" type="submit" value="Change project params">
					</form>
				</c:when>
				<c:otherwise>
					<p>
						<font color=&quot#AABBCC&quot>Your are in IssueTracker edit
							project page now<br> Please login or go to the <a
							href="<c:url value='/index.jsp'/>">main page</a>.
						</font>
					</p>
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