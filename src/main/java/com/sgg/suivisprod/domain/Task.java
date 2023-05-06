package com.sgg.suivisprod.domain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sgg.suivisprod.utils.TaskState;

@Document("task_list_test")
public class Task implements Comparable<Task> {

	@Id
	private String	id;
	private int		taskId;

	@Size(min = 5, max = 50)
	private String	taskType;
	private boolean	prio;

	@Min(value = 0)
	private double	totalWorkingTime;
	private Date	startDate;
	private Date	finishedDate;
	private String	note;
	private String	taskState	= TaskState.TODO.toString();
	private double	productivity;
	private Booth	booth;
	private User	user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = (id.equals("")) ? null : id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public boolean isPrio() {
		return prio;
	}

	public void setPrio(boolean prio) {
		this.prio = prio;
	}

	public double getTotalWorkingTime() {
		return totalWorkingTime;
	}

	public void setTotalWorkingTime(double totalWorkingTime) {
		this.totalWorkingTime = totalWorkingTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public double getProductivity() {
		return productivity;
	}

	public void setProductivity(double productivity) {
		this.productivity = productivity;
	}

	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int compareTo(Task otherTask) {
		if (this.totalWorkingTime > otherTask.totalWorkingTime) {
			return 1;
		} else if (this.totalWorkingTime < otherTask.totalWorkingTime) {
			return -1;
		}
		return 0;
	}
}
