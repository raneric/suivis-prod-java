package com.sgg.suivisprod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgg.suivisprod.domain.Notification;
import com.sgg.suivisprod.domain.User;
import com.sgg.suivisprod.repository.NotificationRepository;
import com.sgg.suivisprod.utils.NotificationType;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	public List<Notification> getAllNotifications(String username) {
		return notificationRepository.findByuserName(username);
	}
	
	public void setAsRead(String id) {
		Optional<Notification> notif = notificationRepository.findById(id);
		if(!notif.isEmpty()) {
			Notification readNotif = notif.get();
			readNotif.setRead(true);
			notificationRepository.save(readNotif);
		}
			
	}
	
	public void notify(NotificationType notifType, String message, User user) {
		Notification notif = new Notification(notifType, message,user);
		this.addNotification(notif);
	}
	
	private void addNotification(Notification notification) {
		notificationRepository.save(notification);
	}
}
