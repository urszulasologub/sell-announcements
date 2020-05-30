package com.example.announcements.service;

import com.example.announcements.models.Announcement;
import com.example.announcements.models.PrivateMessage;
import com.example.announcements.models.User;
import com.example.announcements.repository.AnnouncementRepository;
import com.example.announcements.repository.PrivateMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivateMessageServiceImp implements PrivateMessageService {

	@Autowired
	AnnouncementRepository announcementRepository;

	@Autowired
	UserService userService;

	@Autowired
	PrivateMessageRepository privateMessageRepository;

	@Override
	public List<PrivateMessage> getConversationWithUser(Announcement announcement_id, User buyer_id) {
		User logged_user = userService.getLoggedInUser();
		if (announcement_id.getUser_id() != logged_user)
			throw new RuntimeException("Can't access someone's private messages");
		List<PrivateMessage> announcements_priv = privateMessageRepository.findByAnnouncementId(announcement_id);
		List<PrivateMessage> conversation = new ArrayList<>();
		for (PrivateMessage message : announcements_priv) {
			if (message.getBuyer() == buyer_id)
				conversation.add(message);
		}
		return conversation;
	}

	@Override
	public List<User> getUsersWhoSentMessageToAnnouncement(Announcement announcement_id) {
		return privateMessageRepository.findBuyerByAnnouncementId(announcement_id);
	}
}