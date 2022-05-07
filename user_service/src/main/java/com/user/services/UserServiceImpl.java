package com.user.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entity.User;

@Service
public class UserServiceImpl implements UserService {

	// Fake User List

	List<User> list = List.of(new User(1311L, "Vikram Jha", "7979893048"), new User(1312L, "Akshay Jha", "9876543210"),
			new User(1313L, "Raghav Jha", "6549873210"));

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return list.stream().filter(user -> user.getUserId().equals(id)).findAny().orElse(null);
	}

}
