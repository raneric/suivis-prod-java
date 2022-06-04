package com.sgg.suivisprod.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sgg.suivisprod.utils.NotificationType;

@Document("notifications")
public class Notification {

	@Id
	private String				id;
	private String				message;
	private NotificationType	notificationType;
	private boolean				read;
	private Date				creactedAt;
	private User				user;

	public Notification() {
	}

	public Notification(String message, NotificationType notificationType, User user) {
		super();
		this.message = message;
		this.notificationType = notificationType;
		this.user = user;
		this.creactedAt = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Date getCreactedAt() {
		return creactedAt;
	}

	public void setCreactedAt(Date creactedAt) {
		this.creactedAt = creactedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
