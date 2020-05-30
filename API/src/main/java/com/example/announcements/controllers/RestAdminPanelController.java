package com.example.announcements.controllers;


import com.example.announcements.models.*;
import com.example.announcements.repository.*;
import com.example.announcements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class RestAdminPanelController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PrivateMessageRepository privateMessageRepository;


    @RequestMapping(value = { "/admin_auth" }, method = RequestMethod.GET)
    public List<User> adminUserList() {
        return userRepository.findAll();
    }


    @RequestMapping(value = { "/admin_auth" }, method = RequestMethod.POST)
    public User saveUser(@RequestBody User inputUser) {
        User user = userService.getLoggedInUser();
        if (user == null)
            throw new RuntimeException("Not logged in");

        inputUser.setId(null);
        inputUser.setAnnouncements(null);
        return userService.saveUser(inputUser);
    }


    @RequestMapping(value = { "/admin_auth" }, method = RequestMethod.PUT)
    public User editUser(@RequestBody User inputUser) {
        User user = userService.getLoggedInUser();
        if (user == null)
            throw new RuntimeException("Not logged in");

        return userService.saveUser(inputUser);
    }


    @RequestMapping(value = { "/admin_auth/{id}" }, method = RequestMethod.DELETE)
    public ResponseEntity<?> editUser(@PathVariable("id") Integer id) {
        User user = userService.getLoggedInUser();
        if (user == null)
            throw new RuntimeException("Not logged in");

        Optional<User> userToDelete = userRepository.findById(id);
        if (userToDelete.isPresent()) {
            userToDelete.get().setRoles(Collections.emptySet());
            userRepository.save(userToDelete.get());

            userRepository.delete(userToDelete.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = { "/admin_announcement" }, method = RequestMethod.GET)
    public List<Announcement> adminAnnouncementList() {
        return announcementRepository.findAll();
    }


    @RequestMapping(value = { "/admin_announcement/hide/{announcement_id}" }, method = RequestMethod.PUT)
    public Announcement hideAnnouncement(@PathVariable("announcement_id") Integer announcement_id) {
        Optional<Announcement> announcement = announcementRepository.findById(announcement_id);
        if (announcement.isPresent()) {
            announcement.get().setIs_hidden(true);
            return announcementRepository.save(announcement.get());
        }
        return null;
    }


    @RequestMapping(value = { "/admin_announcement/delete/{announcement_id}" }, method = RequestMethod.DELETE)
    public Map<String, String> deleteAnnouncement(@PathVariable("announcement_id") Integer announcement_id) {
        Optional<Announcement> announcement = announcementRepository.findById(announcement_id);
        Map<String, String> result = new HashMap<>();
        if (announcement.isPresent()) {
            announcementRepository.delete(announcement.get());
            result.put("result", "success");
        } else {
            result.put("result", "failure");
        }
        return result;
    }

    @RequestMapping(value = { "/admin_category" }, method = RequestMethod.GET)
    public List<Category> adminCategoryList() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = {"/admin_category/create"}, method=RequestMethod.POST)
    public Category createCategory(@RequestBody Category new_category) {
        new_category.setId(null);
        return categoryRepository.save(new_category);
    }

    @RequestMapping(value = {"/admin_category/delete/{category_id}"}, method = RequestMethod.DELETE)
    public Map<String, String> deleteCategory(@PathVariable("category_id") Integer category_id) {
        Map<String, String> result = new HashMap<>();
        Optional <Category> category = categoryRepository.findById(category_id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            result.put("result", "success");
        } else {
            result.put("result", "failure");
        }
        return result;
    }

    @RequestMapping(value = { "/admin_priv" }, method = RequestMethod.GET)
    public List<PrivateMessage> adminPrivList() {
        return privateMessageRepository.findAll();
    }

}
