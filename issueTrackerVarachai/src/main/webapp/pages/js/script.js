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
