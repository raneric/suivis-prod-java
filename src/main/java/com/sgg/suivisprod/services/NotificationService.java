package com.sgg.suivisprod.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sgg.suivisprod.domain.Notification;
import com.sgg.suivisprod.utils.NotificationType;

@Service
public class NotificationService {

	private List<Notification> notifications;

	public NotificationService() {
		this.notifications = new LinkedList<>();
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void addNotification(Notification notification) {
		this.notifications.add(notification);
	}
}
