package com.example.announcements.dto;

import com.example.announcements.models.Announcement;

public class AnnouncementDto extends Announcement {

	private Integer category_id;
	private String description;


	public Integer getIntegerCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
}