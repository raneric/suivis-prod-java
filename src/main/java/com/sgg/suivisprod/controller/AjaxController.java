package com.sgg.suivisprod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sgg.suivisprod.services.NotificationService;

@RestController
public class AjaxController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/notification/read/{id}")
	public String setNotifAsRead(@PathVariable String id) {
		notificationService.setAsRead(id);
		return "done";
	}
}
