<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="org.training.constants.ServletConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/script.js" />"></script>
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
			<c:out value="${errorMessage}" />
			<hr>
		</c:if>
	</div>
	<div class="main-page">
		<div class="main-header">
			<jsp:include page="/WEB-INF/pages/includies/header.jsp" />
		</div>

		<div class="main">

			<h1>Edit Issue</h1>
			<c:choose>
				<c:when test="${user.role.roleName eq 'GUEST'}">
					<p>
						<font color=&quot#AABBCC&quot>Your are in IssueTracker now<br>
							Please login or work as a guest.
						</font>
					</p>
				</c:when>
				<c:when
					test="${user.role.roleName eq 'USER' || user.role.roleName eq 'ADMINISTRATOR'}">
					<div class="add-edit-issue">
						<form method="POST"
							action="<c:url value='/issue/${issue.id}/edit/save'/>">
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
										value="${issue.createdBy.firstName} ${issue.createdBy.lastName} : ${issue.createdBy.emailAddress}"
										readonly></td>
								</tr>
								<tr>
									<td>Modify Date:</td>
									<td><input type="text" class="summary-select"
										name=<%= ServletConstants.JSP_MODIFY_DATE%> size="15"
										value="${issue.modifyDate}" readonly></td>
								</tr>
								<tr>
									<td>Modified By:</td>
									<td><c:choose>
											<c:when test="${not empty issue.modifiedBy}">
												<input type="text" class="summary-select"
													name=<%= ServletConstants.JSP_MODIFIED_BY%> size="15"
													value="${issue.modifiedBy.firstName} ${issue.modifiedBy.lastName} : ${issue.modifiedBy.emailAddress}"
													readonly>
											</c:when>
											<c:otherwise>
												<input type="text" class="summary-select"
													name=<%=ServletConstants.JSP_MODIFIED_BY%> size="15"
													value="" readonly>
											</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
									<td>Summary:</td>
									<td><input type="text" class="summary-select"
										name=<%= ServletConstants.JSP_SUMMARY%> size="15"
										value="${issue.summary}"></td>
								</tr>
								<tr>
									<td>Description:</td>
									<td><textarea class="description-select"
											name=<%=ServletConstants.JSP_DESCRIPTION%> cols="50" rows="4">${issue.description}</textarea></td>
								</tr>
								<tr>
									<td>Status:</td>
									<td><select class="status-select"
										name=<%=ServletConstants.JSP_STATUS%>>
											<c:forEach items="${issueStatuses}" var="status">
												<option id="status-${status.id}"
													value="${status.statusName}">${status.statusName}</option>
												<script type="text/javascript">
													if ('${status.statusName}' === '${issue.status.statusName}') {
														selectedIssue("status-"
																+ '${status.id}');
													}
												</script>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>Resolution:</td>
									<td><select class="resolution-select"
										name=<%=ServletConstants.JSP_RESOLUTION%>>
											<c:forEach items="${issueResolutions}" var="resolution">
												<option id="resolution-${resolution.id}"
													value="${resolution.resolutionName}">${resolution.resolutionName}</option>
												<script type="text/javascript">
													if ('${resolution.resolutionName}' === '${issue.resolution.resolutionName}') {
														selectedIssue("resolution-"
																+ '${resolution.id}');
													}
												</script>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>Type:</td>
									<td><select class="type-select"
										name=<%=ServletConstants.JSP_TYPE%>>
											<c:forEach items="${issueTypes}" var="type">
												<option id="type-${type.id}" value="${type.typeName}">${type.typeName}</option>
												<script type="text/javascript">
													if ('${type.typeName}' === '${issue.type.typeName}') {
														selectedIssue("type-"
																+ '${type.id}');
													}
												</script>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>Priority:</td>
									<td><select class="priority-select"
										name=<%=ServletConstants.JSP_PRIORITY%>>
											<c:forEach items="${issuePriorities}" var="priority">
												<option id="priority-${priority.id}"
													value="${priority.priorityName}"
													class="color-${priority.id}">${priority.priorityName}</option>
												<script type="text/javascript">
													if ('${priority.priorityName}' === '${issue.priority.priorityName}') {
														selectedIssue("priority-"
																+ '${priority.id}');
													}
												</script>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>Project:</td>
									<td><select class="project-select"
										name=<%=ServletConstants.JSP_PROJECT%>>
											<c:forEach items="${issueProjects}" var="project">
												<option id="project-${project.id}"
													value="${project.projectName}">${project.projectName}</option>
												<script type="text/javascript">
													if ('${project.projectName}' === '${issue.project.projectName}') {
														selectedIssue("project-"
																+ '${project.id}');
													}
												</script>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>Build Found:</td>
									<td><select class="build-select"
										name=<%=ServletConstants.JSP_BUILD_FOUND%>>
											<c:forEach items="${issueProjectBuilds}" var="build">
												<option id="build-${build.id}" value="${build.buildValue}">${build.buildValue}</option>
												<script type="text/javascript">
													if ('${build.buildValue}' === '${project.buildFound.buildValue}') {
														selectedIssue("build-"
																+ '${build.id}');
													}
												</script>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>Assignee:</td>
									<td><select class="assignee-select"
										name=<%=ServletConstants.JSP_ASSIGNEE%>>
											<c:forEach items="${issueAssignees}" var="assignee">
												<option id="assignee-${assignee.userId}"
													value="${assignee.userId}">${assignee.firstName}
													${assignee.lastName} : ${assignee.emailAddress}</option>
												<script type="text/javascript">
													if ('${assignee.firstName}' === '${issue.assignee.firstName}') {
														selectedIssue("assignee-"
																+ '${assignee.userId}');
													}
												</script>
											</c:forEach>
									</select></td>
								</tr>
							</table>
							<input class="edit-btn" type="submit" value="Edit Issue">
						</form>
					</div>
					<div class="upload-file">
						<hr>
						<form method="POST" action="<c:url value='/issue/${issue.id}/file/upload'/>" enctype="multipart/form-data">
							<p><span class="upload-left">File to upload: <input type="file" name="file"></span>
								<span class="upload-right"><input type="submit" value="Upload"></span>
							</p>
						</form>
						<h2>Attachments:</h2>
						<div class="attachment">
							<c:if test="${empty attachmentList}">
								<p>No attachments here</p>
							</c:if>
							<c:forEach items="${attachmentList}" var="attachment">
								<div>
									<div class="header-comment">
										<div class="add-comment">
											<span class="left"><c:out
											value="${attachment.addedBy.firstName} ${attachment.addedBy.lastName} : ${attachment.addedBy.emailAddress}"></c:out></span>
										</div>
										<div class="date-comment">
											<span class="right">date: <c:out
											value="${attachment.addedDate}"></c:out></span>
										</div>
									</div>
								</div>
								<div class="footer-comment">
								<a href="<c:url value='/issue/${issue.id}/file/${attachment.id}/download'/>"><c:out value="${attachment.name}"/></a>
									<hr>
								</div>
							</c:forEach>
						</div>
						<hr>
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
									<span class="left"><c:out
											value="${comment.addedBy.firstName} ${comment.addedBy.lastName} : ${comment.addedBy.emailAddress}"></c:out></span>
								</div>
								<div class="date-comment">
									<span class="right">date: <c:out
											value="${comment.addedDate}"></c:out></span>
								</div>
							</div>
						</div>
						<div class="footer-comment">
							<span class="comment"><c:out value="${comment.comment}"></c:out></span>
							<hr>
						</div>
					</c:forEach>
				</div>
				<form id="comment-form" method="POST"
					action="<c:url value='/issue/${issue.id}/edit/addComment'/>">
					<div>
						<textarea rows="4" cols="50" name="comment" form="comment-form"></textarea>
					</div>
					<div>
						<input class="add-btn" type="submit" value="Add Comment">
					</div>
				</form>
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