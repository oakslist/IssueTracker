function loskSelectBuild() {
	var order = document.getElementByClassName("build-select");
	order.disabled = 'disabled';
}

function unlockSelectBuild() {
	var order = document.getElementByClassName("build-select");
	order.disabled = 'enable';
}

function selectedOption(id) {
	var curId = id;
	document.getElementById(curId).selected = true;
}

function edit(id, curLink) {
	var userId = id;
	var curUser = document.getElementById("hidden2");
	curUser.value = userId;
	var link = document.forms[0].action;
	link = link + curLink;
	document.forms[0].action = link;
	document.forms[0].submit();
}

function editStatus(id) {
//	var statusId = statusId;
//	var link = "BeforeEditStatusController";
//	edit(statusId, link);
	var statusId = id;
	var curStatus = document.getElementById("hidden3");
	curStatus.value = statusId;
	document.forms[0].submit();
}

function editUser(id) {
	var userId = id;
	var link = "EditDifferentUserController";
	edit(userId, link);
}

function editPassword(id) {
	var userId = id;
	var link = "EditDifferentUserPasswordController";
	edit(userId, link);
}

function editIssue(value) {
	var id = value;
	var curIssue = document.getElementById("hidden1");
	curIssue.value = id;
	document.forms[0].submit();
}

