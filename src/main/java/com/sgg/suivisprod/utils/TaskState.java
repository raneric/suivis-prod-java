package com.sgg.suivisprod.utils;

public enum TaskState {
	TODO("todo"), IN_PROGRESS("in_progress"), STAND_BY("stdby"), DONE("done");
	
	private String tastState;
	
	TaskState(String tastState) {
		this.tastState = tastState;
	}
	
	public String toString() {
		return tastState;
	}
}
