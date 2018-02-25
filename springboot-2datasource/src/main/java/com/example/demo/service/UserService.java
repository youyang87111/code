package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.User;

public interface UserService {
	
	void save(User user);

	List<User> list1();
	List<User> list2();
}
