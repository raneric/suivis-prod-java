package com.sgg.suivisprod.services;
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

	private void addNotification(Notification notification) {
		this.notifications.add(notification);
	}
	
	public void notify(NotificationType notifType, String message) {
		Notification notif = new Notification(notifType, message);
		this.addNotification(notif);
	}
}
