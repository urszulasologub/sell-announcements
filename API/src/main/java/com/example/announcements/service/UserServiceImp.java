package com.example.announcements.service;

import com.example.announcements.models.Role;
import com.example.announcements.models.User;
import com.example.announcements.repository.RoleRepository;
import com.example.announcements.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


@Service
public class UserServiceImp implements UserService {

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;


	@PostConstruct
	private void postConstruct() {
		if (roleRepository.count() == 0) {
			Role admin = new Role();
			admin.setRole("ADMIN_USER");
			Role siteuser = new Role();
			siteuser.setRole("SITE_USER");
			roleRepository.save(admin);
			roleRepository.save(siteuser);
		}
	}


	@Override
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}


	@Override
	public boolean isUserAlreadyPresent(User user) {
		ExampleMatcher modelMatcher = ExampleMatcher.matching()
				.withIgnorePaths("id")
				.withMatcher("email", ignoreCase());
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		return userRepository.exists(Example.of(newUser, modelMatcher));
	}

	/**
	 * Zwraca zalogowanego użytkownika lub null.
	 *
	 * @return obiekt zalogowanego użytkownika lub null
	 */
	@Override
	public User getLoggedInUser() {
		Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
		if (currentAuthentication != null && currentAuthentication.isAuthenticated()) {
			String username = null;
			if (currentAuthentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
				username = ((org.springframework.security.core.userdetails.User) currentAuthentication.getPrincipal()).getUsername();
			} else {
				username = (String) currentAuthentication.getPrincipal();
			}

			Optional<User> userOptional = userRepository.findUserByEmail(username);
			return userOptional.orElse(null); // jeśli znajdzie użytkownika - zwracamy go, a jak nie to null
		} else {
			return null;
		}
	}
}
