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

