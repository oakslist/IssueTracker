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
<script language="JavaScript">

	function loskSelectBuild() {
		var order = document.getElementByClassName("build-select");
		order.disabled = 'disabled';
	}
	
	function unlockSelectBuild() {
		var order = document.getElementByClassName("build-select");
		order.disabled = 'enable';
	}
	
	function selectedIssue(id) {
		var curId = id;
		document.getElementById(curId).selected = true;
	}

</script>
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

			<h1>Edit Issue</h1>
			<c:choose>
				<c:when test="${user.role eq 'GUEST'}">
					<p><font color=&quot#AABBCC&quot>Your are in IssueTracker now<br>
					Please login or work as a guest.</font></p>
				</c:when>
				<c:when test="${user.role eq 'USER' || user.role eq 'ADMINISTRATOR'}">
					<div class="add-edit-issue">
						<form method="POST" action="<c:url value='/EditIssueController'/>">
							<table class="add-edit-table">
								<tr>
									<td>Id:</td>
									<td><input type="text" class="summary-select" 
										name=<%= ServletConstants.JSP_ISSUE_ID%> size="15" 
										value="${issue.id}" readonly></td>
								</tr>
								<tr>
									<td>Create Date:</td>
									<td><input type="text" class="summary-select" 
										name=<%= ServletConstants.JSP_CREATE_DATE%> size="15" 
										value="${issue.createDate}" readonly></td>
								</tr>
								<tr>
									<td>Created By:</td>
									<td><input type="text" class="summary-select" 
										name=<%= ServletConstants.JSP_CREATED_BY%> size="15" 
										value="${issue.createdBy}" readonly></td>
								</tr>
								<tr>
									<td>Modify Date:</td>
									<td><input type="text" class="summary-select" 
										name=<%= ServletConstants.JSP_MODIFY_DATE%> size="15" 
										value="${issue.modifyDate}" readonly></td>
								</tr>
								<tr>
									<td>Modified By:</td>
									<td><input type="text" class="summary-select" 
										name=<%= ServletConstants.JSP_MODIFIED_BY%> size="15" 
										value="${issue.modifiedBy}" readonly></td>
								</tr>
								<tr>
									<td>Summary:</td>
									<td><input type="text" class="summary-select" 
										name=<%= ServletConstants.JSP_SUMMARY%> size="15"
										value="${issue.summary}"></td>
								</tr>
								<tr>
									<td>Description:</td>
									<td><input type="text" class="description-select" 
										name=<%= ServletConstants.JSP_DESCRIPTION%> size="15"
										value="${issue.description}"></td>
								</tr>
								<tr>
									<td>Status:</td>
									<td>
									<select class="status-select" name=<%= ServletConstants.JSP_STATUS%>>
										<c:forEach items="${issueStatuses}" var="status">
        									<option id="status-${status.key}" value="${status.key}">${status.value}</option>
        									<script type="text/javascript">
												if ('${status.value}' === '${issue.status}') {
													selectedIssue("status-" + '${status.key}');
												}
        									</script>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Resolution:</td>
									<td>
									<select class="resolution-select" name=<%= ServletConstants.JSP_RESOLUTION%>>
										<c:forEach items="${issueResolutions}" var="resolution">
        									<option id="resolution-${resolution.key}" value="${resolution.key}">${resolution.value}</option>
        									<script type="text/javascript">
        										if ('${resolution.value}' === '${issue.resolution}') {
													selectedIssue("resolution-" + '${resolution.key}');
												}
        									</script>
    									</c:forEach>
   									</select>
									</td>
								</tr>
								<tr>
									<td>Type:</td>
									<td>
									<select class="type-select" name=<%= ServletConstants.JSP_TYPE%>>
										<c:forEach items="${issueTypes}" var="type">
        									<option id="type-${type.key}" value="${type.key}">${type.value}</option>
        									<script type="text/javascript">
        										if ('${type.value}' === '${issue.type}') {
        											selectedIssue("type-" + '${type.key}');
												}
        									</script>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Priority:</td>
									<td>
									<select class="priority-select" name=<%= ServletConstants.JSP_PRIORITY%>>
										<c:forEach items="${issuePriorities}" var="priority">
        									<option id="priority-${priority.key}" value="${priority.key}" class="color-${priority.key}">${priority.value}</option>
        									<script type="text/javascript">
        										if ('${priority.value}' === '${issue.priority}') {
													selectedIssue("priority-" + '${priority.key}');
												}
         									</script>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Project:</td>
									<td>
									<select class="project-select" name=<%= ServletConstants.JSP_PROJECT%>>
										<c:forEach items="${issueProjects}" var="project">
        									<option id="project-${project.key}" value="${project.key}">${project.value.name}</option>
        									<script type="text/javascript">
        										if ('${project.value}' === '${issue.project}') {
													selectedIssue("project-" + '${project.key}');
												}
         									</script>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Build Found:</td>
									<td>
									<select class="build-select" name=<%= ServletConstants.JSP_BUILD_FOUND%>>
										<c:forEach items="${issueProjectBuilds}" var="build">
        									<option id="build-${build.key}" value="${build.key}">${build.value}</option>
        									<script type="text/javascript">
        										if ('${build.value}' === '${issue.buildFound}') {
													selectedIssue("build-" + '${build.key}');
												}
        									</script>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
								<tr>
									<td>Assignee:</td>
									<td>
									<select class="assignee-select" name=<%= ServletConstants.JSP_ASSIGNEE%>>
										<c:forEach items="${issueAssignees}" var="assignee">
        									<option id="assignee-${assignee.key}" value="${assignee.key}">${assignee.value}</option>
        									<script type="text/javascript">
        										if ('${assignee.value}' === '${issue.assignee}') {
													selectedIssue("assignee-" + '${assignee.key}');
												}
         									</script>
    									</c:forEach>
   									</select>
   									</td>
								</tr>
							</table>
							<input class="edit-btn" type="submit" value="Edit Issue">
						</form>
					</div>
				</c:when>
			</c:choose>
			<div class="comment-part">
			<h2>Comments:</h2>
				<div class="comments">
				<c:if test="${empty commentList}">
					<p>No comments here</p>
				</c:if>
					<c:forEach items="${commentList}" var="comment">
						<div>
							<div class="header-comment">
								<div class="add-comment">
									<span class="left"><c:out value="${comment.addedBy}"></c:out></span>
								</div>
								<div class="date-comment">
									<span class="right">date: <c:out value="${comment.addDate}"></c:out></span>
								</div>
							</div>
						</div>
						<div class="footer-comment">
							<span class="comment"><c:out value="${comment.comment}"></c:out></span>
							<hr>
						</div>
					</c:forEach>
				</div>
				<form id="comment-form" method="POST" action="<c:url value='/AddIssueCommentController'/>">
					<div>
						<textarea rows="4" cols="50" name="comment" form="comment-form"></textarea>
					</div>
					<div>
						<input class="add-btn" type="submit" value="Add Comment">
						<input type="hidden" name="hidden-issue-id" value="${issue.id}">
					</div>					
				</form>
			</div>
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