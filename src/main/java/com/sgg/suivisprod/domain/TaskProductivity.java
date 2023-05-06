package com.sgg.suivisprod.domain;

public class TaskProductivity {
	private String	taskType;
	private double	sumWorkingTime;
	private int		sumNbAfter;
	private float	productivityGoal;

	public TaskProductivity(String taskType, double sumWorkingTime, int sumNbAfter, float produCtivityGoal) {
		super();
		this.taskType = taskType;
		this.sumWorkingTime = sumWorkingTime;
		this.sumNbAfter = sumNbAfter;
		this.productivityGoal = produCtivityGoal;
	}

	public double getSumWorkingTime() {
		return sumWorkingTime;
	}

	public void setSumWorkingTime(double sumWorkingTime) {
		this.sumWorkingTime = sumWorkingTime;
	}

	public int getSumNbAfter() {
		return sumNbAfter;
	}

	public void setSumNbAfter(int sumNbAfter) {
		this.sumNbAfter = sumNbAfter;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public float getProductivityGoal() {
		return productivityGoal;
	}

	public double getProductivity() {
		return ((this.sumNbAfter / this.sumWorkingTime) * 100) / this.productivityGoal;
	}
}
