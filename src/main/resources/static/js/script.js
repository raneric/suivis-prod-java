function openTaskTypeModal() {
	let taskTypeModal = document.getElementById('task-type-modal');
	taskTypeModal.style.display = "flex";
}

function openStatusComModal() {
	let taskTypeModal = document.getElementById('booth-status-modal');
	taskTypeModal.style.display = "flex";
}

function closeModal(event) {
	switch (event.target.id) {
		case 'task-type':
			document.getElementById('task-type-modal').style.display = "none";
			break;
		case 'status-com':
			document.getElementById('booth-status-modal').style.display = "none";
			break;
	}
}

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

function handleUrlOnChange(event){
	if(event.target.value!==""){
		let boothId = getBoothIdFromUrl(event.target.value);
		document.getElementById("boothId").value = boothId;
	}else{
		document.getElementById("boothId").value = "";
	}
}

function getBoothIdFromUrl(url) {
	let boothId = '';
	boothId = url.indexOf('s=') !== -1 ? url.substring(url.indexOf('s=') + 2, url.indexOf('&w')) : null;
	return boothId;
}

