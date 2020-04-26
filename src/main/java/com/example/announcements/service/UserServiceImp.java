package com.example.announcements.service;

import java.util.Arrays;
import java.util.HashSet;

import com.example.announcements.models.Role;
import com.example.announcements.models.User;
import com.example.announcements.repository.RoleRepository;
import com.example.announcements.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;


	@PostConstruct
	private void postConstruct() {
		Role admin = new Role();
		admin.setRole("ADMIN_USER");
		Role superuser = new Role();
		superuser.setRole("SUPER_USER");
		Role siteuser = new Role();
		siteuser.setRole("SITE_USER");
		roleRepository.save(admin);
		roleRepository.save(superuser);
		roleRepository.save(siteuser);
	}


	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		// TODO: Implement
		return false;
	}

}
