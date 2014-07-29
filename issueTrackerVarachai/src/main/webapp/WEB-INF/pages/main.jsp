<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="org.training.constants.ServletConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/script.js" />"></script>
<title><spring:message code="label.issueTracker"/></title>
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
			<h1><spring:message code="label.issueTracker"/></h1>
			<div class="search-btn">
				<input value="<spring:message code='label.search'/>" onclick="#'" type="button">
			</div>
			<c:choose>
				<c:when test="${user.role.roleName eq 'GUEST'}">
					<p>
						<font color=&quot#AABBCC&quot><spring:message code='label.pleaseLogin'/>
						</font>
					</p>
				</c:when>
				<c:when test="${user.role.roleName eq 'USER'}">
					<div class="submit-issue-btn">
						<input value="<spring:message code='label.submitIssue'/>"
							onclick="location.href='<c:url value='/issue/add'/>'"
							type="button">
					</div>
				</c:when>
				<c:when test="${user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="submit-issue-btn">
						<input value="<spring:message code='label.submitIssue'/>"
							onclick="location.href='<c:url value='/issue/add'/>'"
							type="button">
					</div>
					<div class="admin-links">
						<div class="pages-link-admin">
							<p><spring:message code='label.links'/></p>
							<a href="<c:url value='/project/all'/>"><spring:message code='label.projects'/></a>
							<a href="<c:url value='/status/all'/>"><spring:message code='label.statuses'/></a>
							<a href="<c:url value='/resolution/all'/>"><spring:message code='label.resolutions'/></a>
							<a href="<c:url value='/priority/all'/>"><spring:message code='label.priorities'/></a>
							<a href="<c:url value='/type/all'/>"><spring:message code='label.types'/></a>
						</div>
						<div class="add-pages-link-admin">
							<p><spring:message code='label.addLinks'/></p>
							<a href="<c:url value='/project/add'/>"><spring:message code='label.addProject'/></a> 
							<a href="<c:url value='/resolution/add'/>"><spring:message code='label.addResolution'/></a>
							<a href="<c:url value='/priority/add'/>"><spring:message code='label.addPriority'/></a> 
							<a href="<c:url value='/type/add'/>"><spring:message code='label.addType'/></a>
						</div>
						<div class="pages-link-user-admin">
							<p><spring:message code='label.workWithUser'/></p>
							<a href="<c:url value='/user/all'/>"><spring:message code='label.searchUser'/></a>
							 <a href="<c:url value='/user/add'/>" title="Add User"><spring:message code='label.addUser'/></a>
						</div>
					</div>
				</c:when>
			</c:choose>

			<div class="main-issues">
				<form action="<c:url value='/issue/'/>"
					method="POST">
				</form>
				<table class="table-issues">
					<thead>
						<tr>
							<th class="table-id-name"><spring:message code='label.tableId'/></th>
							<th><spring:message code='label.tablePriority'/></th>
							<th><spring:message code='label.tableAssignee'/></th>
							<th><spring:message code='label.tableType'/></th>
							<th><spring:message code='label.tableStatus'/></th>
							<th><spring:message code='label.tableSummary'/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${issuesList}" var="issue">
							<tr>
								<c:choose>
									<c:when
										test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
										<td class="table-id"><a
											href="javascript:myEditParams('${issue.id}','edit')">${issue.id}</a></td>
									</c:when>
									<c:otherwise>
										<td class="table-id">${issue.id}</td>
									</c:otherwise>
								</c:choose>
								<td class="color-${issue.priority.priorityName}">${issue.priority.priorityName}</td>
								<td>${issue.assignee.firstName}${issue.assignee.lastName}
									${issue.assignee.emailAddress}</td>
								<td>${issue.type.typeName}</td>
								<td>${issue.status.statusName}</td>
								<td>${issue.summary}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


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