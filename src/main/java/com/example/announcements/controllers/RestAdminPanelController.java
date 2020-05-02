package com.example.announcements.controllers;


import com.example.announcements.models.*;
import com.example.announcements.repository.RoleRepository;
import com.example.announcements.repository.UserRepository;
import com.example.announcements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class RestAdminPanelController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = { "/admin_auth" }, method = RequestMethod.GET)
    public List<User> adminUserList() {
        return userRepository.findAll();
    }

    @RequestMapping(value = { "/admin_auth" }, method = RequestMethod.POST)
    public User saveUser(@RequestBody User inputUser) {
        inputUser.setId(null);
        inputUser.setAnnouncements(null);
        inputUser.setUser_id(null);
        return userService.saveUser(inputUser);
    }

    @RequestMapping(value = { "/admin_auth" }, method = RequestMethod.PUT)
    public User editUser(@RequestBody User inputUser) {
        return userService.saveUser(inputUser);
    }

    @RequestMapping(value = { "/admin_auth/{id}" }, method = RequestMethod.DELETE)
    public ResponseEntity<?> editUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = { "/admin_announcement" }, method = RequestMethod.GET)
    public List<Announcement> adminAnnouncementList() {
        return null; // TODO
    }

    @RequestMapping(value = { "/admin_category" }, method = RequestMethod.GET)
    public List<Category> adminCategoryList() {
        return null; // TODO
    }

    @RequestMapping(value = { "/admin_priv" }, method = RequestMethod.GET)
    public List<PrivateMessage> adminPrivList() {
        return null; // TODO
    }

}
