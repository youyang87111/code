package com.example.demo.dao2;

import java.util.List;

import com.example.demo.domain.User;

public interface UserDao2 {
	
	void save(User user);

	List<User> list();
}
