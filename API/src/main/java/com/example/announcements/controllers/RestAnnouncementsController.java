package com.example.announcements.controllers;

import com.example.announcements.models.Announcement;
import com.example.announcements.models.Category;
import com.example.announcements.models.User;
import com.example.announcements.repository.AnnouncementRepository;
import com.example.announcements.repository.CategoryRepository;
import com.example.announcements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAnnouncementsController {

	@Autowired
	AnnouncementRepository announcementRepository;

	@Autowired
	CategoryRepository categoryRepository;


	@RequestMapping(value = { "/announcements" }, method = RequestMethod.GET)
	public List<Announcement> announcementList() {
		return announcementRepository.findAll();
	}
	//TODO: return announcements from selected category


	@RequestMapping(value = { "/categories" }, method = RequestMethod.GET)
	public List<Category> CategoryList() {
		return categoryRepository.findAll();
	}


	@RequestMapping(value = { "/announcements/add" }, method = RequestMethod.POST)
	public Announcement saveAnnouncement(@RequestBody Announcement inputAnnouncement) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		inputAnnouncement.setId(null);
		inputAnnouncement.setUser_id(user.getUser_id());
		inputAnnouncement.setIs_hidden(false);
		inputAnnouncement.setCategory_id(null);
		inputAnnouncement.setPrivate_message_id(null);
		return announcementRepository.save(inputAnnouncement);
	}


	//TODO: add request GET for getting announcement


}
