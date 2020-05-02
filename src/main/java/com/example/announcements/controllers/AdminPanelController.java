package com.example.announcements.controllers;


import com.example.announcements.models.Role;
import com.example.announcements.repository.RoleRepository;
import com.example.announcements.repository.UserRepository;
import com.example.announcements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class AdminPanelController {


	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "/admin_auth" }, method = RequestMethod.GET)
	public ModelAndView adminUserList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_auth");
		modelAndView.addObject("users", userRepository.findAll());
		return modelAndView;
	}

	@RequestMapping(value = { "/admin_announcement" }, method = RequestMethod.GET)
	public ModelAndView adminAnnouncementList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_announcement");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin_category" }, method = RequestMethod.GET)
	public ModelAndView adminCategoryList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_category");
		return modelAndView;
	}

	@RequestMapping(value = { "/admin_priv" }, method = RequestMethod.GET)
	public ModelAndView adminPrivList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin_priv");
		return modelAndView;
	}

}
