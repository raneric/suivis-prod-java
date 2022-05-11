package com.sgg.suivisprod.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sgg.suivisprod.domain.Notification;
import com.sgg.suivisprod.utils.NotificationType;

@Service
public class NotificationService {

	private Notification notifications;

	public Notification getNotifications() {
		return notifications;
	}

	public void addNotification(Notification notification) {
		this.notifications = notification;
	}
}
