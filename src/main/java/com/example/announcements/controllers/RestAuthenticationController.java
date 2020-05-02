package com.example.announcements.controllers;

import com.example.announcements.models.Role;
import com.example.announcements.models.User;
import com.example.announcements.repository.RoleRepository;
import com.example.announcements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestAuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestBody User user, HttpServletRequest httpServletRequest, HttpSession session) {
        Map<String, String> result = new HashMap<>();

        if (user.getEmail() != null && user.getPassword() != null) {
            String email = user.getEmail();
            String password = user.getPassword();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            session.invalidate();
            HttpSession newSession = httpServletRequest.getSession(true);

            result.put("result", "success");
            result.put("session", newSession.getId());
        } else {
            result.put("result", "failure");
        }
        return result;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        user.setId(null);
        user.setAnnouncements(null);
        user.setUser_id(null);
        return userService.saveUser(user);
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin"); // resources/template/admin.html
        return modelAndView;
    }

}
