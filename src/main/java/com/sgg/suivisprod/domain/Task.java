package com.sgg.suivisprod.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("task_list_test")
public class Task implements Comparable<Task> {

	@Id
	private String  id;
	private int     taskId;
	private String  taskType;
	private boolean prio;
	private double  totalWorkingTime;
	private Date    startDate;
	private Date    finishedDate;
	private String  note;
	private String  taskState;
	private double  productivity;
	private Booth   booth;
	private User    user;

	public String getId() {
		return id;
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

	/*public String getTotalWorkingTimeAsString() {
		return convertDoubleTimeAsFormatedString(this.totalWorkingTime);
	}*/

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
	public int compareTo(Task task2) {
		if (this.totalWorkingTime > task2.totalWorkingTime) {
			return 1;
		}
		else if (this.totalWorkingTime < task2.totalWorkingTime) {
			return -1;
		}
		return 0;
	}
}
