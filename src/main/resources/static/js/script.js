//----------------------------Modal function section ----------------------------------------------
let openTaskTypeModal = openModal('task-type-modal');
let openStatusComModal = openModal('booth-status-modal');
let openConfirmationComModal = openModal('confirmation-modal');

function openModal(elementId) {
	return function() {
		let taskTypeModal = document.getElementById(elementId);
		taskTypeModal.style.display = "flex";
	}
}

let closeTaskTypeModal = closeModal('task-type-modal');
let closeStatusComModal = closeModal('booth-status-modal');
let closeConfirmationComModal = closeModal('confirmation-modal');

function closeModal(elementId) {
	return function() {
		document.getElementById(elementId).style.display = "none";
	}
}
//---------------------------------------------------------------------------------------------------

//----------------------------Task form function section ----------------------------------------------
function clickedTaskType(event) {
	let tasTypeInput = document.getElementById('taskType');
	tasTypeInput.value = event.target.innerHTML;
}

function handleTaskPriority(event) {
	let isTaskPrio = event.target.checked;

	if (isTaskPrio) {
		addPrioSufixToTaskType();
	} else {
		removePrioSufixToTaskType();
	}
}

function addPrioSufixToTaskType() {
	let tasTypeInput = document.getElementById('taskType');
	if (tasTypeInput.value !== "") {
		tasTypeInput.value = tasTypeInput.value + 'Prio';
	}
}

function removePrioSufixToTaskType() {
	let tasTypeInput = document.getElementById('taskType');
	let currentTaskTypeValue = tasTypeInput.value;
	if (currentTaskTypeValue !== "" && currentTaskTypeValue.indexOf('Prio') !== -1) {
		tasTypeInput.value = currentTaskTypeValue.substr(0, currentTaskTypeValue.indexOf('Prio'));
	}
}

function handleStatusCom(event) {
	let badgeElement;
	switch (event.target.name) {
		case "statu-com":
			badgeElement = document.getElementById("status-com-badge");
			break;
		case "statu-iv":
			badgeElement = document.getElementById("status-iv-badge");
			break;
	}
	badgeElement.innerHTML = event.target.value;
}

function handleUrlOnChange(event) {
	if (event.target.value !== "") {
		let boothId = getBoothIdFromUrl(event.target.value);
		document.getElementById("boothId").value = boothId;
	} else {
		document.getElementById("boothId").value = "";
	}
}

function getBoothIdFromUrl(url) {
	let boothId = '';
	boothId = url.indexOf('s=') !== -1 ? url.substring(url.indexOf('s=') + 2, url.indexOf('&w')) : null;
	return boothId;
}

