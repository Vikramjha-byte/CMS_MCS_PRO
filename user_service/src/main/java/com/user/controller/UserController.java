package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.entity.User;
import com.user.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService service;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{userId}")
	@CircuitBreaker(name = "fallback", fallbackMethod = "fallback")
	public User getUser(@PathVariable("userId") Long userId) {
		// Getting user of the particular user id
		User user = service.getUser(userId);
		// Getting contacts of the particular user id
		List contacts = restTemplate.getForObject("http://contact-service/contact/user/" + userId, List.class);
		// Setting contacts to the particular user
		user.setContacts(contacts);
		return user;
	}

	public User fallback(Exception e) {
		User user= new User();
		return user;
	}
}
