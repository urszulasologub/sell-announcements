package com.example.announcements.controllers;


import com.example.announcements.dto.AnnouncementDto;
import com.example.announcements.models.Announcement;
import com.example.announcements.models.Category;
import com.example.announcements.models.PrivateMessage;
import com.example.announcements.models.User;
import com.example.announcements.repository.AnnouncementRepository;
import com.example.announcements.repository.PrivateMessageRepository;
import com.example.announcements.repository.UserRepository;
import com.example.announcements.service.PrivateMessageService;
import com.example.announcements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class RestPrivateMessagesController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AnnouncementRepository announcementRepository;

	@Autowired
	PrivateMessageRepository privateMessageRepository;

	@Autowired
	PrivateMessageService privateMessageService;

	@RequestMapping(value = { "/announcements/{announcement_id}/messages/{user_id}" }, method = RequestMethod.GET)
	public List<PrivateMessage> getMessages(@PathVariable("announcement_id") Announcement announcement_id, @PathVariable("user_id") User buyer_id) {
		return privateMessageService.getConversationWithUser(announcement_id, buyer_id);
	}

	@RequestMapping(value = { "/announcements/{announcement_id}/messages" }, method = RequestMethod.GET)
	public List<User> getUsersWhoSentMessage(@PathVariable("announcement_id") Announcement announcement_id) {
		return privateMessageService.getUsersWhoSentMessageToAnnouncement(announcement_id);
	}

	@RequestMapping(value = { "/announcements/{announcement_id}/messages/send" }, method = RequestMethod.POST)
	public PrivateMessage sendPrivateMessage(@RequestBody PrivateMessage inputMessage, @PathVariable("announcement_id") Announcement announcement_id) {
		User user = userService.getLoggedInUser();
		if (user == null)
			throw new RuntimeException("Not logged in");
		//TODO: check if announcement exists
		inputMessage.setDatetime(Calendar.getInstance().getTime());
		inputMessage.setBuyer(user);
		inputMessage.setAnnouncement_id(announcement_id);
		return privateMessageRepository.save(inputMessage);
	}

}
