//----------------------------Modal function section ----------------------------------------------
function closeModal() {
	let elements = document.getElementsByClassName("modal-container");
	for (let index = 0; index < elements.length; index++) {
		elements[index].style.visibility = "hidden";
	}
}

let openTaskTypeModal = setDisplayFlexToElement('task-type-modal');
let openStatusComModal = setDisplayFlexToElement('booth-status-modal');
let openConfirmationComModal = setDisplayFlexToElement('confirmation-modal');

function setDisplayFlexToElement(elementId) {
	return function() {
		let taskTypeModal = document.getElementById(elementId);
		taskTypeModal.style.visibility = "visible";
	}
}

let closeTaskTypeModal = setDisplayNoneToElement('task-type-modal');
let closeStatusComModal = setDisplayNoneToElement('booth-status-modal');
let closeConfirmationComModal = setDisplayNoneToElement('confirmation-modal');
let closeNotification = setDisplayNoneToElement('notification');

function setDisplayNoneToElement(elementId) {
	return function() {
		document.getElementById(elementId).style.visibility = "hidden";
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
	let hiddenInput;
	switch (event.target.name) {
		case "statu-com":
			badgeElement = document.getElementById("status-com-badge");
			hiddenInput = document.getElementById("statutCom");
			break;
		case "statu-iv":
			badgeElement = document.getElementById("status-iv-badge");
			hiddenInput = document.getElementById("statutIVP");
			break;
	}
	badgeElement.innerHTML = event.target.value;
	hiddenInput.value = event.target.value;
}

function handleUrlOnChange(event) {
	let url = event.target.value;
	if (url !== "" && isAvalidUrl(url)) {
		document.getElementById("boothId").value = getBoothIdFromUrl(url);
	} else {
		document.getElementById("boothId").value = "";
	}
}

function isAvalidUrl(url) {
	const urlRegex = /^(https:\/\/|http:\/\/|www.)/;
	return urlRegex.test(url);
}

function getBoothIdFromUrl(url) {
	let boothId = '';
	boothId = url.indexOf('s=') !== -1 ? url.substring(url.indexOf('s=') + 2, url.indexOf('&w')) : null;
	return boothId;
}

//-----------------------Dashboard ---------------------------------------
function dropPreventDefault(e) {
	e.preventDefault();
}

function onDropHandler(event, dropElement) {
	event.preventDefault();
	let cardId = event.dataTransfer.getData("text");
	dropElement.appendChild(document.getElementById(cardId));
	sendRequest(`http://localhost:8080/task/asyncupdate/${cardId}?state=${dropElement.id}`);
}

function onDrageHandler(e) {
	e.stopPropagation();
	e.dataTransfer.setData("text", e.target.id);
}

async function sendRequest(url) {
	return fetch(url)
		.then(returnJsonData)
		.catch(error => {
			return error;
		})
}

let returnJsonData = data => {
	return data.json();
}
