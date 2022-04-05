package com.sgg.suivisprod.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("task_type")
public class TaskType {
	
	@Id
	String id;
	String taskName;
	List<Variant> variant;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * @return the variant
	 */
	public List<Variant> getVariant() {
		return variant;
	}
	/**
	 * @param variant the variant to set
	 */
	public void setVariant(List<Variant> variant) {
		this.variant = variant;
	}
	
	
}
