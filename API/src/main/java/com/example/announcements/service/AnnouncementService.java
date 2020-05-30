package com.example.announcements.service;

import com.example.announcements.models.Announcement;
import com.example.announcements.models.Category;

import java.util.List;

public interface AnnouncementService {

	public List<Announcement> getAllPublicAnnouncements();
	public List<Announcement> getPublicAnnouncementsInCategory(Category category_id);
	public Announcement getAnnouncementById(Integer id);
}
