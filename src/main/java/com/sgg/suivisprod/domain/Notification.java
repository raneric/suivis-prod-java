package com.sgg.suivisprod.domain;

import com.sgg.suivisprod.utils.NotificationType;

public class Notification {
	private String				message;
	private NotificationType	notificationType;
	private boolean				read;

	public Notification() {
	}

	public Notification(NotificationType notificationType, String message) {
		this.message = message;
		this.notificationType = notificationType;
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

}
