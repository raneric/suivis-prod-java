package com.sgg.suivisprod.utils;

public enum NotificationType {
	
	INFO("info-notif"),
	WARNING("warning-notif"),
	ERROR("error-notif"),
	SUCCESS("success-notif");

	private String notifType;

	NotificationType(String notifType) {
		this.notifType = notifType;
	}

	public String toString() {
		return notifType;
	}
}
