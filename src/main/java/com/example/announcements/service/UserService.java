package com.example.announcements.service;

import com.example.announcements.models.User;

public interface UserService {

	public void saveUser(User user);
	public boolean isUserAlreadyPresent(User user);
}
