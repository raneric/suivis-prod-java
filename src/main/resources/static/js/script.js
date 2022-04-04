/**
 * 
 */
function openTaskTypeModal() {
	let taskTypeModal = document.getElementById('task-type-modal');
	taskTypeModal.style.display = "flex";
}

function openStatusComModal() {
	let taskTypeModal = document.getElementById('booth-status-modal');
	taskTypeModal.style.display = "flex";
}

function closeModal(event){
	switch(event.target.id){
		case'task-type':
		document.getElementById('task-type-modal').style.display = "none";
		break;
		case'status-com':
		document.getElementById('booth-status-modal').style.display = "none";
		break;
	}
}

function clickedTaskType(event){
	console.log(event.target.innerHTML);
	let tasTypeInput = document.getElementById('taskType');
	tasTypeInput.value = event.target.innerHTML;
	
}