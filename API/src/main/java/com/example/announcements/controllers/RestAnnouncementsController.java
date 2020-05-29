package com.example.announcements.controllers;

import com.example.announcements.dto.AnnouncementDto;
import com.example.announcements.models.Announcement;
import com.example.announcements.models.Category;
import com.example.announcements.models.PrivateMessage;
import com.example.announcements.models.User;
import com.example.announcements.repository.AnnouncementRepository;
import com.example.announcements.repository.CategoryRepository;
import com.example.announcements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class RestAnnouncementsController {

	@Autowired
	AnnouncementRepository announcementRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserService userService;


	@RequestMapping(value = { "/announcements" }, method = RequestMethod.GET)
	public List<Announcement> announcementList() {
		return announcementRepository.findAll();
	}


	@RequestMapping(value = { "/announcements/{id}"}, method = RequestMethod.GET)
	public List<Announcement> announcementListInCategory(@PathVariable("id") Category category_id) {
		return announcementRepository.findByCategoryId(category_id);
	}


	@RequestMapping(value = { "/categories" }, method = RequestMethod.GET)
	public List<Category> CategoryList() {
		return categoryRepository.findAll();
	}


	@RequestMapping(value = { "/announcements/add" }, method = RequestMethod.POST)
	public Announcement saveAnnouncement(@RequestBody AnnouncementDto inputAnnouncement) {
		User user = userService.getLoggedInUser();
		if (user == null)
			throw new RuntimeException("Not logged in");
		inputAnnouncement.setId(null);
		inputAnnouncement.setUser_id(user);
		inputAnnouncement.setIs_hidden(false);
		inputAnnouncement.setStatus("UNSOLD");
		inputAnnouncement.setDatetime(new Date());
		Announcement newAnnouncement = new Announcement(
				inputAnnouncement.getId(),
				categoryRepository.findCategoryById(inputAnnouncement.getIntegerCategory_id()),
				user,
				inputAnnouncement.getName(),
				inputAnnouncement.getPrice(),
				inputAnnouncement.getDescription(),
				inputAnnouncement.getStatus(),
				inputAnnouncement.getImage(),
				inputAnnouncement.getIs_hidden(),
				inputAnnouncement.getPhone_number(),
				inputAnnouncement.getDatetime(),
				inputAnnouncement.getLocation()
		);

		return announcementRepository.save(newAnnouncement);
	}


	//TODO: add request GET for getting announcement


}
